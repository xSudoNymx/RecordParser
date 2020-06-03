package com.example.api.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.api.model.Record;
import com.example.api.service.ParseService;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest
class ParseRecordsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ParseService parseService;

  @Test
  void PostValidRecord() throws Exception {
    Record r = new Record("Wong", "Kailum", "Male", "Orange", "11/24/2003");
    when(parseService.addRecord(anyString())).thenReturn(r);

    MvcResult result = this.mockMvc
        .perform(post("/records").contentType(MediaType.TEXT_PLAIN).content("Wong | Kailum | Male | Orange |11/24/2003"))
        .andExpect(status().isOk())
        .andReturn();

    String content = result.getResponse().getContentAsString();

    assertTrue(content.contains("Kailum"));
    assertTrue(content.contains("Wong"));
    assertTrue(content.contains("Male"));
    assertTrue(content.contains("Orange"));
    assertTrue(content.contains("2003-11-24"));
  }

  @Test
  void PostInvalidRecord() throws Exception {
    when(parseService.addRecord(anyString())).thenReturn(null);

    MvcResult result = this.mockMvc
        .perform(post("/records").contentType(MediaType.TEXT_PLAIN).content("Wong | Kailum | Male | Orange |11/24/2003"))
        .andExpect(status().isBadRequest())
        .andReturn();
    assertTrue(result.getResponse().getContentAsString().isBlank());
  }

  @Test
  void PostEmptyRecord() throws Exception {
    when(parseService.addRecord(anyString())).thenReturn(null);

    MvcResult result = this.mockMvc
        .perform(post("/records").contentType(MediaType.TEXT_PLAIN).content(""))
        .andExpect(status().isBadRequest())
        .andReturn();
    assertTrue(result.getResponse().getContentAsString().isBlank());
  }

  @Test
  void getRecordsByGender() throws Exception {
    Record r1 = new Record("Gill", "Edwin", "Male", "Blue", "05/29/1986");
    Record r2 = new Record("Bridges", "Mary", "Female", "Pink", "02/13/1997");
    Record r3 = new Record("Whitehead", "Chloe", "Female", "Navy", "01/27/2002");
    Record r4 = new Record("Fuller", "Sebastian", "Male", "Brown", "01/09/1987");
    when(parseService.getRecordByGender()).thenReturn(Arrays.asList(r1, r2, r3, r4));

    MvcResult result = this.mockMvc
        .perform(get("/records/gender"))
        .andExpect(status().isOk())
        .andReturn();

    String content = result.getResponse().getContentAsString();

    assertTrue(content.contains("Gill"));
    assertTrue(content.contains("Bridges"));
    assertTrue(content.contains("Whitehead"));
    assertTrue(content.contains("Fuller"));
  }

  @Test
  void getRecordsByBirthday() throws Exception {
    Record r1 = new Record("Gill", "Edwin", "Male", "Blue", "05/29/1986");
    Record r2 = new Record("Bridges", "Mary", "Female", "Pink", "02/13/1997");
    Record r3 = new Record("Whitehead", "Chloe", "Female", "Navy", "01/27/2002");
    Record r4 = new Record("Fuller", "Sebastian", "Male", "Brown", "01/09/1987");
    when(parseService.getRecordByBirthday()).thenReturn(Arrays.asList(r1, r2, r3, r4));

    MvcResult result = this.mockMvc
        .perform(get("/records/birthdate"))
        .andExpect(status().isOk())
        .andReturn();

    String content = result.getResponse().getContentAsString();

    assertTrue(content.contains("Gill"));
    assertTrue(content.contains("Bridges"));
    assertTrue(content.contains("Whitehead"));
    assertTrue(content.contains("Fuller"));
  }

  @Test
  void getRecordsByName() throws Exception {
    Record r1 = new Record("Gill", "Edwin", "Male", "Blue", "05/29/1986");
    Record r2 = new Record("Bridges", "Mary", "Female", "Pink", "02/13/1997");
    Record r3 = new Record("Whitehead", "Chloe", "Female", "Navy", "01/27/2002");
    Record r4 = new Record("Fuller", "Sebastian", "Male", "Brown", "01/09/1987");
    when(parseService.getRecordByName()).thenReturn(Arrays.asList(r1, r2, r3, r4));

    MvcResult result = this.mockMvc
        .perform(get("/records/name"))
        .andExpect(status().isOk())
        .andReturn();

    String content = result.getResponse().getContentAsString();

    assertTrue(content.contains("Gill"));
    assertTrue(content.contains("Bridges"));
    assertTrue(content.contains("Whitehead"));
    assertTrue(content.contains("Fuller"));
  }
}