package com.freeForm.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull
    @Size(max = 30, message = "Name must be less than 30 characters")
    private String name;
    @NotNull
    @Min(value = 1, message = "Effort must be greater than 0")
    @Max(value = 10, message = "Effort must be less than 10")
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
