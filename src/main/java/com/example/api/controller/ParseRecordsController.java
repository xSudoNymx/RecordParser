package com.example.api.controller;

import com.example.api.model.Record;
import com.example.api.service.ParseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/records")
public class ParseRecordsController {
  @Autowired
  ParseService parseService;

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(method = RequestMethod.POST,consumes = "text/plain", produces = "application/json")
  public void addRecord(@RequestBody String record){
    parseService.addRecord(record);
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/gender", method = RequestMethod.GET, produces = "application/json")
  public List<Record> getRecordsByGender(){
    return parseService.getRecordByGender();
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/birthdate", method = RequestMethod.GET, produces = "application/json")
  public List<Record> getRecordsByBirthday(){
    return parseService.getRecordByBirthday();
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/name", method = RequestMethod.GET, produces = "application/json")
  public List<Record> getRecordsByName(){
    return parseService.getRecordByName();
  }


}
