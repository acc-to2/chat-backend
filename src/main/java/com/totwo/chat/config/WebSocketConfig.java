package com.totwo.chat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${spring.activemq.broker-url}")
    private String mqHost;

    @Value("${spring.activemq.port}")
    private int mqPort;

    @Value("${spring.activemq.user}")
    private String mqUsername;

    @Value("${spring.activemq.password}")
    private String mqPassword;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // 클라이언트가 연결할 WebSocket endpoint
                .setAllowedOriginPatterns("*")
                .withSockJS(); // SockJS fallback
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/chat"); // 클라이언트 → 서버 메시지 전송 prefix
        registry.enableStompBrokerRelay("/chat") // 외부 메시지 브로커 사용
                .setRelayHost(mqHost) // MQ 호스트
                .setRelayPort(mqPort) // MQ 포트
                .setClientLogin(mqUsername) // MQ 사용자명
                .setClientPasscode(mqPassword) // MQ 비밀번호
                .setSystemLogin(mqUsername)
                .setSystemPasscode(mqPassword)
                .setVirtualHost("/"); // 가상 호스트 설정
    }
}
