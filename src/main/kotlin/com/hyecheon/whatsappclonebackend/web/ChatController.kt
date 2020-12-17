package com.hyecheon.whatsappclonebackend.web

import com.hyecheon.whatsappclonebackend.model.*
import com.hyecheon.whatsappclonebackend.repository.*
import com.hyecheon.whatsappclonebackend.web.dto.*
import org.springframework.messaging.handler.annotation.*
import org.springframework.messaging.simp.*
import org.springframework.stereotype.*
import org.springframework.web.bind.annotation.*


/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@Controller
class ChatController(
	private val webSocket: SimpMessagingTemplate,
	private val messageRepository: MessageRepository
) {

	@MessageMapping("/chat/message")
	fun sendToMessage(chatMessage: ChatMessage) {
		val message = chatMessage.toMessage()
		webSocket.convertAndSend("/sub/chat/${chatMessage.channel}", ResponseChatMessageDto.from(messageRepository.save(message)))
		webSocket.convertAndSend("/sub/chat/user/${chatMessage.sentBy}/last-message", ResponseChatMessageDto.from(messageRepository.save(message)))
	}
}