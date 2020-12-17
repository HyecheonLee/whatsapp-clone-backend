package com.hyecheon.whatsappclonebackend.web

import com.hyecheon.whatsappclonebackend.repository.*
import com.hyecheon.whatsappclonebackend.web.dto.*
import org.springframework.data.domain.*
import org.springframework.data.web.*
import org.springframework.http.*
import org.springframework.web.bind.annotation.*

/**
 * @author hyecheon
 * @email rainbow880616@gmail.com
 */
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@RestController
@RequestMapping("/api/v1/users")
class UserApi(
	val userRepository: UserRepository
) {

	@GetMapping
	fun user(userSearchDto: UserSearchDto, @PageableDefault(size = 10, page = 0, direction = Sort.Direction.DESC) pageable: Pageable) = run {
		if (userSearchDto.passcode != null) {
			val responseData = ResponseUserWithContactDto.fromUser(
				userRepository.findByPasscodeWithContacts(userSearchDto.passcode).orElseThrow { RuntimeException("passcode Exception[${userSearchDto.passcode}]") })
			ResponseEntity.ok(responseData)
		} else {
			ResponseEntity.ok(userRepository.findAll(pageable).content)
		}
	}
}