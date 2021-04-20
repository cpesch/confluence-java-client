package com.github.crob1140.confluence.content;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents the body of a content object in the Confluence Cloud server.
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentBody {

  @JsonProperty
  private ContentBodyFormat anonymous_export_view;
  @JsonProperty
  private ContentBodyFormat editor2;
  @JsonProperty
  private ContentBodyFormat export_view;
  @JsonProperty
  private ContentBodyFormat storage;
  @JsonProperty
  private ContentBodyFormat styled_view;
  @JsonProperty
  private ContentBodyFormat view;

  @SuppressWarnings("unused")
  private ContentBody() {
    // Required for Jackson deserialization
  }

  /**
   * This constructor creates the content body using the value defined for a given type.
   *
   * @param type The body type that is being defined.
   * @param value The value for the given body type.
   */
  public ContentBody(ContentBodyType type, String value) {
    ContentBodyFormat bodyFormat = new ContentBodyFormat(value, type.getIdentifier());
    switch (type) {
      case ANONYMOUS_EXPORT_VIEW:
        this.anonymous_export_view = bodyFormat;
        break;
      case EDITOR2:
        this.editor2 = bodyFormat;
        break;
      case EXPORT_VIEW:
        this.export_view = bodyFormat;
        break;
      case STORAGE:
        this.storage = bodyFormat;
        break;
      case STYLED_VIEW:
        this.styled_view = bodyFormat;
        break;
      case VIEW:
        this.view = bodyFormat;
        break;
    }
  }

  /**
   * Return the content body format for a given type.
   * @param type The body type that is being defined.
   * @return the content body format for the given type.
   */
  public ContentBodyFormat getFormat(ContentBodyType type) {
    switch (type) {
      case ANONYMOUS_EXPORT_VIEW:
        return anonymous_export_view;
      case EDITOR2:
        return editor2;
      case EXPORT_VIEW:
        return export_view;
      case STORAGE:
        return storage;
      case STYLED_VIEW:
        return styled_view;
      case VIEW:
        return view;
      default:
        throw new IllegalArgumentException("ContentBodyType " + type + " is unknown");
    }
  }
}
