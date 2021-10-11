package ee.taltech.backendapi.service.alpha;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DailyDataPoint {

    private LocalDate date;
    private BigDecimal open;
    private BigDecimal close;

    public DailyDataPoint(LocalDate date, BigDecimal open, BigDecimal close) {
        this.date = date;
        this.open = open;
        this.close = close;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public BigDecimal getClose() {
        return close;
    }
}
