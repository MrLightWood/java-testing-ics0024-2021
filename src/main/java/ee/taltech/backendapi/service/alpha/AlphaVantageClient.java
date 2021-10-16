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

    public String query(String queryFunction) {
        String url = format("%s?function=%s&symbol=BTC&market=USD&apikey=%s",
                alphaVantageConfig.getUrl(),
                queryFunction,
                alphaVantageConfig.getKey());
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        if (!entity.getStatusCode().is2xxSuccessful()) {
            return "{ \"Error Message\": \"Could not fetch data from API\" }";
        }
        System.out.println(entity.getBody());
        return entity.getBody();
    }
}
