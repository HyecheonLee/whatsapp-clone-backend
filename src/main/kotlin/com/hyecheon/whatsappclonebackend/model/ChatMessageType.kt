package com.hyecheon.whatsappclonebackend.model

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
sealed class ChatMessageType(val type: String) {
	class Enter(_type: String) : ChatMessageType(_type)
	class Text(_type: String) : ChatMessageType(_type)
}