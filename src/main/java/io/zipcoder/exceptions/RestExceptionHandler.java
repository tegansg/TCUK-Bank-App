package io.zipcoder.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.zipcoder.error.ErrorDetail;
import io.zipcoder.error.ValidationError;

@ControllerAdvice
public class RestExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(InvalidDateFormatException.class)
	public ResponseEntity<?> handleInvalidDateFormatException(InvalidDateFormatException rnfe,
			HttpServletRequest request) {
		ErrorDetail errordetail = new ErrorDetail();
		errordetail.setTitle("Resource not found");
		errordetail.setStatus(404);
		errordetail.setDetail(rnfe.getLocalizedMessage());
		errordetail.setDeveloperMessage(rnfe.getMessage());
		errordetail.setTimeStamp(new Date().getTime());
		return new ResponseEntity<>(errordetail, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException manve, HttpServletRequest request) {
		ErrorDetail errorDetail = new ErrorDetail();

		List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
		for (FieldError fe : fieldErrors) {

			List<ValidationError> validationErrorList = errorDetail.getErrors().get(fe.getField());
			if (validationErrorList == null) {
				validationErrorList = new ArrayList<>();
				errorDetail.getErrors().put(fe.getField(), validationErrorList);
			}
			ValidationError validationError = new ValidationError();
			validationError.setMessage(messageSource.getMessage(fe, null));
			validationErrorList.add(validationError);
		}

		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);

	}

}
