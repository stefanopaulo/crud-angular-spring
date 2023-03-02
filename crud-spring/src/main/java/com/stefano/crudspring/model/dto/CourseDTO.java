package com.stefano.crudspring.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseDTO {
	
	@JsonProperty("_id")
	private Long id;

    @NotBlank
    @NotNull
    @Length(min = 5, max = 200)
    private String name;

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "Front-end|Back-end")
    private String category;

}
