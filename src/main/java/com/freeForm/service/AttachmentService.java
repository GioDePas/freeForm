package com.freeForm.service;

import com.freeForm.entity.Attachment;
import com.freeForm.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;

    public List<Attachment> getAllAttachments() {
        return attachmentRepository.findAll();
    }
}
