package com.example.api.service;

import com.example.api.model.Record;
import com.example.api.util.RecordRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ParseService {
  private RecordRepository recordRepository;

  public ParseService() {
    recordRepository = new RecordRepository();
  }

  public void addRecord(String record) {
    recordRepository.addRecord(record);
  }

  public List<Record> getRecordByGender() {
    List<Record> records = new ArrayList<>(recordRepository.getRecords());
    records.sort(Comparator.comparing(Record::getGender));
    return records;
  }

  public List<Record> getRecordByBirthday() {
    List<Record> records = new ArrayList<>(recordRepository.getRecords());
    records.sort(Comparator.comparing(Record::getDateOfBirth));
    return records;
  }

  public List<Record> getRecordByName() {
    List<Record> records = new ArrayList<>(recordRepository.getRecords());
    records.sort(Comparator.comparing(Record::getFirstName));
    return records;
  }
}
