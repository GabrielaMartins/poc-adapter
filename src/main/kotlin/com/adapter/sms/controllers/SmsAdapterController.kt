package com.adapter.sms.controllers

import com.adapter.sms.models.SmsAdapterMessageInput
import com.adapter.sms.services.SmsAdapterService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class SmsAdapterController(
    private val smsAdapterService: SmsAdapterService
) {
    @PostMapping(value = ["/send-messages"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun sendMessages(@RequestBody message: SmsAdapterMessageInput): ResponseEntity<String> {
        return ResponseEntity.ok(smsAdapterService.sendMessage(message))
    }

    @PostMapping(value = ["/reply-messages"], produces = ["text/xml"])
    @ResponseBody
    fun replyMessages(@RequestParam("From") from: String,
                      @RequestParam("Body") body: String): ResponseEntity<String> {

        return ResponseEntity.ok( smsAdapterService.replyMessage(body, from).toXml())
    }
}