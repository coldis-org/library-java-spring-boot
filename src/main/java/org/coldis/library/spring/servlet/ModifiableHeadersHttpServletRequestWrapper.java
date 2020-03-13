package org.coldis.library.spring.servlet;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

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
	private MultiValuedMap<String, String> headers;

	/**
	 * Gets the headers.
	 *
	 * @return The headers.
	 */
	private MultiValuedMap<String, String> getHeaders() {
		// If the map has not been initialized.
		if (this.headers == null) {
			// Initializes the headers.
			this.headers = new ArrayListValuedHashMap<>();
			// For each header.
			while (this.getHeaderNames().hasMoreElements()) {
				final String headerName = this.getHeaderNames().nextElement();
				// For each header with the name.
				final Enumeration<String> headersForName = this.getHeaders(headerName);
				while (headersForName.hasMoreElements()) {
					final String headerValue = headersForName.nextElement();
					// Adds the header to the map.
					this.headers.put(headerName.toLowerCase(), headerValue);
				}
			}
		}
		// Returns the headers.
		return this.headers;
	}

	/**
	 * @see javax.servlet.http.HttpServletRequestWrapper#getHeader(java.lang.String)
	 */
	@Override
	public String getHeader(final String name) {
		final Collection<String> headers = this.getHeaders().get(name.toLowerCase());
		return (CollectionUtils.isEmpty(headers) ? null : headers.iterator().next());
	}

	/**
	 * @see javax.servlet.http.HttpServletRequestWrapper#getHeaderNames()
	 */
	@Override
	public Enumeration<String> getHeaderNames() {
		return Collections.enumeration(this.getHeaders().asMap().keySet());
	}

	/**
	 * @see javax.servlet.http.HttpServletRequestWrapper#getHeaders(java.lang.String)
	 */
	@Override
	public Enumeration<String> getHeaders(final String name) {
		return Collections.enumeration(this.getHeaders().get(name.toLowerCase()));
	}

	/**
	 * Adds new headers.
	 *
	 * @param name  Name.
	 * @param value Value.
	 */
	public void addHeaders(final String name, final String value) {
		this.getHeaders().put(name.toLowerCase(), value);
	}

	/**
	 * Remove headers.
	 *
	 * @param name Name.
	 */
	public void removeHeaders(final String name) {
		this.getHeaders().remove(name.toLowerCase());
	}

}
