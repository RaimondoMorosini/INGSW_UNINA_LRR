package api.dieti2024.websockettest;

import api.dieti2024.exceptions.ApiException;
import api.dieti2024.model.Notifica;
import api.dieti2024.util.JsonUtil;
import api.dieti2024.util.WebSocketUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.postgresql.PGConnection;
import org.postgresql.PGNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class ListenerPerLeNotifiche {

    @Autowired
    private DataSource dataSource;

    private PGConnection pgConnection;
    @Autowired
    private WebSocketUtil webSocketUtil;

    @PostConstruct
    public void listen() {
        try (Connection conn = dataSource.getConnection()) {
            pgConnection = conn.unwrap(PGConnection.class);

            try (Statement stmt = conn.createStatement()) {
                stmt.execute("LISTEN notifica_insert_channel");
            }
            CompletableFuture.runAsync(this::processNotifications);

        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }

    public void processNotifications() {
        while (true) {
            try {
                PGNotification[] notifications = pgConnection.getNotifications();
                if (notifications != null) {
                    for (PGNotification notification : notifications) {

                        try {
                            Notifica notifica = JsonUtil.fromJson(notification.getParameter(), Notifica.class);
                            inviaNotificaNuovaOfferta(notifica);
                        }catch(ApiException e){
                            //e.printStackTrace();
                        }
                        catch (Exception e) {
                            //e.printStackTrace();
                        }

                    }
                }

                Thread.sleep(500);
            } catch (SQLException | InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }
    private void inviaNotificaNuovaOfferta(Notifica notifica) {
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("message", "Un utente ha fatto un'offerta:");
        messageMap.put("offerta", notifica);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessage = "";
        try {
            jsonMessage = objectMapper.writeValueAsString(messageMap);
        } catch (JsonProcessingException e) {
            //TODO RIMPIAZZARE CON LOG O SIMILI
            System.out.println("errore in ListenerPerLeNotifiche nella formatazzione del messaggio per invio della notifica ");
        }
        webSocketUtil.inviaMessaggio(jsonMessage, "/notifichePersonali/" + notifica.getUtente());

    }
}
