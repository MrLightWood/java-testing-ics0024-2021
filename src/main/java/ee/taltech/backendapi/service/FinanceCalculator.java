package ee.taltech.backendapi.service;

import ee.taltech.backendapi.dto.FinanceResult;
import ee.taltech.backendapi.service.alpha.DailyDataPoint;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class FinanceCalculator {

    public FinanceResult calculate(DailyDataPoint dailyDataPoint) {
        FinanceResult financeResult = new FinanceResult();
        financeResult.setMonth(dailyDataPoint.getMonth());
        financeResult.setLow(dailyDataPoint.getLow().setScale(2, RoundingMode.HALF_EVEN));
        financeResult.setHigh(dailyDataPoint.getHigh().setScale(2, RoundingMode.HALF_EVEN));
        financeResult.setDifference(calculateDifference(dailyDataPoint));
        return financeResult;
    }

    BigDecimal calculateDifference(DailyDataPoint dailyDataPoint) {
        return dailyDataPoint.getHigh().subtract(dailyDataPoint.getLow()).setScale(2, RoundingMode.HALF_EVEN);
    }
}
