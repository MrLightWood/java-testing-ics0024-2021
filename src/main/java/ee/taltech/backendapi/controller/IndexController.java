package ee.taltech.backendapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/home")
@RestController
public class IndexController {
    @GetMapping
    public String index(){
        return "Welcome to our crypto difference calculator API. BTC to USD";
    }
}
