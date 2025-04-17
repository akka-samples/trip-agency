package com.lb.ai.tools;

import akka.javasdk.JsonSupport;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public record AccommodationAPIResponse(
    String id,
    String name,
    String neighborhood,
    ZonedDateTime checkin,
    ZonedDateTime checkout,
    int pricepernight) {

  private static final Logger log = LoggerFactory.getLogger(AccommodationAPIResponse.class);

  public static List<AccommodationAPIResponse> extract(InputStream json) {

    try {
      return JsonSupport.getObjectMapper()
          .readValue(json, new TypeReference<List<AccommodationAPIResponse>>() {});
    } catch (IOException e) {
      log.error(e.getMessage());
      throw new RuntimeException(e);
    }
  }


}
