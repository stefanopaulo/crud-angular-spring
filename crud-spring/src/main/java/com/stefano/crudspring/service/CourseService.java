package com.stefano.crudspring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stefano.crudspring.exception.RecordNotFoundException;
import com.stefano.crudspring.model.Course;
import com.stefano.crudspring.repository.CourseRepository;

@Service
public class CourseService {
	
	private final CourseRepository courseRepository;
	
	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public List<Course> list() {
		return courseRepository.findAll();
	}
	
	public Course findCourse(Long id) {
		return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
	}
	
	public Course create(Course course) {
		return courseRepository.save(course);
	}
	
	public Course update(Long id, Course course) {
		return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
	}
	
	public void delete(Long id) {
		courseRepository.delete(courseRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(id)));
	}

}
