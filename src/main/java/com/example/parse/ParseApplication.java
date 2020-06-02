package com.example.parse;

import com.example.api.model.Record;
import com.example.api.util.RecordRepository;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class ParseApplication {
  private static RecordRepository recordRepository = new RecordRepository();

  public static void main(String[] args) throws IOException {
    for (String path : args) {
        readFileByLine(path);
    }

    printList(recordRepository.getRecordsSortedByGenderAndLastName(true));
    printList(recordRepository.getRecordsSortedByBirthday(true));
    printList(recordRepository.getRecordsSortedByLastName(false));
  }

  private static void readFileByLine(String filePath) throws IOException {
    Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8);
    stream.forEach(s -> recordRepository.addRecord(s));
  }

  private static void printList(List<Record> list) {
    list.forEach(System.out::println);
    System.out.println();
  }
}
