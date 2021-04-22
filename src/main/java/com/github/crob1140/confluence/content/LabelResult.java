package com.github.crob1140.confluence.content;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents an intermediate result list for {@link Label}s within an instance of {@link Metadata}.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LabelResult {
    @JsonProperty
    private List<Label> labels;

    @SuppressWarnings("unused")
    private LabelResult() {
        // Required for Jackson deserialization
    }

    /**
     * This constructor creates a new intermediate result list for {@link Label}s.
     *
     * @param labels The labels on the content corresponding to this metadata.
     */
    public LabelResult(List<Label> labels) {
        this.labels = labels;
    }

    /**
     * This method returns the labels on the content corresponding to this metadata.
     *
     * @return The labels on the content corresponding to this metadata.
     */
    public List<Label> getLabels() {
        return labels;
    }
}
