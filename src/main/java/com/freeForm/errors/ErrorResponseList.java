package com.freeForm.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseList {
    private List<ErrorResponse> errors;
    private String path;
}
