package com.fernirx.lms.common.utils;

import com.fernirx.lms.common.dtos.responses.ErrorDetail;
import com.fernirx.lms.common.enums.ErrorCode;
import lombok.experimental.UtilityClass;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class ErrorResolver {

    public Optional<ErrorCode> getHighestPriorityErrorCode(List<ErrorDetail> details) {
        return details.stream()
                .map(ErrorResolver::mapDetailToErrorCode)
                .flatMap(Optional::stream)
                .max(Comparator.comparingInt(ErrorCode::getLevel));
    }

    private Optional<ErrorCode> mapDetailToErrorCode(ErrorDetail detail) {
        return Optional.ofNullable(detail.getCode());
    }
}
