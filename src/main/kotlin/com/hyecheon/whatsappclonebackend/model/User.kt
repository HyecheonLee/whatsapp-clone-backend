package com.hyecheon.whatsappclonebackend.model

import com.fasterxml.jackson.annotation.*
import org.springframework.web.bind.annotation.*
import java.time.*
import javax.persistence.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
@Entity
data class User(
	@Id
	val id: Long? = null,
	@OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	val messages: MutableList<Message> = mutableListOf(),
	@ManyToMany(fetch = FetchType.LAZY)
	val contacts: MutableList<User> = mutableListOf(),
	val name: String = "",
	val passcode: String = "",
	val avatar: String = "",
	val lastSeen: LocalDateTime = LocalDateTime.now()
) : BaseEntity() {
	override fun toString(): String {
		return "User(id=$id, name='$name', passcode='$passcode', avatar='$avatar', lastSeen=$lastSeen)"
	}
}
