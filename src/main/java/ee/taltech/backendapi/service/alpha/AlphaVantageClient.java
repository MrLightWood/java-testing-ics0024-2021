package ee.taltech.backendapi.service.alpha;

import ee.taltech.backendapi.configuration.AlphaVantageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.*;

@Service
public class AlphaVantageClient {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AlphaVantageConfig alphaVantageConfig;

    //?&apikey=WLSMUQFVDYXTJ2IH
    public String query() {
        String url = format("%s?function=DIGITAL_CURRENCY_MONTHLY&symbol=BTC&market=USD&apikey=%s",
                alphaVantageConfig.getUrl(),
                alphaVantageConfig.getKey());
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        if (!entity.getStatusCode().is2xxSuccessful()) {
            //todo do sth about it
        }
        return entity.getBody();
    }
}
