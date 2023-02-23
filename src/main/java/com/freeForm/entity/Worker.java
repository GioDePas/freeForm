package com.freeForm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Integer age;
    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            joinColumns = @JoinColumn(
                    name = "task_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "worker_id",
                    referencedColumnName = "id"
            )
    )
    private List<Task> tasks;
}
