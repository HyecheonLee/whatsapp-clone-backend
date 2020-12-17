package com.hyecheon.whatsappclonebackend.web.dto

import com.fasterxml.jackson.annotation.*
import com.hyecheon.whatsappclonebackend.model.*
import java.time.*
import javax.persistence.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
data class ResponseUserDto(
	@Id
	val id: Long? = null,
	val name: String = "",
	val avatar: String = "",
	val lastSeen: LocalDateTime = LocalDateTime.now()
) {
	companion object {
		fun fromUser(user: User) = run {
			ResponseUserDto(user.id, user.name, user.avatar, user.lastSeen)
		}
	}
}

data class ResponseUserWithContactDto(
	@Id
	val id: Long? = null,
	val name: String = "",
	val contacts: MutableList<ResponseUserDto> = mutableListOf(),
	val avatar: String = "",
	val lastSeen: LocalDateTime = LocalDateTime.now()
) {
	companion object {
		fun fromUser(user: User) = run {
			val contacts = user.contacts.map { ResponseUserDto.fromUser(it) }.toMutableList()
			ResponseUserWithContactDto(user.id, user.name, contacts, user.avatar, user.lastSeen)
		}
	}
}
