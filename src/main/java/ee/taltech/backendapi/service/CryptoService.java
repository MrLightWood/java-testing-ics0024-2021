package ee.taltech.backendapi.service;

import ee.taltech.backendapi.dto.CryptoResult;
import ee.taltech.backendapi.service.alpha.AlphaVantageApi;
import ee.taltech.backendapi.service.alpha.MonthlyDataPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CryptoService {

    @Autowired
    private CryptoCalculator cryptoCalculator;
    @Autowired
    private AlphaVantageApi alphaVantageApi;

    public List<CryptoResult> getMonthly(){
        List<CryptoResult> results = new ArrayList<CryptoResult>();
        List<MonthlyDataPoint> response = alphaVantageApi.queryForMonthly();

        for (MonthlyDataPoint month: response
             ) {
            results.add(cryptoCalculator.calculateMonthly(month));
        }
        return results;
    }
}
