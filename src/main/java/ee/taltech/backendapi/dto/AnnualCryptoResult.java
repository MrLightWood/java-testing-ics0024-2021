package ee.taltech.backendapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AnnualCryptoResult {
    private Integer year;
    private BigDecimal low;
    private BigDecimal high;
    private BigDecimal absoluteDifference;
    private String error = "";

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return this.error;
    }
}
