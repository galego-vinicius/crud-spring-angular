package com.vinicius.dto.response;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CourseDTO {
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "category", length = 20, nullable = false)
    private String category;
}
