package com.zomer.chatags.config;

import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // El "enchufe" principal. El frontend se conecta aquí: "ws://localhost:8080/ws"
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*") // Permite conexiones desde cualquier lado
                .withSockJS(); // Habilita compatibilidad si el navegador no soporta WS nativo
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Prefijo para enviar mensajes desde el cliente al servidor (ej: /app/chat)
        registry.setApplicationDestinationPrefixes("/app");

        // Habilitamos dos tipos de rutas de salida:
        // 1. /topic: Para cosas públicas (ej: notificaciones generales)
        // 2. /queue: Para mensajes PRIVADOS (1 a 1)
        registry.enableSimpleBroker("/topic", "/queue");

        // CLAVE PARA CHAT 1 a 1:
        // Cuando enviemos algo a un usuario específico, Spring usará este prefijo.
        // Ej: /user/david/queue/mensajes
        registry.setUserDestinationPrefix("/user");
    }
}
