package com.freeForm.attachment.mapper;

import com.freeForm.attachment.dto.AttachmentDto;
import com.freeForm.attachment.dao.Attachment;

import java.util.List;
import java.util.stream.Collectors;

public class AttachmentMapper {

    public static AttachmentDto mapAttachmentToDto(Attachment attachment) {
            AttachmentDto attachmentDto = new AttachmentDto();
            attachmentDto.setId(attachment.getId());
            attachmentDto.setData(attachment.getData());
            attachmentDto.setName(attachment.getName());
            attachmentDto.setContentType(attachment.getContentType());
            attachmentDto.setTaskId(attachment.getTask().getId());
            return attachmentDto;
    }

    public static List<AttachmentDto> mapAttachmentsToDto(List<Attachment> attachments) {
        return attachments.stream().map(AttachmentMapper::mapAttachmentToDto).collect(Collectors.toList());
    }

}