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

    public List<DataPoint> query(String requestKey, String getObjectKey) {
        String body = alphaVantageClient.query(requestKey);
        JSONObject jsonObject = new JSONObject(body);
        if (jsonObject.has("Error Message")) {
            //todo do sth about it
        }
        JSONObject cryptoToUSDRate = jsonObject.getJSONObject(getObjectKey);
        List<DataPoint> dataPointList = new ArrayList<>();
        for (String key : cryptoToUSDRate.keySet()) {
            JSONObject dataPoint = cryptoToUSDRate.getJSONObject(key);
            dataPointList.add(new DataPoint(
                            LocalDate.parse(key),
                            dataPoint.getBigDecimal("3a. low (USD)"),
                            dataPoint.getBigDecimal("2a. high (USD)")
                    )
            );
        }
        return dataPointList;
    }
}
