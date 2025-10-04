package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.constants.ApiMessages;
import com.fernirx.lms.common.enums.ErrorCode;
import com.fernirx.lms.common.utils.ApiFormatter;
import lombok.Getter;

@Getter
public class AccountDisabledException extends SecurityException {
    private final String username;

    public AccountDisabledException(String username) {
        super(ErrorCode.ACCOUNT_DISABLED, ApiMessages.ACCOUNT_DISABLED);
        this.username = username;
    }
}