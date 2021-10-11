package ee.taltech.backendapi.service;

import ee.taltech.backendapi.dto.AnnualCryptoResult;
import ee.taltech.backendapi.dto.CryptoResult;
import ee.taltech.backendapi.service.alpha.DataPoint;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

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

    public AnnualCryptoResult calculateAnnual(ArrayList<DataPoint> dataPoints) {
        BigDecimal minimum = dataPoints.get(0).getLow();
        BigDecimal maximum = dataPoints.get(0).getHigh();
        for (DataPoint dataPoint : dataPoints
        ) {
            if (dataPoint.getLow().compareTo(minimum) < 0){
                minimum = dataPoint.getLow();
            }

            if (dataPoint.getHigh().compareTo(maximum) > 0){
                maximum = dataPoint.getHigh();
            }
        }

        AnnualCryptoResult cryptoResult = new AnnualCryptoResult();
        cryptoResult.setHigh(maximum);
        cryptoResult.setLow(minimum);
        cryptoResult.setAbsoluteDifference(calculateABDifference(maximum, minimum));
        return cryptoResult;
    }

    BigDecimal calculateDifference(DataPoint dataPoint) {
        return dataPoint.getHigh().subtract(dataPoint.getLow()).setScale(2, RoundingMode.HALF_EVEN).abs();
    }

    BigDecimal calculateABDifference(BigDecimal a, BigDecimal b) {
        return a.subtract(b).setScale(2, RoundingMode.HALF_EVEN).abs();
    }
}
