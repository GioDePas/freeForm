package com.freeForm.attachment.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.freeForm.task.dao.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private byte[] data;
    private String name;
    private String contentType;
    @OneToOne(mappedBy = "attachment")
    @JsonBackReference
    private Task task;
}
