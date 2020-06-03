package com.example.api.controller;

import com.example.api.model.Record;
import com.example.api.service.ParseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  @RequestMapping(method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
  public ResponseEntity<?> addRecord(@RequestBody String record) {
    Record r = parseService.addRecord(record);
    if (r != null) {
      return new ResponseEntity<>(r, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/gender", method = RequestMethod.GET, produces = "application/json")
  public List<Record> getRecordsByGender() {
    return parseService.getRecordByGender();
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/birthdate", method = RequestMethod.GET, produces = "application/json")
  public List<Record> getRecordsByBirthday() {
    return parseService.getRecordByBirthday();
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/name", method = RequestMethod.GET, produces = "application/json")
  public List<Record> getRecordsByName() {
    return parseService.getRecordByName();
  }


}
