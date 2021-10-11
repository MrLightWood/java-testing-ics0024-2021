package ee.taltech.backendapi.dto;

import java.math.BigDecimal;

public class AnnualCryptoResult {
    private Integer year;
    private BigDecimal low;
    private BigDecimal high;
    private BigDecimal absoluteDifference;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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
