package com.freeForm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer effort;
    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "attachment_id",
            referencedColumnName = "id"
    )
    private Attachment attachment;
}
