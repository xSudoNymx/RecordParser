package com.example.api.model;

import com.example.api.exception.DateFormatException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Record {

  private static final String DATEFORMAT = "MM/dd/yyyy";

  private String firstName;
  private String lastName;
  private String gender;
  private String favoriteColor;
  private Date dateOfBirth;

  public Record(String lName, String fName, String gender, String favColor, String dateOfBirth) {
    this.lastName = lName;
    this.firstName = fName;
    this.gender = gender;
    this.favoriteColor = favColor;
    this.dateOfBirth = parseDate(dateOfBirth);
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getGender() {
    return gender;
  }

  public String getFavoriteColor() {
    return favoriteColor;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  @Override
  public String toString() {
    return lastName + " " + firstName + " " + gender + " " + favoriteColor + " " + dateToString();
  }

  private Date parseDate(String date) {
    try {
      return new SimpleDateFormat(DATEFORMAT).parse(date);
    } catch (ParseException e) {
      throw new DateFormatException();
    }
  }

  private String dateToString() {
    return new SimpleDateFormat(DATEFORMAT).format(dateOfBirth);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Record)) {
      return false;
    }
    Record record = (Record) o;
    return firstName.equals(record.firstName) &&
        lastName.equals(record.lastName) &&
        gender.equals(record.gender) &&
        favoriteColor.equals(record.favoriteColor) &&
        dateOfBirth.equals(record.dateOfBirth);
  }
}
