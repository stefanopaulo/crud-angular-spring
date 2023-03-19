package com.stefano.crudspring.model.dto;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.stefano.crudspring.enums.CategoryEnum;
import com.stefano.crudspring.model.Course;

@Component
public class CourseMapper {

	public CourseDTO toDTO(Course course) {
		if (Objects.isNull(course)) {
			return null;
		}
		
		return new CourseDTO(course.getId(), course.getName(), "Front-end");
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
		course.setCategory(CategoryEnum.FRONTEND);
		course.setStatus("Ativo");

		return course; 
	}
	
}
