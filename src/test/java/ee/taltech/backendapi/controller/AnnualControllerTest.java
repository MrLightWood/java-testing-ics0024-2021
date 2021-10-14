package ee.taltech.backendapi.controller;

import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.taltech.backendapi.service.alpha.AlphaVantageApi;
import ee.taltech.backendapi.service.alpha.DataPoint;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class AnnualControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ObjectMapper objectMapper;

    @MockBean
    private AlphaVantageApi alphaVantageApi;

    @Test
    void annualCryptoControllerForOneYear() throws Exception {
        Mockito.when(alphaVantageApi.query("DIGITAL_CURRENCY_MONTHLY", "Time Series (Digital Currency Monthly)")).thenReturn(
                List.of(
                        new DataPoint(LocalDate.of(2020, 1, 31), new BigDecimal(100), new BigDecimal(1000)),
                        new DataPoint(LocalDate.of(2020, 2, 28), new BigDecimal(10), new BigDecimal(100)),
                        new DataPoint(LocalDate.of(2020, 3, 31), new BigDecimal(1), new BigDecimal(10))
                )
        );

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/annual"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].low", is(1)))
                .andExpect(jsonPath("$[0].high", is(1000)))
                .andExpect(jsonPath("$[0].absoluteDifference", is(999.0)));
    }

    @Test
    void annualCryptoControllerForThreeYears() throws Exception {
        Mockito.when(alphaVantageApi.query("DIGITAL_CURRENCY_MONTHLY", "Time Series (Digital Currency Monthly)")).thenReturn(
                List.of(
                        new DataPoint(LocalDate.of(2020, 1, 31), new BigDecimal(100), new BigDecimal(1000)),
                        new DataPoint(LocalDate.of(2020, 2, 28), new BigDecimal(10), new BigDecimal(100)),
                        new DataPoint(LocalDate.of(2020, 3, 31), new BigDecimal(1), new BigDecimal(10)),
                        new DataPoint(LocalDate.of(2021, 1, 31), new BigDecimal(500), new BigDecimal(5000)),
                        new DataPoint(LocalDate.of(2021, 2, 28), new BigDecimal(50), new BigDecimal(500)),
                        new DataPoint(LocalDate.of(2021, 3, 31), new BigDecimal(5), new BigDecimal(50)),
                        new DataPoint(LocalDate.of(2022, 1, 31), new BigDecimal(800), new BigDecimal(8000)),
                        new DataPoint(LocalDate.of(2022, 2, 28), new BigDecimal(80), new BigDecimal(800)),
                        new DataPoint(LocalDate.of(2022, 3, 31), new BigDecimal(8), new BigDecimal(80))
                )
        );

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/annual"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].low", is(1)))
                .andExpect(jsonPath("$[0].high", is(1000)))
                .andExpect(jsonPath("$[0].absoluteDifference", is(999.0)))
                .andExpect(jsonPath("$[1].low", is(5)))
                .andExpect(jsonPath("$[1].high", is(5000)))
                .andExpect(jsonPath("$[1].absoluteDifference", is(4995.0)))
                .andExpect(jsonPath("$[2].low", is(8)))
                .andExpect(jsonPath("$[2].high", is(8000)))
                .andExpect(jsonPath("$[2].absoluteDifference", is(7992.0)));
    }
}
