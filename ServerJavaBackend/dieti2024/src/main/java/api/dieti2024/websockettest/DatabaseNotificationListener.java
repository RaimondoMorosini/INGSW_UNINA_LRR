package api.dieti2024.websockettest;

import jakarta.annotation.PostConstruct;
import org.postgresql.PGConnection;
import org.postgresql.PGNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.CompletableFuture;

@Component
public class DatabaseNotificationListener {

    @Autowired
    private DataSource dataSource;

    private PGConnection pgConnection;

    @PostConstruct
    public void listen() {
        try (Connection conn = dataSource.getConnection()) {
            pgConnection = conn.unwrap(PGConnection.class);

            try (Statement stmt = conn.createStatement()) {
                stmt.execute("LISTEN offerta_insert_channel");
            }
            CompletableFuture.runAsync(this::processNotifications);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void processNotifications() {
        while (true) {
            try {
                PGNotification[] notifications = pgConnection.getNotifications();
                if (notifications != null) {
                    for (PGNotification notification : notifications) {
                        System.out.println("Received notification: " + notification.getParameter());
                    }
                }

                Thread.sleep(500);
            } catch (SQLException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}