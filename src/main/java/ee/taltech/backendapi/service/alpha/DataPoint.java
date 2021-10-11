package ee.taltech.backendapi.service.alpha;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DataPoint {

    private final LocalDate date;
    private final BigDecimal low;
    private final BigDecimal high;

    public DataPoint(LocalDate date, BigDecimal low, BigDecimal high) {
        this.date = date;
        this.low = low;
        this.high = high;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getLow() {
        return low;
    }

    public BigDecimal getHigh() {
        return high;
    }
}
