package com.stefano.crudspring.enums.converters;

import java.util.Objects;
import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.stefano.crudspring.enums.StatusEnum;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<StatusEnum, String> {

	@Override
	public String convertToDatabaseColumn(StatusEnum status) {
		if (Objects.isNull(status)) {			
			return null;
		}
		
		return status.getValue();
	}

	@Override
	public StatusEnum convertToEntityAttribute(String value) {
		if (Objects.isNull(value)) {			
			return null;
		}
		
		return Stream.of(StatusEnum.values())
				.filter(s -> s.getValue().equals(value))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
