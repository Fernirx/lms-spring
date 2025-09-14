package com.fernirx.lms.common.utils;

import lombok.experimental.UtilityClass;
import static com.fernirx.lms.common.constants.EmailMessages.*;

@UtilityClass
public class EmailFormatter {

    public String passwordReset(String to, String newPassword) {
        return String.format(RESET_PASSWORD_TEMPLATE, to, newPassword);
    }

    public String newAccount(String to, String username, String newPassword) {
        return String.format(NEW_USER_TEMPLATE, to, username, newPassword);
    }
}
