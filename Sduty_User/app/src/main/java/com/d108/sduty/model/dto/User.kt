package com.d108.sduty.model.dto

import java.io.Serializable

data class User(var id: String, var pass: String, var name: String?, var email: String?): Serializable{
    constructor(id: String, pass: String): this(id, pass, "", "")

}