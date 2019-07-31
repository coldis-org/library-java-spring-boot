package org.coldis.library.test.spring.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.coldis.library.exception.BusinessException;
import org.coldis.library.exception.IntegrationException;
import org.coldis.library.service.client.GenericRestServiceClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringValueResolver;

/**
  * Exception handler service.
  */
@Service
public class ExceptionHandlerServiceClient implements EmbeddedValueResolverAware {
	
	/**
	 * Value resolver.
	 */
	private StringValueResolver valueResolver;

	/**
	 * JMS template.
	 */
	@Autowired(required = false)
	private JmsTemplate jmsTemplate;
	
	/**
	 * Service client.
	 */
	@Autowired
	@Qualifier(value = "restServiceClient")	private GenericRestServiceClient serviceClient;

	/**
	 * No arguments constructor.
	 */
	public ExceptionHandlerServiceClient() {
		super();
	}
	
	/**
	 * @see org.springframework.context.EmbeddedValueResolverAware#
	 *      setEmbeddedValueResolver(org.springframework.util.StringValueResolver)
	 */
	@Override
	public void setEmbeddedValueResolver(final StringValueResolver resolver) {
		valueResolver = resolver;
	}

	/**
	 * Test service.

 @param  code              Message code.
 @param  parameters        Parameters.
 @throws BusinessException Exception.
  */
	public void businessExceptionService(
			java.lang.String code,
			java.lang.Object[] parameters) throws BusinessException {
		// Operation parameters.
		StringBuilder path = new StringBuilder(this.valueResolver
				.resolveStringValue("http://localhost:29000/exception/business?"));
		final HttpMethod method = HttpMethod.POST;
		final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		Object body = null;
		final Map<String, Object> uriParameters = new HashMap<>();
		final MultiValueMap<String, Object> partParameters = new LinkedMultiValueMap<>();
		final ParameterizedTypeReference<?> returnType =
				new ParameterizedTypeReference<Void>() {};
		// Adds the content type headers.
		GenericRestServiceClient.addContentTypeHeaders(headers,
				MediaType.APPLICATION_JSON_UTF8_VALUE);
		// Adds the URI parameter to the map.
		uriParameters.put("code", code);
		path.append("code={code}&");
		// Adds the URI parameter to the map.
		uriParameters.put("parameters", parameters);
		path.append("parameters={parameters}&");
		// Executes the operation and returns the response.
		this.serviceClient.executeOperation(path.toString(), method, headers,
				partParameters.isEmpty() ? body : partParameters,
				uriParameters, returnType);
	}
	
	/**
	 * Test service.

 @param  code                 Message code.
 @param  parameters           Parameters.
 @throws IntegrationException Exception.
  */
	public void integrationExceptionService(
			java.lang.String code,
			java.lang.Object[] parameters) throws BusinessException {
		// Operation parameters.
		StringBuilder path = new StringBuilder(this.valueResolver
				.resolveStringValue("http://localhost:29000/exception/integration?"));
		final HttpMethod method = HttpMethod.POST;
		final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		Object body = null;
		final Map<String, Object> uriParameters = new HashMap<>();
		final MultiValueMap<String, Object> partParameters = new LinkedMultiValueMap<>();
		final ParameterizedTypeReference<?> returnType =
				new ParameterizedTypeReference<Void>() {};
		// Adds the content type headers.
		GenericRestServiceClient.addContentTypeHeaders(headers,
				MediaType.APPLICATION_JSON_UTF8_VALUE);
		// Adds the URI parameter to the map.
		uriParameters.put("code", code);
		path.append("code={code}&");
		// Adds the URI parameter to the map.
		uriParameters.put("parameters", parameters);
		path.append("parameters={parameters}&");
		// Executes the operation and returns the response.
		this.serviceClient.executeOperation(path.toString(), method, headers,
				partParameters.isEmpty() ? body : partParameters,
				uriParameters, returnType);
	}
	
	/**
	 * Test service.

 @param object Test object.
  */
	public void constraintViolationExceptionService(
			@javax.validation.Valid org.coldis.library.test.spring.exception.TestClass object) throws BusinessException {
		// Operation parameters.
		StringBuilder path = new StringBuilder(this.valueResolver
				.resolveStringValue("http://localhost:29000/exception/constraint-violation?"));
		final HttpMethod method = HttpMethod.POST;
		final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		Object body = null;
		final Map<String, Object> uriParameters = new HashMap<>();
		final MultiValueMap<String, Object> partParameters = new LinkedMultiValueMap<>();
		final ParameterizedTypeReference<?> returnType =
				new ParameterizedTypeReference<Void>() {};
		// Adds the content type headers.
		GenericRestServiceClient.addContentTypeHeaders(headers,
				MediaType.APPLICATION_JSON_UTF8_VALUE);
		// Sets the operation body.
		body = object;
		// Executes the operation and returns the response.
		this.serviceClient.executeOperation(path.toString(), method, headers,
				partParameters.isEmpty() ? body : partParameters,
				uriParameters, returnType);
	}
	

}