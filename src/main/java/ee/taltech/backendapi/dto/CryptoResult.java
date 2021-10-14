package ee.taltech.backendapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CryptoResult {
    private LocalDate date;
    private BigDecimal low;
    private BigDecimal high;
    private BigDecimal absoluteDifference;

}


