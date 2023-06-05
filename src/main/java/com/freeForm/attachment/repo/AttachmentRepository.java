package com.freeForm.attachment.repo;

import com.freeForm.attachment.dao.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
