package com.github.crob1140.confluence.requests;

import com.github.crob1140.confluence.content.ContentBodyType;
import com.github.crob1140.confluence.content.ContentStatus;
import com.github.crob1140.confluence.content.StandardContentType;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestCreateContentRequestValidation {
  private final CreateContentRequest.Builder requestBuilder;
  private final Class<Exception> expectedExceptionClass;
  private final String expectedExceptionMessage;

  public TestCreateContentRequestValidation(String description,
      CreateContentRequest.Builder requestBuilder, Class<Exception> expectedExceptionClass,
      String expectedExceptionMessage) {
    this.requestBuilder = requestBuilder;
    this.expectedExceptionClass = expectedExceptionClass;
    this.expectedExceptionMessage = expectedExceptionMessage;
  }

  @Parameters(name = "{0}")
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{{
        "Requests cannot be created without a space key",
        new CreateContentRequest.Builder().setType(StandardContentType.PAGE).setBody(
            ContentBodyType.STORAGE, "test body"),
        IllegalStateException.class,
        "You must specify the space that the content is being created in"
    }, {
        "Requests cannot be created without a type",
        new CreateContentRequest.Builder().setSpaceKey("TESTSPACE").setBody(ContentBodyType.VIEW,
            "test body"),
        IllegalStateException.class,
        "You must specify the type of content you want to create"
    }, {
        "Requests for drafts cannot be created without providing a content ID",
        new CreateContentRequest.Builder().setType(StandardContentType.PAGE).setStatus(
            ContentStatus.DRAFT).setSpaceKey("TESTSPACE").setBody(ContentBodyType.EXPORT_VIEW,
            "test body"),
        IllegalStateException.class,
        "You must provide an ID when creating a draft"
    }});
  }

  @Test
  public void testExpectedExceptionThrown() {
    try {
      this.requestBuilder.build();
      Assert.fail("Should have thrown " + this.expectedExceptionClass + " but didn't");
    } catch (Exception e) {
      Assert.assertEquals(this.expectedExceptionClass, e.getClass());
      Assert.assertEquals(this.expectedExceptionMessage, e.getMessage());
    }
  }
}
