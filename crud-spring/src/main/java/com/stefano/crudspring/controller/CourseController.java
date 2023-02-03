package com.stefano.crudspring.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stefano.crudspring.model.Course;
import com.stefano.crudspring.service.CourseService;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {

	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping
	public List<Course> list() {
		return courseService.list();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Course> findCourse(@PathVariable @NotNull @Positive Long id) {
		return courseService.findCourse(id).map(record -> ResponseEntity.ok(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Course create(@RequestBody @Valid Course course) {
		return courseService.create(course);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Course> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Course course) {
		return courseService.update(id, course).map(recordFound -> ResponseEntity.ok().body(recordFound)).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
		if (courseService.delete(id)) {
			ResponseEntity.noContent().<Void>build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
