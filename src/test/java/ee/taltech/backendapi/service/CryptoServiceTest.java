package ee.taltech.backendapi.service;

import ee.taltech.backendapi.dto.CryptoResult;
import ee.taltech.backendapi.service.alpha.DataPoint;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CryptoServiceTest {
    @Autowired
    private CryptoService cryptoService;

    @Test
    void getMonthlyAuto() throws Exception {
        String value = testData("testMonthlyData.json");
        JSONObject jsonObject = new JSONObject(value);

        JSONObject cryptoToUSDRate = jsonObject.getJSONObject("Time Series (Digital Currency Monthly)");
        List<DataPoint> dataPointList = new ArrayList<>();
        for (String key : cryptoToUSDRate.keySet()) {
            JSONObject dataPoint = cryptoToUSDRate.getJSONObject(key);
            dataPointList.add(new DataPoint(
                            LocalDate.parse(key),
                            dataPoint.getBigDecimal("3a. low (USD)"),
                            dataPoint.getBigDecimal("2a. high (USD)")
                    )
            );
        }

        List<CryptoResult> expected = new ArrayList<CryptoResult>();

        for (DataPoint month : dataPointList
        ) {
            CryptoResult cryptoResult = new CryptoResult();
            cryptoResult.setDate(month.getDate());
            cryptoResult.setLow(month.getLow().setScale(2, RoundingMode.HALF_EVEN));
            cryptoResult.setHigh(month.getHigh().setScale(2, RoundingMode.HALF_EVEN));
            cryptoResult.setAbsoluteDifference(month.getLow().subtract(month.getHigh()).setScale(2, RoundingMode.HALF_EVEN).abs());
            expected.add(cryptoResult);
        }

        List<CryptoResult> actual = cryptoService.getData("monthly");

        assertFalse(new ReflectionEquals(expected).matches(actual));
    }

    @Test
    void getMonthlyManual() throws Exception {

        DataPoint month = new DataPoint(LocalDate.parse("2021-10-16"), BigDecimal.valueOf(43283.03), BigDecimal.valueOf(62933.00));
        CryptoResult expected = new CryptoResult();
        expected.setDate(month.getDate());
        expected.setLow(month.getLow().setScale(2, RoundingMode.HALF_EVEN));
        expected.setHigh(month.getHigh().setScale(2, RoundingMode.HALF_EVEN));
        expected.setAbsoluteDifference(BigDecimal.valueOf(19649.97).setScale(2, RoundingMode.HALF_EVEN).abs());

        List<CryptoResult> actual = cryptoService.getData("monthly");

        CryptoResult actualObject = actual.stream().filter(e -> e.getDate().isEqual(LocalDate.parse("2021-10-16"))).findAny().orElse(null);
        assertFalse(new ReflectionEquals(expected).matches(actualObject));
    }

    private String testData(String path) throws IOException {
        Resource resource = new ClassPathResource(path);
        try (InputStream inputStream = resource.getInputStream()) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
