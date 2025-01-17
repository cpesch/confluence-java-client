package com.github.crob1140.confluence.requests;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.ws.rs.HttpMethod;

import com.github.crob1140.confluence.content.Content;

public class DeleteAttachmentsRequest extends ConfluenceRequest {
    private final String id;

    public DeleteAttachmentsRequest(Builder builder) {
        super();
        this.id = builder.id;
    }

    @Override
    public String getRelativePath() {
        return "rest/api/content/{id}".replace("{id}", this.id);
    }

    /**
     * This method returns the HTTP method used by this request.
     */
    @Override
    public String getMethod() {
        return HttpMethod.DELETE;
    }

    @Override
    public Map<String, String> getQueryParams() {
        return new HashMap<>();
    }

    @Override
    public Optional<Object> getBodyEntity() {
        return Optional.empty();
    }

    /**
     * This method returns the class of the object in the body of the response for this request.
     *
     * @return The class of the object in the body of response for this request.
     */
    @Override
    public Class<?> getReturnType() {
        return Content.class;
    }

    public static class Builder {
        private String id;

        /**
         * This method sets the unique identifier for the content.
         *
         * @param id The unique identifier for the content.
         * @return This instance, for the purposes of method chaining.
         */
        public DeleteAttachmentsRequest.Builder setId(String id) {
            this.id = id;
            return this;
        }

        /**
         * This method creates an instance of {@link DeleteAttachmentsRequest} using the values that were
         * set on this instance.
         *
         * @return A new instance of {@link DeleteAttachmentsRequest} with the values set on this instance.
         * @throws IllegalStateException If the request that would be created would be invalid.
         */
        public DeleteAttachmentsRequest build() throws IllegalStateException {
            if (this.id == null || this.id.equals("")) {
                throw new IllegalStateException("You must specify the id of the page you want to fetch attachemnts");
            }
            return new DeleteAttachmentsRequest(this);
        }
    }
}
