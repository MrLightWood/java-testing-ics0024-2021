package ee.taltech.backendapi.service.alpha;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MonthlyDataPoint {

    private final LocalDate month;
    private final BigDecimal low;
    private final BigDecimal high;

    public MonthlyDataPoint(LocalDate month, BigDecimal low, BigDecimal high) {
        this.month = month;
        this.low = low;
        this.high = high;
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
}
