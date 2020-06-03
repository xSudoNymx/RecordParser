package com.example.api.exception;

public class DateFormatException extends IllegalArgumentException {

  private static final String ERROR_MESSAGE = "Date Format Error - MM/DD/YYYY";

  public DateFormatException() {
    super(ERROR_MESSAGE);
  }
}
