package com.example.api.model;

import static org.junit.jupiter.api.Assertions.*;

import com.example.api.exception.DateFormatException;
import org.junit.jupiter.api.Test;

class RecordTest {

  @Test
  void testToString() {
    Record r2 = new Record("Wong", "Kailum", "Male", "Orange", "11/24/2003");
    assertEquals("Wong Kailum Male Orange 11/24/2003", r2.toString());
  }

  @Test
  void testInvalidDateEmptyRecordCreation() {
    DateFormatException thrown = assertThrows(DateFormatException.class,
        () -> new Record("Wong", "Kailum", "Male", "Orange", ""));

    assertTrue(thrown.getMessage().contains("Date Format Error"));
  }

  @Test
  void testInvalidDateRecordCreation() {
    DateFormatException thrown = assertThrows(DateFormatException.class,
        () -> new Record("Wong", "Kailum", "Male", "Orange", "11-24-2003"));

    assertTrue(thrown.getMessage().contains("Date Format Error"));
  }
}