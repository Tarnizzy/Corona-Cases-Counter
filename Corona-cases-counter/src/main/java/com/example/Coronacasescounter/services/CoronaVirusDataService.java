package com.example.Coronacasescounter.services;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CoronaVirusDataService {


    private static String CORONA_VIRUS_DATA_SOURCE = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/01-01-2021.csv";
   @PostConstruct
    public void fetchVirusData() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                              .uri(URI.create(CORONA_VIRUS_DATA_SOURCE))
                                .build();

      HttpResponse<String> httpResponse =  httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.body());

    }
}
