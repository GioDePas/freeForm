package com.freeForm.attachment.web;

import com.freeForm.attachment.dto.AttachmentDto;
import com.freeForm.attachment.service.AttachmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    private final static String BEARER = "bearerAuth";

    @GetMapping
    @SecurityRequirement(name = BEARER)
    public List<AttachmentDto> getAllAttachments() {
        return attachmentService.getAllAttachments();
    }
}
