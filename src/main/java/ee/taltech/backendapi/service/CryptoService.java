package ee.taltech.backendapi.service;

import ee.taltech.backendapi.dto.AnnualCryptoResult;
import ee.taltech.backendapi.dto.CryptoResult;
import ee.taltech.backendapi.service.alpha.AlphaVantageApi;
import ee.taltech.backendapi.service.alpha.DataPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

@Service
public class CryptoService {

    @Autowired
    private CryptoCalculator cryptoCalculator;
    @Autowired
    private AlphaVantageApi alphaVantageApi;

    public List<CryptoResult> getMonthly() {
        List<CryptoResult> results = new ArrayList<CryptoResult>();
        List<DataPoint> response = alphaVantageApi.query("DIGITAL_CURRENCY_MONTHLY", "Time Series (Digital Currency Monthly)");

        for (DataPoint month : response
        ) {
            results.add(cryptoCalculator.calculate(month));
        }
        return results;
    }

    public List<CryptoResult> getWeekly() {
        List<CryptoResult> results = new ArrayList<CryptoResult>();
        List<DataPoint> response = alphaVantageApi.query("DIGITAL_CURRENCY_WEEKLY", "Time Series (Digital Currency Weekly)");

        for (DataPoint week : response
        ) {
            results.add(cryptoCalculator.calculate(week));
        }
        return results;
    }

    public List<AnnualCryptoResult> getAnnual() {
        List<AnnualCryptoResult> results = new ArrayList<AnnualCryptoResult>();
        List<DataPoint> response = alphaVantageApi.query("DIGITAL_CURRENCY_MONTHLY", "Time Series (Digital Currency Monthly)");

        HashMap<Integer, ArrayList<DataPoint>> years = new HashMap<Integer, ArrayList<DataPoint>>();

        for (DataPoint month : response
        ) {
            int year = month.getDate().getYear();
            if (years.get(year) != null) {
                years.get(year).add(month);
            } else {
                years.put(year, new ArrayList<DataPoint>());
                years.get(year).add(month);
            }
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
}
