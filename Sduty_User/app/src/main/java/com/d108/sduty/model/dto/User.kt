package com.d108.sduty.model.dto

import com.kakao.sdk.common.util.SdkLogLevel
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.util.*

data class User(
    var id: String,
    var pass: String,
    var name: String,
    var tel: String,
    var email: String,
    var fcm_token: String,
    var regtime: Date?,
    var user_public: Boolean
    ){
    constructor(): this("","","","","","", null,true)
    constructor(id: String, password: String):
            this(id, password, "", "", "")
    constructor(id: String, password: String, name: String, tel: String, email: String):
            this(id, password, name, tel, email, "",null, true)
    constructor(id: String, password: String, name: String, tel: String, email: String, fcm_token: String):
            this(id, password, name, tel, email, fcm_token,null, true)
    
}