package com.vinicius.dto.request;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCourseDTO {

    @NotNull
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @NotNull
    @Column(name = "category", length = 20, nullable = false)
    private String category;
}
