package ee.taltech.backendapi.service.alpha;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DailyDataPoint {

    private final LocalDate month;
    private final BigDecimal low;
    private final BigDecimal high;
    private final BigDecimal difference;

    public DailyDataPoint(LocalDate month, BigDecimal low, BigDecimal high) {
        this.month = month;
        this.low = low;
        this.high = high;
        this.difference = BigDecimal.valueOf(0);
    }

    public LocalDate getMonth() {
        return month;
    }

    public BigDecimal getLow() {
        return low;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public BigDecimal getDifference() {
        return difference;
    }

    @Override
    public String toString() {
        return "DailyDataPoint{" +
                "month=" + month +
                ", low=" + low +
                ", high=" + high +
                ", difference=" + difference +
                '}';
    }
}
