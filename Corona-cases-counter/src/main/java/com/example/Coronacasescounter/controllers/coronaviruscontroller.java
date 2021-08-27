package com.example.Coronacasescounter.controllers;

import com.example.Coronacasescounter.model.LocationStats;
import com.example.Coronacasescounter.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoronaVirusController {
    @Autowired
    private CoronaVirusDataService coronaVirusDataService;

    @RequestMapping("/Countries/{name}")
    public String returnCasesPerCountry(@PathVariable String name) {
        String nation = coronaVirusDataService.returnStatsForACountry(name);
        return nation;
    }

    @RequestMapping("/")
    public int getNumberOfDeaths() {
        int numOfDeaths = coronaVirusDataService.returnTotalNumberOfDeaths();
        return numOfDeaths;
    }

    @GetMapping
    @RequestMapping("/Cases/{name}")
    public int getNumberOfActiveCasesPerCountry(@PathVariable String name) {
        return coronaVirusDataService.returnTotalNumberOfActiveCases(name);
    }
    @RequestMapping("/Cases/")
    public String sayHello(){
        return "Hello";
    }
}
