package com.github.crob1140.confluence.requests;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import javax.ws.rs.HttpMethod;

/**
 * This class represents a HTTP download request performed against the Confluence REST API
 */
public class DownloadAttachmentRequest extends ConfluenceRequest {
  private final String relativePath;

  public DownloadAttachmentRequest(DownloadAttachmentRequest.Builder builder) {
    super();
    this.relativePath = builder.relativePath;
  }

  @Override
  public String getRelativePath() {
    return relativePath;
  }

  /**
   * This method returns the HTTP method used by this request.
   */
  @Override
  public String getMethod() {
    return HttpMethod.GET;
  }

  @Override
  public Map<String, String> getQueryParams() {
    return Collections.emptyMap();
  }

  @Override
  public Optional<Object> getBodyEntity() {
    return Optional.empty();
  }

  @Override
  public Class<?> getReturnType() {
    return InputStream.class;
  }

  public static class Builder {
    private String relativePath;

    /**
     * This method sets the relative path for the attachment download.
     *
     * @param relativePath The uthe relative path for the attachment download..
     * @return This instance, for the purposes of method chaining.
     */
    public DownloadAttachmentRequest.Builder setRelativePath(String relativePath) {
      this.relativePath = relativePath;
      return this;
    }

    /**
     * This method creates an instance of {@link DownloadAttachmentRequest} using the values that were
     * set on this instance.
     *
     * @return A new instance of {@link DownloadAttachmentRequest} with the values set on this instance.
     * @throws IllegalStateException If the request that would be created would be invalid.
     */
    public DownloadAttachmentRequest build() throws IllegalStateException {
      if (this.relativePath == null || this.relativePath.equals("")) {
        throw new IllegalStateException("You must specify the relative path of the attachment you want to download");
      }
      return new DownloadAttachmentRequest(this);
    }
  }
}
