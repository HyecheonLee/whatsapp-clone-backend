package com.hyecheon.whatsappclonebackend.model

import org.springframework.data.annotation.*
import org.springframework.data.jpa.repository.config.*
import java.time.*
import javax.persistence.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
@MappedSuperclass
@EnableJpaAuditing
abstract class BaseEntity {
	@CreatedDate
	@Column(updatable = false)
	val createdDate: LocalDateTime = LocalDateTime.now()

	@LastModifiedDate
	@Column(updatable = true)
	val lastModifiedDate: LocalDateTime = LocalDateTime.now()
}