package ee.taltech.backendapi.controller;

import ee.taltech.backendapi.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/example")
@RestController
public class ExampleController {

    @GetMapping
    public String string(){
        return "This is an example get";
    }

}
