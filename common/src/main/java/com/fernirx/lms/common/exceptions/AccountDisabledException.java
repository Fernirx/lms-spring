package com.fernirx.lms.common.exceptions;

import com.fernirx.lms.common.enums.ErrorCode;
import com.fernirx.lms.common.utils.ApiFormatter;
import lombok.Getter;

@Getter
public class AccountDisabledException extends SecurityException {
    private final String username;

    public AccountDisabledException(String username) {
        super(ErrorCode.ACCOUNT_DISABLED, ApiFormatter.accountDisabled(username));
        this.username = username;
    }
}