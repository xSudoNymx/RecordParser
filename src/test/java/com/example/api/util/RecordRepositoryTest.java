package com.example.api.util;

import static org.junit.jupiter.api.Assertions.*;

import com.example.api.exception.DateFormatException;
import com.example.api.model.Record;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecordRepositoryTest {

  private RecordRepository recordRepository;

  @BeforeEach
  void setUp() {
    recordRepository = new RecordRepository();
  }

  @Test
  void parseRecordPipe() {
    Record r1 = recordRepository.parseRecord("Wong | Kailum | Male | Orange |11/24/2003");
    Record r2 = new Record("Wong", "Kailum", "Male", "Orange", "11/24/2003");

    assertEquals(r1, r2);
  }

  @Test
  void parseRecordComma() {
    Record r1 = recordRepository.parseRecord("Weber, Rikesh, Male, Gray,04/11/1901");
    Record r2 = new Record("Weber", "Rikesh", "Male", "Gray", "04/11/1901");

    assertEquals(r1, r2);
  }

  @Test
  void parseRecordSpace() {
    Record r1 = recordRepository.parseRecord("Whitehead Chloe  Female Navy 01/27/2002");
    Record r2 = new Record("Whitehead", "Chloe", "Female", "Navy", "01/27/2002");

    assertEquals(r1, r2);
  }

  @Test
  void parseRecordBadDate() {
    DateFormatException thrown = assertThrows(DateFormatException.class,
        () -> recordRepository.parseRecord("Fuller Sebastian   Male Brown 01-09-1987"));

    assertTrue(thrown.getMessage().contains("Date Format Error"));
  }

  @Test
  void parseBadRecordBadDate() {
    DateFormatException thrown = assertThrows(DateFormatException.class,
        () -> recordRepository.parseRecord("Bad Request going in here"));

    assertTrue(thrown.getMessage().contains("Date Format Error"));
  }

  @Test
  void parseRecordEmpty() {
    Record r1 = recordRepository.parseRecord(" ");

    assertNull(r1);
  }

  @Test
  void testAddStringRecord() {
    recordRepository.addRecord("Wong | Kailum | Male | Orange |11/24/2003");

    assertEquals(recordRepository.getRecords().size(), 1);
  }

  @Test
  void testAddBadStringRecord() {
    DateFormatException thrown = assertThrows(DateFormatException.class,
        () -> recordRepository.addRecord("Bad Request going in here"));

    assertTrue(thrown.getMessage().contains("Date Format Error"));
    assertEquals(0, recordRepository.getRecords().size());
  }

  @Test
  void testAddEmptyStringRecord() {
    recordRepository.addRecord(" ");
    assertEquals(0, recordRepository.getRecords().size());
  }

  @Test
  void testAddRecord() {
    Record r = new Record("Weber", "Rikesh", "Male", "Gray", "04/11/1901");
    recordRepository.addRecord(r);

    assertEquals(recordRepository.getRecords().size(), 1);
    assertEquals(recordRepository.getRecords().get(0), r);
  }

  @Test
  void testAddNullRecord() {
    recordRepository.addRecord((Record) null);
    assertEquals(0, recordRepository.getRecords().size());
  }
}