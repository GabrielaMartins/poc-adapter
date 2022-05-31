package com.adapter.sms.services

import com.adapter.sms.models.SmsAdapterMessageInput
import com.twilio.rest.api.v2010.account.Message
import com.twilio.twiml.MessagingResponse
import com.twilio.twiml.messaging.Body
import com.twilio.twiml.messaging.Message as MessageTwiml
import com.twilio.type.PhoneNumber
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service



interface SmsAdapterServiceAbstract{
    fun sendMessage(sms: SmsAdapterMessageInput): String
    fun replyMessage(body: String, from: String): MessagingResponse
}

@Service
class SmsAdapterService: SmsAdapterServiceAbstract {
    private val logger: org.slf4j.Logger? = LoggerFactory.getLogger("SmsAdapterService")

    override fun sendMessage(sms: SmsAdapterMessageInput): String {
        val message = Message.creator(
            PhoneNumber(sms.phoneReceiver),
            PhoneNumber(sms.phoneSender),
            sms.body
        ).create()

        return if(message.errorMessage.isNullOrEmpty()) {
            logger?.info("MessageId: " + message.sid)
            message.sid
        } else {
            message.errorMessage
        }
    }

    override fun replyMessage(body: String, from: String): MessagingResponse {
        var message = ""

        if (body == "hello") {
            message = "Hi there! Your number is $from"
        } else if (body.equals("bye")) {
            message = "Goodbye!"
        }

        val messageBody =  Body.Builder(message).build()
        val sms = MessageTwiml.Builder().body(messageBody).build()

        return MessagingResponse.Builder().message(sms).build()
    }
}