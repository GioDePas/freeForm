package com.freeForm.service;

import com.freeForm.dto.AttachmentDto;
import com.freeForm.entity.Attachment;
import com.freeForm.mapper.AttachmentMapper;
import com.freeForm.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;

    @Transactional(readOnly = true)
    public List<AttachmentDto> getAllAttachments() {
        List<Attachment> attachments = attachmentRepository.findAll();
        return AttachmentMapper.mapAttachmentsToDto(attachments);
    }
}
