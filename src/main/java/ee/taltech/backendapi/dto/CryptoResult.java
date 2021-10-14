package ee.taltech.backendapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CryptoResult {
    private LocalDate date;
    private BigDecimal low;
    private BigDecimal high;
    private BigDecimal absoluteDifference;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public BigDecimal getAbsoluteDifference() {
        return absoluteDifference;
    }

    public void setAbsoluteDifference(BigDecimal difference) {
        this.absoluteDifference = difference;
    }
}


