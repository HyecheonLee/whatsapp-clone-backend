package com.hyecheon.whatsappclonebackend.web.dto

import com.hyecheon.whatsappclonebackend.model.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
data class RequestMessageDto(
	val sentBy: Long,
	val channel: String,
	val type: String,
	val message: String? = null,
	val fileUrl: String? = null
) {
	fun toMessage() = run {
		var message = Message(user = User(id = sentBy), channel = channel, type = type)
		if (this.message != null) {
			message = message.copy(message = this.message)
		}
		if (this.fileUrl != null) {
			message = message.copy(fileUrl = this.fileUrl)
		}
		message
	}
}