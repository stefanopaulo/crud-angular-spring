package com.stefano.crudspring.exception;

public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RecordNotFoundException(Long id) {
		super("Registo não encontrado com id: " + id);
	}

}
