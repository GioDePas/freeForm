package com.freeForm.mapper;

import com.freeForm.dto.AttachmentDto;
import com.freeForm.entity.Attachment;

import java.util.List;
import java.util.stream.Collectors;

public class AttachmentMapper {
    public static List<AttachmentDto> mapAttachmentsToDtos(List<Attachment> attachments) {
        return attachments.stream().map(attachment -> {
            AttachmentDto attachmentDto = new AttachmentDto();
            attachmentDto.setId(attachment.getId());
            attachmentDto.setData(attachment.getData());
            attachmentDto.setName(attachment.getName());
            attachmentDto.setContentType(attachment.getContentType());
            attachmentDto.setTaskId(attachment.getTask().getId());
            return attachmentDto;
        }).collect(Collectors.toList());
    }
}