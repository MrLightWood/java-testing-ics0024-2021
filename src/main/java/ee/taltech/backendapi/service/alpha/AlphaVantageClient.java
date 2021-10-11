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

    public String query(String stock) {
        String url = format("%s?function=TIME_SERIES_DAILY&symbol=%s&apikey=%s",
                alphaVantageConfig.getUrl(),
                stock,
                alphaVantageConfig.getKey());
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        if (!entity.getStatusCode().is2xxSuccessful()){
            //todo do sth about it
        }
        return entity.getBody();
    }
}
