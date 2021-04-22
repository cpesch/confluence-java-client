package com.github.crob1140.confluence.content;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents the links for an instance of {@link Content}.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Links {
    @JsonProperty
    private String download;

    @SuppressWarnings("unused")
    private Links() {
        // Required for Jackson deserialization
    }

    /**
     * This method returns the download link of the corresponding content.
     *
     * @return The download link of the corresponding content.
     */
    public String getDownload() {
        return this.download;
    }

}
