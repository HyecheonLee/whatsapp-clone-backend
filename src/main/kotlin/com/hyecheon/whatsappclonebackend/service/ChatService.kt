package com.hyecheon.whatsappclonebackend.service

import com.fasterxml.jackson.databind.*
import com.hyecheon.whatsappclonebackend.model.*
import com.hyecheon.whatsappclonebackend.repository.*
import org.slf4j.*
import org.springframework.stereotype.*
import org.springframework.web.socket.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
@Service
class ChatService(
	private val messageRepository: MessageRepository,
	private val objectMapper: ObjectMapper
) {
	private val chatRooms = mutableMapOf<String, ChatRoom>()

	private val log = LoggerFactory.getLogger(this::class.java)
	fun <T> sendMessage(session: WebSocketSession, message: T) {
		if (message != null) {
			try {
				session.sendMessage(TextMessage(message.toJson()))

			} catch (e: Exception) {
				log.error(e.message, e)
			}
		}
	}

	fun findRoomById(channel: String, session: WebSocketSession): ChatRoom {
		return chatRooms.getOrPut(channel) {
			ChatRoom(channel).apply { addSession(session) }
		}
	}

	fun createRoom(channel: String): ChatRoom {
		return chatRooms.getOrPut(channel) { ChatRoom(channel) }
	}

	fun Any.toJson(): String {
		return objectMapper.writeValueAsString(this)
	}
}