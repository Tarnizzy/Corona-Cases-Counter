package com.example.Coronacasescounter.controllers;

import com.example.Coronacasescounter.model.LocationStats;
import com.example.Coronacasescounter.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class coronaviruscontroller{
    @Autowired
    private CoronaVirusDataService coronaVirusDataService;
    @RequestMapping("/Countries/{name}")
   public String returnCasesPerCountry(@PathVariable String name){
    String nation= coronaVirusDataService.returnStatsForACountry(name);
    return nation;
  }
  @RequestMapping("/")
public String sayHello(){
        return "Hello World";
  }

}
