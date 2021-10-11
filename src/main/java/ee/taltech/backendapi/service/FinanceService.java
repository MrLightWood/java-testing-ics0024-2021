package ee.taltech.backendapi.service;

import ee.taltech.backendapi.dto.FinanceResult;
import ee.taltech.backendapi.service.alpha.AlphaVantageApi;
import ee.taltech.backendapi.service.alpha.DailyDataPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceService {

    @Autowired
    private FinanceCalculator financeCalculator;
    @Autowired
    private AlphaVantageApi alphaVantageApi;

    public FinanceResult result(String stock){
        List<DailyDataPoint> response = alphaVantageApi.queryForDaily(stock);
        return financeCalculator.calculate(response);
    }
}
