package com.github.crob1140.confluence.requests;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.ws.rs.HttpMethod;

import com.github.crob1140.confluence.content.Content;

public class AddAttachmentsRequest extends ConfluenceFileUploadRequest {
    private final String id;
    private final File file;

    public AddAttachmentsRequest(Builder builder) {
        super();
        this.id = builder.id;
        this.file = builder.file;
    }

    @Override
    public String getRelativePath() {
        return "rest/api/content/{id}/child/attachment".replace("{id}", this.id);
    }

    /**
     * This method returns the HTTP method used by this request.
     */
    @Override
    public String getMethod() {
        return HttpMethod.POST;
    }

    @Override
    public Map<String, String> getQueryParams() {
        return new HashMap<>();
    }

    @Override
    public Optional<Object> getBodyEntity() {
        return Optional.empty();
    }

    @Override
    public File getFile() {
        return file;
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
        private File file;

        /**
         * This method sets the unique identifier for the content.
         *
         * @param id The unique identifier for the content.
         * @return This instance, for the purposes of method chaining.
         */
        public AddAttachmentsRequest.Builder setId(String id) {
            this.id = id;
            return this;
        }

        public AddAttachmentsRequest.Builder setFile(File file) {
            this.file = file;
            return this;
        }

        /**
         * This method creates an instance of {@link AddAttachmentsRequest} using the values that were
         * set on this instance.
         *
         * @return A new instance of {@link AddAttachmentsRequest} with the values set on this instance.
         * @throws IllegalStateException If the request that would be created would be invalid.
         */
        public AddAttachmentsRequest build() throws IllegalStateException {
            if (this.id == null || this.id.equals("")) {
                throw new IllegalStateException("You must specify the id of the page you want to add attachemnts");
            }

            if (this.file == null) {
                throw new IllegalStateException("You must specify the file");
            }

            return new AddAttachmentsRequest(this);
        }
    }
}
