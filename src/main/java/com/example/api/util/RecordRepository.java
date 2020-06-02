package com.example.api.util;

import com.example.api.model.Record;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class RecordRepository {

  private static final String PIPE_REGEX = "\\s*\\|\\s*";
  private static final String COMMA_REGEX = "\\s*,\\s*";
  private static final String SPACE_REGEX = "\\s+";
  private static final int RECORD_SIZE = 5;
  private List<Record> records = new ArrayList<>();

  public void addRecord(Record record){
    if(record == null)
      return;
    records.add(record);
  }

  public void addRecord(String record){
    if(record == null || record.isBlank())
      return;
    records.add(parseRecord(record));
  }

  public Record parseRecord(String s) {
    String[] strArray = splitRecord(s);

    if (strArray.length != RECORD_SIZE) {
      return null;
    }

    return new Record(strArray[0], strArray[1], strArray[2], strArray[3], strArray[4]);
  }

  private String[] splitRecord(String s) {
    if (s.contains("|")) {
      return s.split(PIPE_REGEX);
    } else if (s.contains(",")) {
      return s.split(COMMA_REGEX);
    } else {
      return s.split(SPACE_REGEX);
    }
  }

  public List<Record> getRecords() {
    return records;
  }
}
