package br.com.kme.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;

import jakarta.validation.ConstraintViolationException;


@ControllerAdvice
@RestControllerAdvice
public class EntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(EntityExceptionHandler.class);

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Map<String, Object> handle(HttpMessageNotReadableException ex) {
		logger.error("Erro HTTP de mensagem não legível:", ex);
		return errorMap(HttpStatus.BAD_REQUEST, "O corpo (body) da requisição possui erros ou não existe");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidDefinitionException.class)
	public Map<String, Object> handle(InvalidDefinitionException ide) {
		logger.error("Erro de definição inválida:", ide);
		String atributo = ide.getPath().get(ide.getPath().size() - 1).getFieldName();
		String msgDeErro = "O atributo '" + atributo + "' possui formato inválido";
		return errorMap(HttpStatus.BAD_REQUEST, msgDeErro);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, Object> handle(ConstraintViolationException cve) {
		logger.error("Erro de violação de restrição:", cve);
		List<String> errorMessages = cve.getConstraintViolations().stream()
				.map(constraintViolation -> constraintViolation.getMessage()).collect(Collectors.toList());

		Map<String, Object> response = new HashMap<>();
		response.put("errors", errorMessages);
		return response;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public Map<String, Object> handle(IllegalArgumentException ie) {
		logger.error("Exceção de argumento ilegal:", ie);
		return errorMap(HttpStatus.BAD_REQUEST, ie.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NullPointerException.class)
	public Map<String, Object> handle(NullPointerException npe) {
		logger.error("Exceção de ponteiro nulo:", npe);
		return errorMap(HttpStatus.BAD_REQUEST, npe.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingPathVariableException.class)
	public Map<String, Object> handle(MissingPathVariableException mpve) {
		logger.error("Exceção de caminho de variável ausente:", mpve);
		return errorMap(HttpStatus.BAD_REQUEST, mpve.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public Map<String, Object> handle(MethodArgumentTypeMismatchException matme) {
		logger.error("Exceção de tipo de argumento de método:", matme);
		return errorMap(HttpStatus.BAD_REQUEST, "A URI possui valores inválidos");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Map<String, Object> handle(HttpRequestMethodNotSupportedException hrmnse) {
		logger.error("Exceção de método HTTP não suportado:", hrmnse);
		return errorMap(HttpStatus.BAD_REQUEST, hrmnse.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Map<String, Object> handle(MissingServletRequestParameterException mrpe) {
		logger.error("Exceção de parâmetro de solicitação ausente:", mrpe);
		return errorMap(HttpStatus.BAD_REQUEST, mrpe.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InvalidDataAccessResourceUsageException.class)
	public Map<String, Object> handle(InvalidDataAccessResourceUsageException ie) {
		logger.error("Erro de uso inválido de recurso de acesso a dados:", ie);
		return errorMap(HttpStatus.BAD_REQUEST, "Ocorreu um erro de integração com a Api externa");
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public Map<String, Object> handlePSQLExceptions(DataIntegrityViolationException dve) {
		logger.error("Exceção de violação de integridade de dados:", dve);
		return errorMap(HttpStatus.BAD_REQUEST, "Ocorreu um erro de integridade referencial na base de dados");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, Object> handle(MethodArgumentNotValidException manve) {
		logger.error("Exceção de argumento de método não válido:", manve);
		var erros = manve.getFieldErrors();
		JSONObject body = new JSONObject();
		JSONObject details = new JSONObject();
		erros.forEach((erro) -> {
			details.put("status", HttpStatus.BAD_REQUEST);
			details.put("message", erro.getDefaultMessage());
		});
		body.put("errors", details);
		return body.toMap();
	}

	private Map<String, Object> errorMap(HttpStatus httpStatus, String errorMessage) {

		JSONObject body = new JSONObject();

		JSONObject detalhe = new JSONObject();
		detalhe.put("message", errorMessage);
		detalhe.put("status", httpStatus);

		JSONArray detalhes = new JSONArray();
		detalhes.put(detalhe);

		body.put("errors", detalhes);

		return body.toMap();

	}
}