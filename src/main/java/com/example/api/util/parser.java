package com.example.api.util;

import com.example.api.model.Record;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class parser {

  private static final String PIPE_REGEX = "\\s*\\|\\s*";
  private static final String COMMA_REGEX = "\\s*,\\s*";
  private static final String SPACE_REGEX = "\\s+";
  private static final int RECORD_SIZE = 5;
  private static List<Record> records = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    if (args.length <= 0) {
      return;
    }

    for (String path : args) {
      records.addAll(readFileByLine(path));
    }

    printList(getRecordsSortedByGenderAndLastName(true));
    printList(getRecordsSortedByBirthday(true));
    printList(getRecordsSortedByLastName(false));
  }

  private static List<Record> readFileByLine(String filePath) throws IOException {
    List<Record> list = new ArrayList<>();
    Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8);
    stream.forEach(s -> { Record record = strToRecord(s); if (record != null) { list.add(record); } });
    return list;
  }

  public static Record strToRecord(String s) {
    String[] strArray = splitRecord(s);

    if (strArray.length != RECORD_SIZE) {
      return null;
    }

    return new Record(strArray[0], strArray[1], strArray[2], strArray[3], strArray[4]);
  }

  private static String[] splitRecord(String s){
    if (s.contains("|")) {
      return s.split(PIPE_REGEX);
    } else if (s.contains(",")) {
      return s.split(COMMA_REGEX);
    } else {
      return s.split(SPACE_REGEX);
    }
  }

  public static List<Record> getRecords() {
    return records;
  }

  public static List<Record> getRecordsSortedByGenderAndLastName(boolean asc){
    records.sort(Comparator.comparing(Record::getGender).thenComparing(Record::getLastName));

    if(!asc)
      Collections.reverse(records);

    return records;
  }

  public static List<Record> getRecordsSortedByBirthday(boolean asc){
    records.sort(Comparator.comparing(Record::getDateOfBirth));

    if(!asc)
      Collections.reverse(records);

    return records;
  }

  public static List<Record> getRecordsSortedByLastName(boolean asc){
    records.sort(Comparator.comparing(Record::getLastName));

    if(!asc)
      Collections.reverse(records);

    return records;
  }

  private static void printList(List<Record> list) {
    list.forEach(System.out::println);
    System.out.println();
  }
}
