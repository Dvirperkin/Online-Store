package hac.ex4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentsController {
    @GetMapping("fragments/navbar")
    public String getNav(){
        return "fragments/navigator";
    }
    @GetMapping("fragments/searchbar")

    public String getSearchbar(){
        return "fragments/searchbar";
    }
}
