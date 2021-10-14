package ee.taltech.backendapi.service;

import ee.taltech.backendapi.dto.AnnualCryptoResult;
import ee.taltech.backendapi.dto.CryptoResult;
import ee.taltech.backendapi.service.alpha.DataPoint;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CryptoCalculator {

    public CryptoResult calculate(DataPoint dataPoint) {
        CryptoResult cryptoResult = new CryptoResult();
        cryptoResult.setDate(dataPoint.getDate());
        cryptoResult.setLow(dataPoint.getLow().setScale(2, RoundingMode.HALF_EVEN));
        cryptoResult.setHigh(dataPoint.getHigh().setScale(2, RoundingMode.HALF_EVEN));
        cryptoResult.setAbsoluteDifference(calculateDifference(dataPoint.getLow(), dataPoint.getHigh()));
        return cryptoResult;
    }

    public AnnualCryptoResult calculateAnnual(List<DataPoint> dataPoints) {
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
        cryptoResult.setAbsoluteDifference(calculateDifference(maximum, minimum));
        return cryptoResult;
    }

    BigDecimal calculateDifference(BigDecimal a, BigDecimal b) {
        return a.subtract(b).setScale(2, RoundingMode.HALF_EVEN).abs();
    }
}
