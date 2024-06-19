package com.vinicius.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCourse;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "category", length = 20, nullable = false)
    private String category;
}
