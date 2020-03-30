package br.com.vivo.waynemobile.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.validation.Valid;

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
import br.com.vivo.waynemobile.model.Action;
import br.com.vivo.waynemobile.model.CreateResponse;
import br.com.vivo.waynemobile.util.JSONUtils;

@Service
public class CDRService {
	
	@Value("${actionrecorder}")
	private String endpoint;
	
	private static final HttpClient HTTP_CLIENT = new HttpClient(new MultiThreadedHttpConnectionManager());

	private static final ObjectMapper mapper = new ObjectMapper();

	private static StringRequestEntity getRequestEntity(String requestBody) throws UnsupportedEncodingException {
			return new StringRequestEntity(requestBody, "application/json", "UTF-8");
	}
	
	private String getCreateURI(Long userId, Long lineId) {
		return this.endpoint + "/user/" + userId + "/line/" + lineId + "/action";
	}
	
	private String getDeleteURI(Long userId , Long lineId , Long actionId) {
		return this.endpoint + "/user/" + userId + "/line/" + lineId + "/action/" + actionId;
	}

	
	public CreateResponse createAction(Long userId, Long lineId, @Valid Action action) throws IOException {
		PostMethod postMethod = new PostMethod(getCreateURI(userId, lineId));
		postMethod.setRequestEntity(getRequestEntity(JSONUtils.toJSON(action)));
		HTTP_CLIENT.executeMethod(postMethod);

		if (postMethod.getStatusCode() != 200) {
			throw new PartnerHTTPException(HttpStatus.valueOf(postMethod.getStatusCode()),
					postMethod.getResponseBodyAsString());
		}
		return mapper.readValue(postMethod.getResponseBodyAsString(), CreateResponse.class);
	
	}

	public void deleteAction(Long userId, Long lineId, Long actionId) throws IOException {
		DeleteMethod deleteMethod = new DeleteMethod(getDeleteURI(userId, lineId, actionId));
		HTTP_CLIENT.executeMethod(deleteMethod);

		if (deleteMethod.getStatusCode() != 200) {
			throw new PartnerHTTPException(HttpStatus.valueOf(deleteMethod.getStatusCode()),
					deleteMethod.getResponseBodyAsString());
		}
		
	}

}
