package com.hyecheon.whatsappclonebackend.repository

import com.hyecheon.whatsappclonebackend.model.*
import org.springframework.data.domain.*
import org.springframework.data.jpa.repository.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
interface MessageRepository : JpaRepository<Message, Long> {
	fun findMessagesByChannelIn(channels: Collection<String>, pageable: Pageable): Page<Message>

	fun findMessagesByChannelOrderByCreatedDateDesc(channel: String, pageable: Pageable): Page<Message>
}