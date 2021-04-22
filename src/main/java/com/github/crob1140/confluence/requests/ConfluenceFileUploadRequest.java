package com.github.crob1140.confluence.requests;

import java.io.File;
import java.util.Map;

import javax.ws.rs.core.MediaType;

/**
 * This class represents a HTTP file upload request performed against the Confluence REST API
 */
public abstract class ConfluenceFileUploadRequest extends ConfluenceRequest {
  public abstract File getFile();
}
