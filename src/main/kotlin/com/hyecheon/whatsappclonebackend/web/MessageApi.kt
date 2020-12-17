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
@RequestMapping("/api/v1/messages")
class MessageApi(
	private val messageRepository: MessageRepository
) {
	@PostMapping
	fun createMessage(@RequestBody requestMessageDto: RequestMessageDto) {
		val message = requestMessageDto.toMessage()
		ResponseEntity.ok(ResponseDto(ResponseChatMessageDto.from(messageRepository.save(message))))
	}

	@GetMapping
	fun getMessage(@RequestParam(value = "channels") channels: List<String>, @PageableDefault(size = 10, page = 0, direction = Sort.Direction.DESC) pageable: Pageable) = run {
		val data = messageRepository.findMessagesByChannelIn(channels, pageable).map { message -> ResponseChatMessageDto.from(message) }
		ResponseEntity.ok(ResponseDto(data))
	}

	@GetMapping(value = ["/send/{sendId}/to/{toId}/last-message"])
	fun getMessage(@PathVariable sendId: String, @PathVariable toId: String) = run {
		val message = messageRepository.findMessagesByChannelOrderByCreatedDateDesc("${sendId}_${toId}", PageRequest.of(0, 1))
		val data = message
			.map { message -> ResponseChatMessageDto.from(message) }
		ResponseEntity.ok(data)
	}
}