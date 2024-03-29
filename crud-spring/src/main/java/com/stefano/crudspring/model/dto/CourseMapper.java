package com.stefano.crudspring.model.dto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.stefano.crudspring.enums.CategoryEnum;
import com.stefano.crudspring.model.Course;

@Component
public class CourseMapper {

	public CourseDTO toDTO(Course course) {
		if (Objects.isNull(course)) {
			return null;
		}
		
		List<LessonDTO> lessons = course.getLessons()
				.stream()
				.map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
				.collect(Collectors.toList());
		
		return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), lessons);
	}
	
	public Course toEntity(CourseDTO courseDTO) {
		if (Objects.isNull(courseDTO)) {
			return null;
		}
		
		Course course = new Course();

		if (courseDTO.getId() != null) {
			course.setId(courseDTO.getId());
		}
		
		course.setName(courseDTO.getName());
		course.setCategory(convertCategoryValue(courseDTO.getCategory()));

		return course; 
	}
	
	public CategoryEnum convertCategoryValue(String value) {
		if (Objects.isNull(value)) {
			return null;
		}
		
		return switch (value) {
			case "Front-end" -> CategoryEnum.FRONTEND;
			case "Back-end" -> CategoryEnum.BACKEND;
			default -> throw new IllegalArgumentException("Categoria inválida: " + value);
		};
	}
	
}
