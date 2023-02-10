package com.freeForm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull
    @Size(min = 2, max = 30)
    private String firstname;
    @NotNull
    @Size(min = 2, max = 30)
    private String lastname;
    @NotNull
    private String email;
    @NotNull
    private int age;
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
