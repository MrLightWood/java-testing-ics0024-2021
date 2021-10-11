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

    public FinanceResult calculate(List<DailyDataPoint> dailyDataPoints){
        Optional<DailyDataPoint> dailyDataPointOp = dailyDataPoints.stream().max(Comparator.comparing(DailyDataPoint::getDate));
        if (dailyDataPointOp.isEmpty()){
            return new FinanceResult();
        }
        DailyDataPoint dailyDataPoint = dailyDataPointOp.get();
        FinanceResult financeResult = new FinanceResult();
        financeResult.setDate(dailyDataPoint.getDate());
        financeResult.setOpen(dailyDataPoint.getOpen().setScale(2, RoundingMode.HALF_EVEN));
        financeResult.setClose(dailyDataPoint.getClose().setScale(2, RoundingMode.HALF_EVEN));

        financeResult.setChange(calculateChange(dailyDataPoint));
        return financeResult;
    }

    BigDecimal calculateChange(DailyDataPoint dailyDataPoint) {
        return dailyDataPoint.getClose().subtract(dailyDataPoint.getOpen()).setScale(2, RoundingMode.HALF_EVEN);
    }
}
