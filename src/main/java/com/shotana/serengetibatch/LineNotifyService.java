package com.shotana.serengetibatch;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class LineNotifyService {
    private static final String LINE_URL = "https://notify-api.line.me/api/notify";
    private final RestTemplate restTemplate;

    @Value("${line.token}")
    private String lineToken;

    public LineNotifyService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void notifyLine(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        System.out.println("LINETOKEN: " + lineToken);
        headers.add("Authorization", "Bearer " + lineToken);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add("message", message);
        RequestEntity<MultiValueMap<String, String>> request =
                new RequestEntity<>(body, headers, HttpMethod.POST, URI.create(LINE_URL));

        try {
            ResponseEntity<Object> responseEntity = restTemplate.exchange(request, Object.class);
        } catch (Throwable throwable) {
            System.out.println(throwable);
        }
    }
}
