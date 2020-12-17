package com.hyecheon.whatsappclonebackend.web.dto

import com.hyecheon.whatsappclonebackend.model.*
import java.time.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
data class ResponseChatMessageDto(
	val id: Long,
	val user: ResponseUserDto,
	val channel: String,
	val type: String,
	val message: String? = null,
	val fileUrl: String? = null,
	val createdDate: LocalDateTime
) {
	companion object {
		fun from(message: Message) = run {
			if (message.id == null) throw RuntimeException("id가 없습니다.")
			ResponseChatMessageDto(
				message.id,
				ResponseUserDto.fromUser(message.user),
				message.channel,
				message.type,
				message.message,
				message.fileUrl,
				message.createdDate
			)
		}
	}
}