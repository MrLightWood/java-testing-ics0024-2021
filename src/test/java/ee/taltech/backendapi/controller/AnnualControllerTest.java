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
    void financeControllerMockTest() throws Exception {
        Mockito.when(alphaVantageApi.queryForMonthly()).thenReturn(
                List.of(new DataPoint(LocalDate.of(2020, 10, 10), BigDecimal.ONE, BigDecimal.TEN)));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/annual"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].low", is(1)))
                .andExpect(jsonPath("$[0].high", is(10)))
                .andExpect(jsonPath("$[0].absoluteDifference", is(9.0)))
        ;

    }
}
