package com.stefano.crudspring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stefano.crudspring.enums.CategoryEnum;
import com.stefano.crudspring.enums.StatusEnum;
import com.stefano.crudspring.enums.converters.CategoryConverter;
import com.stefano.crudspring.enums.converters.StatusConverter;

import lombok.Data;

@Data
@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 5, max = 200)
    @Column(length = 200, nullable = false)
    private String name;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = CategoryConverter.class)
    private CategoryEnum category;
    
    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private StatusEnum status = StatusEnum.ATIVO;
    
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
	private List<Lesson> lessons = new ArrayList<>();
}
