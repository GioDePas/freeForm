package com.freeForm.controller;

import com.freeForm.entity.Attachment;
import com.freeForm.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attachments")
public class AttachmentController {
    private final AttachmentService attachmentService;
    //getAttachmentById
    //getAllAttachments
}
