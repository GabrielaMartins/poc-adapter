package com.adapter.sms.models

import com.fasterxml.jackson.annotation.JsonProperty

data class SmsAdapterMessageInput(
    val body: String,
    val phoneReceiver: String,
    val phoneSender: String
)
