package com.stefano.crudspring.enums.converters;

import java.util.Objects;
import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.stefano.crudspring.enums.CategoryEnum;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<CategoryEnum, String> {

	@Override
	public String convertToDatabaseColumn(CategoryEnum category) {
		if (Objects.isNull(category)) {			
			return null;
		}
		
		return category.getValue();
	}

	@Override
	public CategoryEnum convertToEntityAttribute(String value) {
		if (Objects.isNull(value)) {			
			return null;
		}
		
		return Stream.of(CategoryEnum.values())
				.filter(c -> c.getValue().equals(value))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
