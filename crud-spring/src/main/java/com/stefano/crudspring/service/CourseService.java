package com.stefano.crudspring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.stefano.crudspring.exception.RecordNotFoundException;
import com.stefano.crudspring.model.dto.CourseDTO;
import com.stefano.crudspring.model.dto.CourseMapper;
import com.stefano.crudspring.repository.CourseRepository;

@Service
public class CourseService {
	
	private final CourseRepository courseRepository;
	private final CourseMapper courseMapper;
	
	public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
		this.courseRepository = courseRepository;
		this.courseMapper = courseMapper;
	}

	public List<CourseDTO> list() {
		return courseRepository.findAll()
				.stream()
				.map(courseMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	public CourseDTO findCourse(Long id) {
		return courseRepository.findById(id).map(courseMapper::toDTO).orElseThrow(() -> new RecordNotFoundException(id));
	}
	
	public CourseDTO create(CourseDTO courseDTO) {
		return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(courseDTO)));
	}
	
	public CourseDTO update(Long id, CourseDTO courseDTO) {
		return courseRepository.findById(id)
				.map(recordFound -> {
					recordFound.setName(courseDTO.getName());
					recordFound.setCategory(courseMapper.convertCategoryValue(courseDTO.getCategory()));
					return courseMapper.toDTO(courseRepository.save(recordFound));
				})
				.orElseThrow(() -> new RecordNotFoundException(id));
	}
	
	public void delete(Long id) {
		courseRepository.delete(courseRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(id)));
	}

}
