package com.freeForm.attachment.service;

import com.freeForm.attachment.dto.AttachmentDto;
import com.freeForm.attachment.dao.Attachment;
import com.freeForm.attachment.mapper.AttachmentMapper;
import com.freeForm.attachment.repo.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;

    public List<AttachmentDto> getAllAttachments() {
        List<Attachment> attachments = attachmentRepository.findAll();
        return AttachmentMapper.mapAttachmentsToDto(attachments);
    }
}
