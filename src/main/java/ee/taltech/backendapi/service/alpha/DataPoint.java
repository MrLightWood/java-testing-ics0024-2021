package ee.taltech.backendapi.service.alpha;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class DataPoint {

    private final LocalDate date;
    private final BigDecimal low;
    private final BigDecimal high;
    private String error = "";

    public DataPoint(LocalDate date, BigDecimal low, BigDecimal high) {
        this.date = date;
        this.low = low;
        this.high = high;
    }

    public void setError(String error) {
        this.error = error;
    }
}
