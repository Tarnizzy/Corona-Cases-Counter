package com.example.Coronacasescounter.services;

import com.example.Coronacasescounter.model.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CoronaVirusDataService {


    private List<LocationStats> allStats = new ArrayList<>();

    private static String CORONA_VIRUS_DATA_SOURCE = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/01-01-2021.csv";
   @PostConstruct
   @Scheduled(cron = "*/30 * * * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
       List<LocationStats> newStats = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                              .uri(URI.create(CORONA_VIRUS_DATA_SOURCE))
                                .build();

      HttpResponse<String> httpResponse =  httpClient.send(request, HttpResponse.BodyHandlers.ofString());
       StringReader csvbodyReader = new StringReader(httpResponse.body());
       Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvbodyReader);
       for(CSVRecord record:records){
           LocationStats locationStats = new LocationStats();
           locationStats.setCountry(record.get("Country_Region"));
           int numOfDeaths = Integer.parseInt(record.get("Deaths"));
           int numberOfCases = Integer.parseInt(record.get("Confirmed"));
           locationStats.setNumOfDeaths(numOfDeaths);
           locationStats.setLatestReportedTotal(numberOfCases);
           newStats.add(locationStats);
       }
        this.allStats = newStats;
    }

    public String returnStatsForACountry(String countryName){
       Stream<LocationStats> ourData = allStats.stream();
        Optional<LocationStats> targetCountry = ourData.filter(L->L.getCountry().equals(countryName)).findFirst();
       String targetCountryName = targetCountry.map(Object::toString).orElse("");
       return targetCountryName;
    }


}
