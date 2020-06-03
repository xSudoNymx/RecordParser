package com.example.api.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.api.exception.DateFormatException;
import com.example.api.model.Record;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParseServiceTest {
  private ParseService parseService;

  @BeforeEach
  void setUp() {
    parseService = new ParseService();
  }

  @Test
  void getRecordsSortedByGender() {
    Record r1 = new Record("Gill","Edwin","Male","Blue","05/29/1986");
    Record r2 = new Record("Bridges", "Mary","Female","Pink", "02/13/1997");
    Record r3 = new Record("Whitehead", "Chloe",  "Female", "Navy", "01/27/2002");
    Record r4 = new Record("Fuller", "Sebastian",   "Male", "Brown", "01/09/1987");

    parseService.addRecord("Gill,Edwin,Male,Blue,05/29/1986");
    parseService.addRecord("Bridges         | Mary |            Female | Pink |                   02/13/1997");
    parseService.addRecord("Whitehead Chloe  Female Navy 01/27/2002");
    parseService.addRecord("Fuller Sebastian   Male Brown 01/09/1987");

    List<Record> list = parseService.getRecordByGender();
    assertEquals(4, list.size());
    assertEquals(r2, list.get(0));
    assertEquals(r3, list.get(1));
    assertEquals(r1, list.get(2));
    assertEquals(r4, list.get(3));
  }

  @Test
  void getRecordsSortedByBirthday() {
    Record r1 = new Record("Gill","Edwin","Male","Blue","05/29/1986");
    Record r2 = new Record("Bridges", "Mary","Female","Pink", "02/13/1997");
    Record r3 = new Record("Whitehead", "Chloe",  "Female", "Navy", "01/27/2002");
    Record r4 = new Record("Fuller", "Sebastian",   "Male", "Brown", "01/09/1987");

    parseService.addRecord("Gill,Edwin,Male,Blue,05/29/1986");
    parseService.addRecord("Bridges         | Mary |            Female | Pink |                   02/13/1997");
    parseService.addRecord("Whitehead Chloe  Female Navy 01/27/2002");
    parseService.addRecord("Fuller Sebastian   Male Brown 01/09/1987");

    List<Record> list = parseService.getRecordByBirthday();
    assertEquals(4, list.size());
    assertEquals(r1, list.get(0));
    assertEquals(r4, list.get(1));
    assertEquals(r2, list.get(2));
    assertEquals(r3, list.get(3));
  }

  @Test
  void getRecordsSortedByName() {
    Record r1 = new Record("Gill","Edwin","Male","Blue","05/29/1986");
    Record r2 = new Record("Bridges", "Mary","Female","Pink", "02/13/1997");
    Record r3 = new Record("Whitehead", "Chloe",  "Female", "Navy", "01/27/2002");
    Record r4 = new Record("Fuller", "Sebastian",   "Male", "Brown", "01/09/1987");

    parseService.addRecord("Gill,Edwin,Male,Blue,05/29/1986");
    parseService.addRecord("Bridges         | Mary |            Female | Pink |                   02/13/1997");
    parseService.addRecord("Whitehead Chloe  Female Navy 01/27/2002");
    parseService.addRecord("Fuller Sebastian   Male Brown 01/09/1987");

    List<Record> list = parseService.getRecordByName();
    assertEquals(4, list.size());
    assertEquals(r3, list.get(0));
    assertEquals(r1, list.get(1));
    assertEquals(r2, list.get(2));
    assertEquals(r4, list.get(3));
  }
}