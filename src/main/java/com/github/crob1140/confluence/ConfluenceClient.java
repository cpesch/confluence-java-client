package com.github.crob1140.confluence;

import com.github.crob1140.confluence.auth.AuthMethod;
import com.github.crob1140.confluence.content.Content;
import com.github.crob1140.confluence.errors.ConfluenceRequestException;
import com.github.crob1140.confluence.errors.ErrorResponse;
import com.github.crob1140.confluence.requests.ConfluenceRequest;
import com.github.crob1140.confluence.requests.CreateContentRequest;
import com.github.crob1140.confluence.requests.GetContentRequest;
import com.github.crob1140.confluence.requests.GetContentResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This class sends requests to a Confluence Cloud server
 */
public class ConfluenceClient {

  private AuthMethod authMethod;
  private WebTarget wikiTarget;

  /**
   * This constructor creates a client that can send requests to the Confluence Cloud server located
   * at the given target.
   *
   * The requests generated by a client created through this constructor do not include any
   * authorisation, and can therefore only be used to access publicly available content.
   *
   * @param wikiTarget The resource target pointing to the location of the Confluence Cloud server.
   */
  public ConfluenceClient(WebTarget wikiTarget) {
    this.wikiTarget = wikiTarget;
  }

  /**
   * This constructor creates a client that can send requests to the Confluence Cloud server located
   * at the given target.
   *
   * The requests generated by a client created through this constructor will use the credentials
   * defined by the given {@link AuthMethod}, and are therefore subject to the permissions given to
   * the user that corresponds to these credentials.
   *
   * @param wikiTarget The resource target pointing to the location of the Confluence Cloud server.
   * @param authMethod The authorization method to use for all requests generated by this client.
   */
  public ConfluenceClient(WebTarget wikiTarget, AuthMethod authMethod) {
    this(wikiTarget);
    this.authMethod = authMethod;
  }

  /**
   * This method sends a request to the Confluence Cloud server to retrieve content content matching
   * the conditions set in the given {@link GetContentRequest}
   *
   * @param request The request defining the conditions for the Content that should be returned.
   */
  public List<Content> getContent(GetContentRequest request) throws ConfluenceRequestException {
    return ((GetContentResponse) performRequest(request)).getResults();
  }

  /**
   * This method sends a request to the Confluence Cloud server to create the content defined in the
   * given {@link CreateContentRequest}.
   *
   * @param request The request defining the content that should be created, and what fields should
   * be returned in the response.
   */
  public Content createContent(CreateContentRequest request) throws ConfluenceRequestException {
    return (Content) performRequest(request);
  }

  /**
   * This method performs the given request and returns the servers response.
   *
   * @throws ConfluenceRequestException If the server responses with an error status code
   */
  Object performRequest(ConfluenceRequest request) throws ConfluenceRequestException {
    WebTarget endpointTarget = wikiTarget.path(request.getRelativePath());
    for (Entry<String, Set<String>> queryParam : request.getQueryParams().entrySet()) {
      String paramName = queryParam.getKey();
      Set<String> paramValues = queryParam.getValue();
      endpointTarget = endpointTarget.queryParam(paramName, paramValues.toArray());
    }

    Invocation.Builder invocationBuilder = endpointTarget.request();
    Map<String, String> headers = getRequestHeaders(request);
    for (Entry<String, String> headerEntry : headers.entrySet()) {
      invocationBuilder.header(headerEntry.getKey(), headerEntry.getValue());
    }

    String methodName = request.getMethod();
    Response response;
    if (request.getBodyEntity().isPresent()) {
      Object bodyEntity = request.getBodyEntity().get();
      response = invocationBuilder.method(methodName, Entity.json(bodyEntity));
    } else {
      response = invocationBuilder.method(methodName);
    }

    int statusCode = response.getStatus();
    if (response.getStatus() >= 300) {
      String errorMsg;
      if (MediaType.APPLICATION_JSON_TYPE.equals(response.getMediaType())) {
        ErrorResponse errResponse = response.readEntity(ErrorResponse.class);
        errorMsg = errResponse.getMessage();
      } else {
        errorMsg = response.getStatusInfo().getReasonPhrase();
      }
      throw new ConfluenceRequestException(statusCode, errorMsg);
    }

    return response.readEntity(request.getReturnType());
  }

  /**
   * This method returns the headers that should be included in the given request.
   *
   * @param request The request to generate headers for
   * @return A map representing the headers for the request
   */
  private Map<String, String> getRequestHeaders(ConfluenceRequest request) {
    Map<String, String> requestHeaders = new HashMap<>();
    requestHeaders.put("Content-Type", request.getContentType().toString());
    requestHeaders.put("Accept", request.getAcceptedResponseType().toString());
    if (authMethod != null) {
      requestHeaders.put("Authorization", authMethod.getAuthHeaderValue());
    }
    return requestHeaders;
  }
}
