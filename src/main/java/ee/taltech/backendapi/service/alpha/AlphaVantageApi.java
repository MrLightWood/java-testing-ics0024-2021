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

    public List<DailyDataPoint> queryForDaily(String stock) {
        String body = alphaVantageClient.query(stock);
        JSONObject jsonObject = new JSONObject(body);
        if (jsonObject.has("Error Message")) {
            //todo do sth about it
        }
        JSONObject timeSeriesDaily = jsonObject.getJSONObject("Time Series (Daily)");
        List<DailyDataPoint> dataPointList = new ArrayList<>();
        for (String key : timeSeriesDaily.keySet()) {
            JSONObject dataPoint = timeSeriesDaily.getJSONObject(key);
            dataPointList.add(new DailyDataPoint(
                    LocalDate.parse(key),
                    dataPoint.getBigDecimal("1. open"),
                    dataPoint.getBigDecimal("4. close")));
        }
        return dataPointList;
    }
}
