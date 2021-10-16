package ee.taltech.backendapi.controller;

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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MonthlyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlphaVantageApi alphaVantageApi;

    @Test
    void monthlyCryptoControllerForThreeMonths() throws Exception {
        Mockito.when(alphaVantageApi.query("DIGITAL_CURRENCY_MONTHLY", "Time Series (Digital Currency Monthly)")).thenReturn(
                List.of(
                        new DataPoint(LocalDate.of(2021, 10, 31), new BigDecimal(5000), new BigDecimal(10000)),
                        new DataPoint(LocalDate.of(2021, 11, 30), new BigDecimal(150), new BigDecimal(1000)),
                        new DataPoint(LocalDate.of(2021, 12, 31), new BigDecimal(10), new BigDecimal(50))
                )
        );

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/monthly"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].low", is(5000.0)))
                .andExpect(jsonPath("$[0].high", is(10000.0)))
                .andExpect(jsonPath("$[0].absoluteDifference", is(5000.0)))
                .andExpect(jsonPath("$[1].low", is(150.0)))
                .andExpect(jsonPath("$[1].high", is(1000.0)))
                .andExpect(jsonPath("$[1].absoluteDifference", is(850.0)))
                .andExpect(jsonPath("$[2].low", is(10.0)))
                .andExpect(jsonPath("$[2].high", is(50.0)))
                .andExpect(jsonPath("$[2].absoluteDifference", is(40.0)));
    }

    @Test
    void monthlyCryptoControllerForOneMonth() throws Exception {
        Mockito.when(alphaVantageApi.query("DIGITAL_CURRENCY_MONTHLY", "Time Series (Digital Currency Monthly)")).thenReturn(
                List.of(
                        new DataPoint(LocalDate.of(2021, 5, 31), new BigDecimal(54), new BigDecimal(106))
                 )
        );

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/monthly"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].low", is(54.0)))
                .andExpect(jsonPath("$[0].high", is(106.0)))
                .andExpect(jsonPath("$[0].absoluteDifference", is(52.0)));
    }
}
