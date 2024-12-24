package com.ms.auth.constant

import com.ms.auth.dto.response.ResultInfo


object ResultInfoConstant {
    val SUCCESS = ResultInfo("000001", "SUCCESS", "Success", "S")
    val SYSTEM_ERROR = ResultInfo("000002", "SYSTEM_ERROR", "System Error", "F")

    val BAD_REQUEST: ResultInfo = ResultInfo("000003", "BAD_REQUEST", "Bad Request", "F")

    val REQ_FIELD_VALIDATION_ERROR: ResultInfo =
        ResultInfo("000004", "REQ_FIELD_VALIDATION_ERROR", "", "F")

    val UNAUTHORIZED_ACCESS_IN_APPLICATIONS: ResultInfo =
        ResultInfo("000005", "UNAUTHORIZED_ACCESS_IN_APPLICATIONS", "Access denied!", "F")

    val SOMETHING_WENT_WRONG: ResultInfo =
        ResultInfo("000006", "SOMETHING_WENT_WRONG", "Something went wrong!", "F")
}