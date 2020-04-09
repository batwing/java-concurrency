package com.java.recruitment.revolut;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlShorter {
  public static final String BASE_URL = "http://short.com/";
  public static final int MAX_SEO_KEYWORD_SIZE = 20;

  public String convert(String originalUrl, String seoKeyword) throws MalformedURLException {
    if (originalUrl == null || "".equals(originalUrl)) {
      throw new IllegalArgumentException("URL is blank or empty");
    }

    if (seoKeyword == null || "".equals(seoKeyword)) {
      throw new IllegalArgumentException("URL is blank or empty");
    }

    if (seoKeyword.length() > MAX_SEO_KEYWORD_SIZE) {
      throw new IllegalArgumentException(String.format("SEO-keyword could be at most: %s", MAX_SEO_KEYWORD_SIZE));
    }

    new URL(originalUrl);
    return BASE_URL + seoKeyword;
  }
}
