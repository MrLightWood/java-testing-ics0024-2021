package ee.taltech.backendapi.service;

import ee.taltech.backendapi.dto.FinanceResult;
import ee.taltech.backendapi.service.alpha.AlphaVantageApi;
import ee.taltech.backendapi.service.alpha.DailyDataPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FinanceService {

    @Autowired
    private FinanceCalculator financeCalculator;
    @Autowired
    private AlphaVantageApi alphaVantageApi;

    public List<FinanceResult> result(){
        List<FinanceResult> results = new ArrayList<FinanceResult>();
        List<DailyDataPoint> response = alphaVantageApi.queryForMonthly();
        System.out.println(response);
        for (DailyDataPoint res:response
             ) {
            System.out.println(res);
        }
        for (DailyDataPoint month: response
             ) {
            results.add(financeCalculator.calculate(month));
        }
        return results;
    }
}
