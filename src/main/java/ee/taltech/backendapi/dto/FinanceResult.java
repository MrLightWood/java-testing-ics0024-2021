package ee.taltech.backendapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FinanceResult {

    private LocalDate month;
    private BigDecimal low;
    private BigDecimal high;
    private BigDecimal difference;

    public LocalDate getMonth() {
        return month;
    }

    public void setMonth(LocalDate month) {
        this.month = month;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getDifference() {
        return difference;
    }

    public void setDifference(BigDecimal difference) {
        this.difference = difference;
    }
}
