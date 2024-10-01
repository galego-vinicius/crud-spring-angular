package com.vinicius.controllers;

import com.vinicius.dto.request.CreateCourseDTO;
import com.vinicius.dto.response.CourseDTO;
import com.vinicius.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping(path = "/courses")
@Component
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<Page<CourseDTO>> getCourseList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) throws Exception{
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(courseService.getCourseList(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CourseDTO>> getCourseById(@PathVariable Long id){
        return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CreateCourseDTO course){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseService.createCourse(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> editCourse (@RequestBody CreateCourseDTO newCourse,
                                                        @PathVariable Long id) throws Exception {
        return new ResponseEntity<>(courseService.updateCourse(newCourse, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}
