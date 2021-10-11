package ee.taltech.backendapi.service;

import ee.taltech.backendapi.dto.CryptoResult;
import ee.taltech.backendapi.service.alpha.MonthlyDataPoint;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CryptoCalculator {

    public CryptoResult calculateMonthly(MonthlyDataPoint monthlyDataPoint) {
        CryptoResult cryptoResult = new CryptoResult();
        cryptoResult.setDate(monthlyDataPoint.getMonth());
        cryptoResult.setLow(monthlyDataPoint.getLow().setScale(2, RoundingMode.HALF_EVEN));
        cryptoResult.setHigh(monthlyDataPoint.getHigh().setScale(2, RoundingMode.HALF_EVEN));
        cryptoResult.setAbsoluteDifference(calculateDifference(monthlyDataPoint));
        return cryptoResult;
    }

    BigDecimal calculateDifference(MonthlyDataPoint monthlyDataPoint) {
        return monthlyDataPoint.getHigh().subtract(monthlyDataPoint.getLow()).setScale(2, RoundingMode.HALF_EVEN).abs();
    }
}
