package com.freeForm.controller;

import com.freeForm.dto.AttachmentDto;
import com.freeForm.entity.Attachment;
import com.freeForm.mapper.AttachmentMapper;
import com.freeForm.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attachments")
public class AttachmentController {
    private final AttachmentService attachmentService;

    @GetMapping
    public List<AttachmentDto> getAllAttachments() {
        List<Attachment> attachments = attachmentService.getAllAttachments();
        return AttachmentMapper.mapAttachmentsToDtos(attachments);
    }
}
