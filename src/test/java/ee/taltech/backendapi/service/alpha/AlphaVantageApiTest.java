package ee.taltech.backendapi.service.alpha;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class AlphaVantageApiTest {
    @Autowired
    private AlphaVantageApi alphaVantageApi;
    @MockBean
    private AlphaVantageClient alphaVantageClient;

    @Test
    void checkError() throws Exception {
        //todo fix this
        String value = testData("testErrorData.json");
        when(alphaVantageClient.query(anyString())).thenReturn(value);
        List<DataPoint> dataPoints = alphaVantageApi.query("DIGITAL_CURRENCY_MONTHLY", "Time Series (Digital Currency Monthly)");

        assertThat(dataPoints).hasSize(1);
        assertEquals(dataPoints.get(0).getError(), "Invalid API call. Please retry or visit the documentation (https://www.alphavantage.co/documentation/) for DIGITAL_CURRENCY_MONTHLY.");
    }

    @Test
    void getMonthly() throws Exception {
        String value = testData("testMonthlyData.json");
        when(alphaVantageClient.query(anyString())).thenReturn(value);
        List<DataPoint> dataPoints = alphaVantageApi.query("DIGITAL_CURRENCY_MONTHLY", "Time Series (Digital Currency Monthly)");

        assertThat(dataPoints).hasSize(33);
    }

    private String testData(String path) throws IOException {
        Resource resource = new ClassPathResource(path);
        try (InputStream inputStream = resource.getInputStream()) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}