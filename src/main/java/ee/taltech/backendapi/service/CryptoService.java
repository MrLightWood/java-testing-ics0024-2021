package ee.taltech.backendapi.service;

import ee.taltech.backendapi.dto.AnnualCryptoResult;
import ee.taltech.backendapi.dto.CryptoResult;
import ee.taltech.backendapi.service.alpha.AlphaVantageApi;
import ee.taltech.backendapi.service.alpha.DataPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Service
public class CryptoService {

    @Autowired
    private CryptoCalculator cryptoCalculator;
    @Autowired
    private AlphaVantageApi alphaVantageApi;

    public List<CryptoResult> getData(String type) {
        String requestKey = "";
        String objectKey = "";

        switch(type.toLowerCase(Locale.ROOT))
        {
            case "weekly":
                requestKey = "DIGITAL_CURRENCY_WEEKLY";
                objectKey = "Time Series (Digital Currency Weekly)";
                break;
            case "monthly":
                requestKey = "DIGITAL_CURRENCY_MONTHLY";
                objectKey = "Time Series (Digital Currency Monthly)";
                break;
        }

        List<CryptoResult> results = new ArrayList<CryptoResult>();
        List<DataPoint> response = alphaVantageApi.query(requestKey, objectKey);

        if(hasError(response)) {
            CryptoResult errorObject = new CryptoResult();
            errorObject.setError(response.get(0).getError());
            results.add(errorObject);
            return results;
        }

        for (DataPoint data : response
        ) {
            results.add(cryptoCalculator.calculate(data));
        }
        return results;
    }

    public List<AnnualCryptoResult> getAnnual() {
        List<AnnualCryptoResult> results = new ArrayList<AnnualCryptoResult>();
        List<DataPoint> response = alphaVantageApi.query("DIGITAL_CURRENCY_MONTHLY", "Time Series (Digital Currency Monthly)");

        if(hasError(response)) {
            AnnualCryptoResult errorObject = new AnnualCryptoResult();
            errorObject.setError(response.get(0).getError());
            results.add(errorObject);
            return results;
        }

        HashMap<Integer, ArrayList<DataPoint>> years = new HashMap<Integer, ArrayList<DataPoint>>();

        for (DataPoint month : response
        ) {
            int year = month.getDate().getYear();
            years.computeIfAbsent(year, k -> new ArrayList<DataPoint>());
            years.get(year).add(month);
        }

        for (int key : years.keySet()
        ) {
            AnnualCryptoResult cryptoResult = cryptoCalculator.calculateAnnual(years.get(key));
            cryptoResult.setYear(key);
            results.add(
                    cryptoResult
            );
        }

        return results;
    }

    private boolean hasError(List<DataPoint> response) {
        if(response.size() == 1) {
            if(!response.get(0).getError().isEmpty()){
                return true;
            }
        }

        return false;
    }
}
