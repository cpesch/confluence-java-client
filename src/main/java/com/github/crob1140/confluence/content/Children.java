package com.github.crob1140.confluence.content;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents the children for an instance of {@link Content}.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Children {
    @JsonProperty
    private ChildrenPage page;

    @SuppressWarnings("unused")
    private Children() {
        // Required for Jackson deserialization
    }

    /**
     * This method returns the page children of the corresponding content.
     *
     * @return The page children of the corresponding content.
     */
    public ChildrenPage getPage() {
        return this.page;
    }
}
