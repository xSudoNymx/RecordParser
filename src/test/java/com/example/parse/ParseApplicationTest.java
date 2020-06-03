package com.example.parse;

import static org.junit.jupiter.api.Assertions.*;

import com.example.api.exception.DateFormatException;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class ParseApplicationTest {

  @Test
  void testBadPath() {
    assertThrows(IOException.class, () -> ParseApplication.main(new String[]{"BadName"}));

  }

  @Test
  void testEmptyPath() {
    assertThrows(IOException.class, () -> ParseApplication.main(new String[]{""}));
  }
}