package com.d108.sduty.model.dto

import com.kakao.sdk.common.util.SdkLogLevel
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.util.*

data class User(
    var user_seq: Int,
    var user_id: String,
    var user_pass: String,
    var user_name: String,
    var user_tel: String,
    var user_email: String,
    var user_fcm_token: String,
    var user_regtime: Date?,
    var user_public: Int
){
    constructor(): this(0,"","","","","","", null,1)
    constructor(id: String, password: String):
            this(id, password, "", "", "")
    constructor(id: String, password: String, name: String, tel: String, email: String):
            this(0, id, password, name, tel, email, "",null, 1)
    constructor(id: String, password: String, name: String, tel: String, email: String, fcm_token: String):
            this(0,id, password, name, tel, email, fcm_token,null, 1)

}
