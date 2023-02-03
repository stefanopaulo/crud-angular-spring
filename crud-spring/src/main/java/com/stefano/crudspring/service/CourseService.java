package com.stefano.crudspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
	
	public Optional<Course> findCourse(Long id) {
		return courseRepository.findById(id);
	}
	
	public Course create(Course course) {
		return courseRepository.save(course);
	}
	
	public Optional<Course> update(Long id, Course course) {
		return courseRepository.findById(id).map(recordFound -> {
			recordFound.setName(course.getName());
			recordFound.setCategory(course.getCategory());
			return courseRepository.save(recordFound);
		});
	}
	
	public boolean delete(Long id) {
		return courseRepository.findById(id)
				.map(recordFound -> {
					courseRepository.deleteById(id);
					return true;
				})
				.orElse(false);
	}

}