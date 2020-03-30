package br.com.vivo.waynemobile.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.vivo.waynemobile.exception.PartnerHTTPException;
import br.com.vivo.waynemobile.model.CreateResponse;
import br.com.vivo.waynemobile.model.User;
import br.com.vivo.waynemobile.util.JSONUtils;

@Service
public class UserService {

	@Value("${actionrecorder}")
	private String endpoint;

	private static final HttpClient HTTP_CLIENT = new HttpClient(new MultiThreadedHttpConnectionManager());

	private static final ObjectMapper mapper = new ObjectMapper();

	private static StringRequestEntity getRequestEntity(String requestBody) throws UnsupportedEncodingException {
			return new StringRequestEntity(requestBody, "application/json", "UTF-8");
	}

	private String getCreateURI() {
		return this.endpoint.concat("/user");				
	}
	
	private String getDeleteURI(Long userId) {
		return this.endpoint.concat("/user/" + userId);				
	}
	
	public CreateResponse createUser(User user) throws IOException {
		PostMethod postMethod = new PostMethod(getCreateURI());
		postMethod.setRequestEntity(getRequestEntity(JSONUtils.toJSON(user)));
		HTTP_CLIENT.executeMethod(postMethod);

		if (postMethod.getStatusCode() != 200) {
			throw new PartnerHTTPException(HttpStatus.valueOf(postMethod.getStatusCode()),
					postMethod.getResponseBodyAsString());
		}
		return mapper.readValue(postMethod.getResponseBodyAsString(), CreateResponse.class);
	}
	
	public void deleteUser(Long userId) throws IOException {
		DeleteMethod deleteMethod = new DeleteMethod(getDeleteURI(userId));
		HTTP_CLIENT.executeMethod(deleteMethod);

		if (deleteMethod.getStatusCode() != 200) {
			throw new PartnerHTTPException(HttpStatus.valueOf(deleteMethod.getStatusCode()),
					deleteMethod.getResponseBodyAsString());
		}
	}

}
