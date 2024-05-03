package api.dieti2024.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableWebSocket
public class DynamicWebSocketConfig /*implements WebSocketConfigurer */{

    private final Map<String, WebSocketHandler> handlers = new ConcurrentHashMap<>();

    @Autowired
    private WebSocketHandler  handlerFactory;
/*
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // Non è necessario aggiungere alcun handler qui
    }

    public void addWebSocketHandler(String path) {
        WebSocketHandler handler = handlerFactory.createHandler();
        handlers.put(path, handler);
        // Aggiungi l'endpoint WebSocket dinamico
        registry.addHandler(handler, path).setAllowedOrigins("*");
    }

    public void removeWebSocketHandler(String path) {
        WebSocketHandler handler = handlers.remove(path);
        // Rimuovi l'endpoint WebSocket
        if (handler != null) {
            registry.removeHandler(handler);
        }
    }

 */
}
