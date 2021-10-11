package ee.taltech.backendapi.service;

import ee.taltech.backendapi.dto.CryptoResult;
import ee.taltech.backendapi.service.alpha.DataPoint;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CryptoCalculator {

    public CryptoResult calculate(DataPoint dataPoint) {
        CryptoResult cryptoResult = new CryptoResult();
        cryptoResult.setDate(dataPoint.getDate());
        cryptoResult.setLow(dataPoint.getLow().setScale(2, RoundingMode.HALF_EVEN));
        cryptoResult.setHigh(dataPoint.getHigh().setScale(2, RoundingMode.HALF_EVEN));
        cryptoResult.setAbsoluteDifference(calculateDifference(dataPoint));
        return cryptoResult;
    }

    BigDecimal calculateDifference(DataPoint dataPoint) {
        return dataPoint.getHigh().subtract(dataPoint.getLow()).setScale(2, RoundingMode.HALF_EVEN).abs();
    }
}
