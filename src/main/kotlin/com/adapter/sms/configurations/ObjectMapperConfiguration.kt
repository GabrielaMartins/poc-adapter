package com.adapter.sms.configurations

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ObjectMapperConfiguration {
    @Bean
    fun objectMapper(): ObjectMapper {
        return jacksonObjectMapper().apply {
            propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
            disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .registerModule(JavaTimeModule())
        }
    }
}