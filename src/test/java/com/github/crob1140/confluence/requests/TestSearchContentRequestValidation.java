package com.github.crob1140.confluence.requests;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.github.crob1140.confluence.content.ContentBodyType;
import com.github.crob1140.confluence.content.StandardContentType;

@RunWith(Parameterized.class)
public class TestSearchContentRequestValidation {
  private final SearchContentRequest.Builder requestBuilder;
  private final Class<Exception> expectedExceptionClass;
  private final String expectedExceptionMessage;

  public TestSearchContentRequestValidation(String description,
      SearchContentRequest.Builder requestBuilder, Class<Exception> expectedExceptionClass,
      String expectedExceptionMessage) {
    this.requestBuilder = requestBuilder;
    this.expectedExceptionClass = expectedExceptionClass;
    this.expectedExceptionMessage = expectedExceptionMessage;
  }

  @Parameters(name = "{0}")
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{{
        "Requests cannot be created without a CQL query",
        new SearchContentRequest.Builder(),
        IllegalStateException.class,
        "You must specify a CQL query"
    }, {
        "Requests cannot be created with negative limits",
        new SearchContentRequest.Builder().setLimit(-1).setCql("text~'my search phrase'"),
        IllegalStateException.class,
        "The limit must be a positive number"
    }, {
        "Requests cannot be created with a limit of zero",
        new SearchContentRequest.Builder().setLimit(0).setCql("text~'my search phrase'"),
        IllegalStateException.class,
        "The limit must be a positive number"
    }, {
        "Requests cannot be created with negative start positions",
        new SearchContentRequest.Builder().setStartPosition(-1).setCql("text~'my search phrase'"),
        IllegalStateException.class,
        "The start position must be a positive number"
    }, {
        "Requests cannot be created with a start position of zero",
        new SearchContentRequest.Builder().setStartPosition(0).setCql("text~'my search phrase'"),
        IllegalStateException.class,
        "The start position must be a positive number"
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
