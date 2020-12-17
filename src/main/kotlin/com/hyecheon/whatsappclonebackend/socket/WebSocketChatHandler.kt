package com.hyecheon.whatsappclonebackend.socket

import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.module.kotlin.*
import com.hyecheon.whatsappclonebackend.model.*
import com.hyecheon.whatsappclonebackend.service.*
import com.hyecheon.whatsappclonebackend.web.dto.*
import org.slf4j.*
import org.springframework.stereotype.*
import org.springframework.web.socket.*
import org.springframework.web.socket.handler.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
@Component
class WebSocketChatHandler(
	private val objectMapper: ObjectMapper,
	private val chatService: ChatService
) : TextWebSocketHandler() {
	private val log = LoggerFactory.getLogger(this::class.java)
	override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
		val payload = message.payload
		val chatMessage = objectMapper.readValue<ChatMessage>(payload)
		val room = chatService.findRoomById(chatMessage.channel, session)
		room.handleAction(session, chatMessage, chatService)
	}
}