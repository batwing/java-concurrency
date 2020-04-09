package com.java.recruitment.revolut;


import static org.junit.Assert.assertEquals;
import java.net.MalformedURLException;
import org.junit.Test;

public class UrlShorterTest {

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenUrlIsEmpty() throws MalformedURLException {
    new UrlShorter().convert("", "test-seo");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenSeoIsEmpty() throws MalformedURLException {
    new UrlShorter().convert("http://test.com", "");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenUrlIsNull() throws MalformedURLException{
    new UrlShorter().convert(null, "test-seo");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenSeoIsNull() throws MalformedURLException {
    new UrlShorter().convert("http://test.com", null);
  }

  @Test(expected = MalformedURLException.class)
  public void shouldThrowExceptionWhenProvidedUrlIsNotCorrect() throws MalformedURLException{
    new UrlShorter().convert("http/test.com", "test-seo");
  }

  @Test
  public void shouldReturnShortUrlIncludingBaseUrl() throws MalformedURLException{
    assertEquals("http://short.com/test-seo", new UrlShorter().convert("http://test.com/test123", "test-seo"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenSeoKeywordIsLongerThenMaxLength() throws MalformedURLException{
    new UrlShorter().convert("http://test.com", "1234567890123456789012345");
  }
}
