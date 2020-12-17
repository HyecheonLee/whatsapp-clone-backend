package com.hyecheon.whatsappclonebackend.repository

import com.hyecheon.whatsappclonebackend.model.*
import org.springframework.data.jpa.repository.*
import java.util.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
interface UserRepository : JpaRepository<User, Long> {
	fun findByPasscode(passcode: String): Optional<User>

	@Query(value = "select u from User u join fetch u.contacts where u.passcode = :passcode")
	fun findByPasscodeWithContacts(passcode: String): Optional<User>
}