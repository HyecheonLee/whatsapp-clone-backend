package com.hyecheon.whatsappclonebackend.model

import com.hyecheon.whatsappclonebackend.service.*
import org.springframework.web.socket.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
class ChatRoom(
	val channel: String,
	private val sessions: MutableSet<WebSocketSession> = mutableSetOf()
) {
	fun addSession(session: WebSocketSession) {
		sessions.add(session)
	}

	fun handleAction(session: WebSocketSession, chatMessage: ChatMessage, chatService: ChatService) {
		if (chatMessage.type == "Enter") {
			sessions.add(session)
		} else {
			sendMessage(chatMessage, chatService)
		}
	}

	private fun <T> sendMessage(message: T, chatService: ChatService) {
		sessions.parallelStream().forEach {
			chatService.sendMessage(it, message)
		}
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is ChatRoom) return false

		if (channel != other.channel) return false

		return true
	}

	override fun hashCode(): Int {
		return channel.hashCode()
	}
}