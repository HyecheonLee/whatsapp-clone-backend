package com.hyecheon.whatsappclonebackend.config

import com.fasterxml.jackson.core.type.*
import com.fasterxml.jackson.databind.*
import com.hyecheon.whatsappclonebackend.model.*
import com.hyecheon.whatsappclonebackend.repository.*
import org.springframework.boot.*
import org.springframework.core.io.*
import org.springframework.stereotype.*
import org.springframework.transaction.annotation.*


/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
@Component
class InitDB(
	private val userRepository: UserRepository,
	private val messageRepository: MessageRepository,
	private val objectMapper: ObjectMapper
) : CommandLineRunner {

	@Transactional
	override fun run(vararg args: String?) {
/*		val usersJson = String(ClassPathResource("users.json").inputStream.readAllBytes())
		val users = objectMapper.readValue(usersJson, object : TypeReference<List<User>>() {})
		users.forEach { user ->
			userRepository.save(user.copy(contacts = mutableListOf()))
		}
		userRepository.flush()
		userRepository.saveAll(users)*/
	}
}