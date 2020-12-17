package com.hyecheon.whatsappclonebackend.model

import com.fasterxml.jackson.annotation.*
import javax.persistence.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
@Entity
data class Message(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null,
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	val user: User,
	val channel: String,
	val type: String,
	@Lob
	val message: String? = null,
	@Lob
	val fileUrl: String? = null,
) : BaseEntity() {
	override fun toString(): String {
		return "Message(id=$id, channel='$channel', type='$type', message='$message', fileUrl='$fileUrl')"
	}
}