package org.coldis.library.spring.servlet;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Modifiable headers HTTP Servlet request wrapper.
 *
 */
public class ModifiableHeadersHttpServletRequestWrapper extends HttpServletRequestWrapper {

	/**
	 * Constructor.
	 *
	 * @param request Request.
	 */
	public ModifiableHeadersHttpServletRequestWrapper(final HttpServletRequest request) {
		super(request);
	}

	/**
	 * Headers.
	 */
	private final Map<String, String> headers = new HashMap<>();

	/**
	 * Adds a header to the request.
	 *
	 * @param name  Header name.
	 * @param value Header value.
	 */
	public void addHeader(final String name, final String value) {
		this.headers.put(name, value);
	}

	/**
	 * @see javax.servlet.http.HttpServletRequestWrapper#getHeader(java.lang.String)
	 */
	@Override
	public String getHeader(final String name) {
		String headerValue = super.getHeader(name);
		if (this.headers.containsKey(name)) {
			headerValue = this.headers.get(name);
		}
		return headerValue;
	}

	/**
	 * @see javax.servlet.http.HttpServletRequestWrapper#getHeaderNames()
	 */
	@Override
	public Enumeration<String> getHeaderNames() {
		final List<String> names = Collections.list(super.getHeaderNames());
		for (final String name : this.headers.keySet()) {
			names.add(name);
		}
		return Collections.enumeration(names);
	}

	/**
	 * @see javax.servlet.http.HttpServletRequestWrapper#getHeaders(java.lang.String)
	 */
	@Override
	public Enumeration<String> getHeaders(final String name) {
		final List<String> values = Collections.list(super.getHeaders(name));
		if (this.headers.containsKey(name)) {
			values.add(this.headers.get(name));
		}
		return Collections.enumeration(values);
	}

}
