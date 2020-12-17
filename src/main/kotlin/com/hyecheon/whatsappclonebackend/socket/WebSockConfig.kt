package com.hyecheon.whatsappclonebackend.socket

import org.springframework.context.annotation.*
import org.springframework.messaging.simp.config.*
import org.springframework.web.socket.config.annotation.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
@Configuration
@EnableWebSocketMessageBroker
class WebSockConfig : WebSocketMessageBrokerConfigurer {
	override fun configureMessageBroker(registry: MessageBrokerRegistry) {
		registry.enableSimpleBroker("/sub")
		registry.setApplicationDestinationPrefixes("/pub")
	}

	override fun registerStompEndpoints(registry: StompEndpointRegistry) {
		registry.addEndpoint("/chat").setAllowedOriginPatterns("*")
			.withSockJS()
	}
}