package com.freeForm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentDto {
    private Long id;
    private byte[] data;
    private String name;
    private String contentType;
    private Long taskId;
}
