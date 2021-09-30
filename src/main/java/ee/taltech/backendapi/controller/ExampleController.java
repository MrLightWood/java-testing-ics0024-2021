package ee.taltech.backendapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/example")
@RestController
public class ExampleController {
    @GetMapping
    public String string(){
        return "This is an example get";
    }

}
