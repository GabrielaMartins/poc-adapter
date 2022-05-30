package com.adapter.sms.configurations

import com.twilio.Twilio
import com.twilio.http.TwilioRestClient
import com.twilio.rest.api.v2010.account.Message
import com.twilio.type.PhoneNumber
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SmsAdapterConfiguration(
    @Value("\${twilio.credentials.account-sid}") private val accountSid: String,
    @Value("\${twilio.credentials.auth-token}") private val authToken: String){
    @Bean
    fun twilioClient() {
        return Twilio.init(accountSid, authToken)
    }
}