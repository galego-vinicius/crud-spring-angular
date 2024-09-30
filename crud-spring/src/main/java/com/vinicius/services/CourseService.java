package com.vinicius.services;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinicius.dto.request.CreateCourseDTO;
import com.vinicius.dto.response.CourseDTO;
import com.vinicius.entities.Course;
import com.vinicius.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final ObjectMapper objectMapper;
    private final CourseRepository courseRepository;

    public Page<CourseDTO> getCourseList (Pageable pageable) throws Exception{
        Page<Course> entity= courseRepository.findAll(pageable);
        return entity.map(this::returnDTO);
    }

    public Optional<CourseDTO> getCourseById (Long id) {
        return Optional.ofNullable(objectMapper.convertValue(courseRepository.findById(id).orElseThrow(),
                CourseDTO.class));
    }

    public CourseDTO createCourse (CreateCourseDTO newCourse) {
        return objectMapper.convertValue(
                courseRepository.save(objectMapper.convertValue(newCourse, Course.class))
                , CourseDTO.class);
    }

    public CourseDTO updateCourse (CreateCourseDTO newCourse, Long id) throws Exception {
        Course course = objectMapper.convertValue(newCourse, Course.class);
        courseRepository.findById(id)
                .map(oldCourse -> {
                    oldCourse.setName(course.getName());
                    oldCourse.setCategory(course.getCategory());
                    courseRepository.save(oldCourse);
                    return objectMapper.convertValue(oldCourse, CourseDTO.class);
                }).orElseThrow(() -> new Exception("Curso não encontrado!"));
        return null;
    }

    public void deleteCourse (Long id){
        courseRepository.deleteById(id);
    }

    public CourseDTO returnDTO(Course course) {
        return objectMapper.convertValue(course, CourseDTO.class);
    }
}
