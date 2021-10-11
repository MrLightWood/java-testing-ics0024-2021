package ee.taltech.backendapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ee.taltech.backendapi.dto.FinanceResult;
import ee.taltech.backendapi.service.FinanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/crypto")
@RestController
public class CryptoController {
    Logger logger = LoggerFactory.getLogger(CryptoController.class);

    @Autowired
    private FinanceService financeService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public List<FinanceResult> dailyChange() throws JsonProcessingException {
        logger.info("Requesting monthly");
        List<FinanceResult> results = financeService.result();
        logger.info("Monthly change result {}", objectMapper.writeValueAsString(results));
        return results;
    }
}
