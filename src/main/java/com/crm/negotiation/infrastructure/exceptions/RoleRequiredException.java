package com.crm.negotiation.infrastructure.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class RoleRequiredException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	


  public RoleRequiredException() {
      super();
  }

  public RoleRequiredException(String message) {
    super(message);
  }



}
