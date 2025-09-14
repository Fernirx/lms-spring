package com.fernirx.lms.common.constants;

public final class EmailMessages {

    public static final String RESET_PASSWORD_TEMPLATE = """
            Hello %s,

            Your account password has been reset.
            Your new temporary password is: %s

            Please log in and change your password as soon as possible.

            Regards,
            LMS Team
            """;

    public static final String NEW_USER_TEMPLATE = """
            Hello %s,

            Your LMS account has been created successfully.
            Username: %s
            Temporary password: %s

            Please log in and change your password after your first login.

            Regards,
            LMS Team
            """;

    private EmailMessages() {
        throw new UnsupportedOperationException("This is a constants class and cannot be instantiated");
    }
}
