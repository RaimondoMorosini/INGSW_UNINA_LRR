package api.dieti2024.websockettest;

import api.dieti2024.exceptions.ApiException;
import api.dieti2024.model.Offerta;
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
public class DatabaseNotificationListener {

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
                stmt.execute("LISTEN offerta_insert_channel");
                stmt.execute("LISTEN notifica_insert_channel");
            }
            CompletableFuture.runAsync(this::processNotifications);

        } catch (SQLException e) {
            // Gestisci l'eccezione
        }
    }

    public void processNotifications() {
        while (true) {
            try {
                PGNotification[] notifications = pgConnection.getNotifications();
                if (notifications != null) {
                    for (PGNotification notification : notifications) {
                        String channel = notification.getName();
                        String payload = notification.getParameter();

                        try {
                            if ("offerta_insert_channel".equals(channel)) {
                                Offerta offerta = JsonUtil.fromJson(payload, Offerta.class);
                                inviaNotificaOfferta(offerta);
                            } else if ("notifica_insert_channel".equals(channel)) {
                                Notifica notifica = JsonUtil.fromJson(payload, Notifica.class);
                                inviaNotificaPersonale(notifica);
                            }
                        } catch (ApiException e) {
                            // Gestisci l'ApiException
                        } catch (Exception e) {
                            // Gestisci altre eccezioni
                        }
                    }
                }
                Thread.sleep(500);
            } catch (SQLException | InterruptedException e) {
                // Gestisci l'eccezione
            }
        }
    }

    private void inviaNotificaOfferta(Offerta offerta) {
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("message", "Un utente ha fatto un'offerta:");
        messageMap.put("offerta", offerta);

        inviaMessaggioWebSocket(messageMap, "/asta/" + offerta.getAstaId());
    }

    private void inviaNotificaPersonale(Notifica notifica) {
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("message", "Hai una nuova notifica personale:");
        messageMap.put("notifica", notifica);

        inviaMessaggioWebSocket(messageMap, "/notifichePersonali/" + notifica.getUtente());
    }

    private void inviaMessaggioWebSocket(Map<String, Object> messageMap, String destination) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonMessage = objectMapper.writeValueAsString(messageMap);
            webSocketUtil.inviaMessaggio(jsonMessage, destination);
        } catch (JsonProcessingException e) {
            System.out.println("Errore nella formattazione del messaggio JSON per WebSocket");
        }
    }
}
