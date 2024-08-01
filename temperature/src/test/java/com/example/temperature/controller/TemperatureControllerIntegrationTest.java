package com.example.temperature.controller;

import com.example.temperature.dto.TemperatureSensorDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TemperatureControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void pageAllTemperatureDataShouldReturnPagedRecords() throws Exception {

        ResultActions resultActions = mockMvc.perform(
                 get("/api/getAll")
                .param("page","0")
                .param("size","5")
                .param("sort","temperature,asc")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$").exists());
        resultActions.andExpect(jsonPath("$.content[0].temperature").value(20));
        resultActions.andExpect(jsonPath("$.content[1].temperature").value(21));
        resultActions.andExpect(jsonPath("$.content[0].timestamp").value("2023-07-26"));
        resultActions.andExpect(jsonPath("$.content[1].timestamp").value("2023-07-28"));
        resultActions.andExpect(jsonPath("$.totalPages").value(2));
        resultActions.andExpect(jsonPath("$.size").value(5));
        resultActions.andExpect(jsonPath("$.totalElements").isNumber());
        resultActions.andExpect(jsonPath("$.totalPages").isNumber());
    }

    @Test
    public void registrySensorData() throws Exception{

        TemperatureSensorDto dto = new TemperatureSensorDto();

        dto.setTemperature(25);
        dto.setTimestamp(LocalDate.now());

        String sensorJson = mapper.writeValueAsString(dto); // transformar um objeto em uma string

        mockMvc.perform(post("/api/registry")
                .contentType(MediaType.APPLICATION_JSON)
                .content(sensorJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.temperature").value(25))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    public void testUpdateSensor_Success() throws Exception{

        TemperatureSensorDto dto = new TemperatureSensorDto();

        dto.setTemperature(25);
        dto.setTimestamp(LocalDate.now());

        String sensorJson = mapper.writeValueAsString(dto);

        mockMvc.perform(put("/api/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(sensorJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("OK"))
                .andExpect(jsonPath("$.statusMsg").value("Update was realized!"));
    }

    @Test
    public void testUpdateSensor_Failed() throws Exception{

        TemperatureSensorDto dto = new TemperatureSensorDto();

        dto.setTemperature(20);
        dto.setTimestamp(LocalDate.of(2023, 7, 26));

        String sensorJson = mapper.writeValueAsString(dto);

        mockMvc.perform(put("/api/update/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(sensorJson))
                .andExpect(status().isExpectationFailed())
                .andExpect(jsonPath("$.statusCode").value("EXPECTATION_FAILED"))
                .andExpect(jsonPath("$.statusMsg").value("update was not realized!"));

    }

    @Test
    public void testDeleteSensor_Sucess() throws Exception{

        mockMvc.perform(delete("/api/delete/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("OK"))
                .andExpect(jsonPath("$.statusMsg").value("Delete was realized!"));
    }

    @Test
    public void testDeleteSensor_Failed() throws Exception{

        mockMvc.perform(delete("/api/delete/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isExpectationFailed())
                .andExpect(jsonPath("$.statusCode").value("EXPECTATION_FAILED"))
                .andExpect(jsonPath("$.statusMsg").value("Delete was not realized!"));
    }


}
