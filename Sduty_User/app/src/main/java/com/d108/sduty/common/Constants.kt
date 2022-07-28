package com.d108.sduty.common

const val SERVER_URL = "http://d108.kro.kr:8090" // AWS

const val SOLAPI_API_KEY = "NCSOR6GTURVMA4XT"
const val SOLAPI_API_SECRET_KEY = "BENC5C7DNBGXLVJLZLLTN5WB5FSJCDOL"
const val SOLAPI_DOMAIN = "https://api.solapi.com"

const val SENDBIRD_APP_ID = "A5324725-2AC3-4971-AF99-D0CB863D0717"
const val UNKNOWN_SENDBIRD_ERROR = "unknown sendbird error"
const val EXTRA_ROOM_ID = "com.sendbird.calls.quickstart.ROOM_ID"
const val EXTRA_IS_NEWLY_CREATED = "com.sendbird.calls.quickstart.IS_NEWLY_CREATED"
const val EXTRA_ENTER_ERROR_CODE = "com.sendbird.calls.quickstart.ENTER_ERROR_CODE"
const val EXTRA_ENTER_ERROR_MESSAGE = "com.sendbird.calls.quickstart.ENTER_ERROR_MESSAGE"

const val REQUEST_CODE_PREVIEW = 0
const val REQUEST_CODE_PERMISSIONS = 1
const val REQUEST_CODE_AUDIO_RECORD_PERMISSION = 2
const val RESULT_ENTER_FAIL = 3

const val COMMON_JOIN = 0
const val KAKAO_JOIN = 1
const val NAVER_JOIN = 2

const val JOB_BUTTON = 0
const val INTEREST_BUTTON = 1
const val BIRTH_BUTTON = 2


enum class PAGE{
    LOGIN, JOIN, PREVIEW
}