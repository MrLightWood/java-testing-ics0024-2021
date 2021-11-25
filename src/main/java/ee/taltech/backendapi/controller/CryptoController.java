package ee.taltech.backendapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ee.taltech.backendapi.dto.AnnualCryptoResult;
import ee.taltech.backendapi.dto.CryptoResult;
import ee.taltech.backendapi.service.CryptoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping
@RestController
public class CryptoController {
    Logger logger = LoggerFactory.getLogger(CryptoController.class);

    @Autowired
    private CryptoService cryptoService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping({"/monthly", "monthly1"})
    public List<CryptoResult> monthlyDifference() throws JsonProcessingException {
        logger.info("Requesting monthly");
        List<CryptoResult> results = cryptoService.getData("monthly");
        logger.info("Monthly change result {}", objectMapper.writeValueAsString(results));
        return results;
    }

    @GetMapping("/weekly")
    public List<CryptoResult> weeklyDifference() throws JsonProcessingException {
        logger.info("Requesting weekly");
        List<CryptoResult> results = cryptoService.getData("weekly");
        logger.info("Weekly change result {}", objectMapper.writeValueAsString(results));
        return results;
    }

    @GetMapping("/annual")
    public List<AnnualCryptoResult> annualDifference() throws JsonProcessingException {
        logger.info("Requesting annual");
        List<AnnualCryptoResult> results = cryptoService.getAnnual();
        logger.info("Annual change result {}", objectMapper.writeValueAsString(results));
        return results;
    }
}
