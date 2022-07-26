package com.d108.sduty.model.dto

import java.util.*

data class Profile(var profile_user_seq: Int,
                   var profile_nickname: String,
                   var profile_birth: Date,
                   var profile_public_birth: Int,
                   var profile_short_introduce: String,
                   var profile_image: String,
                   var profile_job: String,
                   var profile_public_job: Int,
                   var profile_interest: String,
                   var profile_public_interest: Int,
                   var profile_followers: Int,
                   var profile_followees: Int,
                   var profile_main_achievement_seq: Int,
                   var profile_block_action: Int,
                   var profile_warning: Int,
                   var is_prohibited_user: Int
) {
    // 프로필 등록용
    constructor(nickname: String, birth: Date, public_birth: Int, short_introduce: String, image: String, job: String, public_job: Int, interest: String, public_interest: Int, main_achievement_seq: Int)
    :this(0, nickname, birth, public_birth, short_introduce, image, job, public_job, interest, public_interest, 0, 0, main_achievement_seq, 0, 0, 0)
}