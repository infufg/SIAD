package br.ufg.inf.integracao.service;

import br.ufg.inf.integracao.exception.DuplicateUserException;
import br.ufg.inf.integracao.util.RegisterUserFromJSON;
import org.apache.http.*;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.nio.protocol.*;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Locale;

public class SIADRegisterUserHandler implements HttpAsyncRequestHandler<HttpRequest> {
	public HttpAsyncRequestConsumer<HttpRequest> processRequest(final HttpRequest request, final HttpContext context) {
		return new BasicAsyncRequestConsumer();
	}

	public void handle(final HttpRequest request, final HttpAsyncExchange httpexchange, final HttpContext context) throws HttpException, IOException {
		HttpResponse response = httpexchange.getResponse();

		String method = request.getRequestLine().getMethod().toUpperCase(Locale.ENGLISH);
		if (!method.equals("POST")) {
			throw new MethodNotSupportedException(method + " method not supported");
		}

		String jsonString = EntityUtils.toString(((BasicHttpEntityEnclosingRequest) request).getEntity());
		NStringEntity entity = null;

		try {
			RegisterUserFromJSON.registerUserFromJSON(jsonString);
			response.setStatusCode(HttpStatus.SC_OK);
			entity = new NStringEntity(jsonString, ContentType.APPLICATION_JSON);
		} catch (DuplicateUserException e) {
			response.setStatusCode(HttpStatus.SC_BAD_REQUEST);
			entity = new NStringEntity(e.getMessage(), ContentType.DEFAULT_TEXT);
		} finally {
			response.setEntity(entity);
			httpexchange.submitResponse(new BasicAsyncResponseProducer(response));
		}
	}

}