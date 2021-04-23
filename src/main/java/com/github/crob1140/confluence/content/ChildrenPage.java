package com.github.crob1140.confluence.content;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents the page children for an instance of {@link Content}.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChildrenPage {
    @JsonProperty
    private List<Content> results;
    @JsonProperty
    private int start;
    @JsonProperty
    private int limit;
    @JsonProperty
    private int size;

    @SuppressWarnings("unused")
    private ChildrenPage() {
        // Required for Jackson deserialization
    }

    /**
     * This method returns the page children of the corresponding content.
     *
     * @return The page children of the corresponding content.
     */
    public List<Content> getResults() {
        return this.results;
    }

    /**
     * This method returns the start of the results list with the page children of the corresponding content.
     *
     * @return The start of the results list with the page children of the corresponding content.
     */
    public int getStart() {
        return this.start;
    }

    /**
     * This method returns the limit of the results list with the page children of the corresponding content.
     *
     * @return The limit of the results list with the page children of the corresponding content.
     */
    public int getLimit() {
        return this.limit;
    }

    /**
     * This method returns the number of page children of the corresponding content.
     *
     * @return The number of page children of the corresponding content.
     */
    public int getSize() {
        return this.size;
    }
}
