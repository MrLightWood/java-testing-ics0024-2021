package ee.taltech.backendapi.service;

import ee.taltech.backendapi.dto.AnnualCryptoResult;
import ee.taltech.backendapi.dto.CryptoResult;
import ee.taltech.backendapi.service.alpha.DataPoint;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CryptoCalculatorTest {
    @Autowired
    private CryptoCalculator cryptoCalculator;

    @Test
    void calculateNullDataPoint() throws Exception {
        DataPoint input = new DataPoint(LocalDate.now(), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        CryptoResult expected = new CryptoResult();
        expected.setDate(LocalDate.now());
        expected.setHigh(BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_EVEN));
        expected.setLow(BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_EVEN));
        expected.setAbsoluteDifference(BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_EVEN).abs());

        CryptoResult actual = cryptoCalculator.calculate(input);

        assertTrue(new ReflectionEquals(expected).matches(actual));
    }

    @Test
    void calculationWithPositiveNumbers() throws Exception {
        DataPoint input = new DataPoint(LocalDate.now(), BigDecimal.valueOf(42), BigDecimal.valueOf(777));
        CryptoResult expected = new CryptoResult();
        expected.setDate(LocalDate.now());
        expected.setLow(BigDecimal.valueOf(42).setScale(2, RoundingMode.HALF_EVEN));
        expected.setHigh(BigDecimal.valueOf(777).setScale(2, RoundingMode.HALF_EVEN));
        expected.setAbsoluteDifference(BigDecimal.valueOf(735).setScale(2, RoundingMode.HALF_EVEN).abs());

        CryptoResult actual = cryptoCalculator.calculate(input);

        assertTrue(new ReflectionEquals(expected).matches(actual));
    }

    @Test
    void calculationWithNegativeNumbers() throws Exception {
        DataPoint input = new DataPoint(LocalDate.now(), BigDecimal.valueOf(-145), BigDecimal.valueOf(-32));
        CryptoResult expected = new CryptoResult();
        expected.setDate(LocalDate.now());
        expected.setLow(BigDecimal.valueOf(-145).setScale(2, RoundingMode.HALF_EVEN));
        expected.setHigh(BigDecimal.valueOf(-32).setScale(2, RoundingMode.HALF_EVEN));
        expected.setAbsoluteDifference(BigDecimal.valueOf(113).setScale(2, RoundingMode.HALF_EVEN).abs());

        CryptoResult actual = cryptoCalculator.calculate(input);

        assertTrue(new ReflectionEquals(expected).matches(actual));
    }

    @Test
    void calculationWithNegativeAndPositiveNumbers_1() throws Exception {
        DataPoint input = new DataPoint(LocalDate.now(), BigDecimal.valueOf(-18), BigDecimal.valueOf(64));
        CryptoResult expected = new CryptoResult();
        expected.setDate(LocalDate.now());
        expected.setLow(BigDecimal.valueOf(-18).setScale(2, RoundingMode.HALF_EVEN));
        expected.setHigh(BigDecimal.valueOf(64).setScale(2, RoundingMode.HALF_EVEN));
        expected.setAbsoluteDifference(BigDecimal.valueOf(82).setScale(2, RoundingMode.HALF_EVEN).abs());

        CryptoResult actual = cryptoCalculator.calculate(input);

        assertTrue(new ReflectionEquals(expected).matches(actual));
    }

    void calculationWithNegativeAndPositiveNumbers_2() throws Exception {
        DataPoint input = new DataPoint(LocalDate.now(), BigDecimal.valueOf(15), BigDecimal.valueOf(-48));
        CryptoResult expected = new CryptoResult();
        expected.setDate(LocalDate.now());
        expected.setLow(BigDecimal.valueOf(15).setScale(2, RoundingMode.HALF_EVEN));
        expected.setHigh(BigDecimal.valueOf(-48).setScale(2, RoundingMode.HALF_EVEN));
        expected.setAbsoluteDifference(BigDecimal.valueOf(63).setScale(2, RoundingMode.HALF_EVEN).abs());

        CryptoResult actual = cryptoCalculator.calculate(input);

        assertTrue(new ReflectionEquals(expected).matches(actual));
    }

    void calculateAnnual() throws Exception {
        List<DataPoint> input = List.of(
                new DataPoint(
                        LocalDate.now(),
                        BigDecimal.valueOf(15),
                        BigDecimal.valueOf(45)
                ),
                new DataPoint(
                        LocalDate.now(),
                        BigDecimal.valueOf(4),
                        BigDecimal.valueOf(32)
                ),
                new DataPoint(
                        LocalDate.now(),
                        BigDecimal.valueOf(24),
                        BigDecimal.valueOf(435)
                )
        );

        AnnualCryptoResult expected = new AnnualCryptoResult();
        expected.setYear(2021);
        expected.setLow(BigDecimal.valueOf(4));
        expected.setHigh(BigDecimal.valueOf(435));
        expected.setAbsoluteDifference(BigDecimal.valueOf(431));

        AnnualCryptoResult actual = cryptoCalculator.calculateAnnual(input);
        assertTrue(new ReflectionEquals(expected).matches(actual));
    }
}
