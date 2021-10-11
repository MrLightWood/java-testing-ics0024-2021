package ee.taltech.backendapi.service.alpha;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlphaVantageApi {

    @Autowired
    private AlphaVantageClient alphaVantageClient;

    public List<DailyDataPoint> queryForMonthly() {
        String body = alphaVantageClient.query();
        JSONObject jsonObject = new JSONObject(body);
        if (jsonObject.has("Error Message")) {
            //todo do sth about it
        }
        JSONObject cryptoToUSDRate = jsonObject.getJSONObject("Time Series (Digital Currency Monthly)");
        List<DailyDataPoint> dataPointList = new ArrayList<>();
        for (String key : cryptoToUSDRate.keySet()) {
            JSONObject dataPoint = cryptoToUSDRate.getJSONObject(key);
            dataPointList.add(new DailyDataPoint(
                            LocalDate.parse(key),
                            dataPoint.getBigDecimal("2a. high (USD)"),
                            dataPoint.getBigDecimal("3a. low (USD)")
                    )
            );
        }
        System.out.println(dataPointList);
        return dataPointList;
    }
}
