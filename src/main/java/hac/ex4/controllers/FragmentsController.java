package hac.ex4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentsController {
    /**
     *
     * @return - nav fragment
     */
    @GetMapping("fragments/navbar")
    public String getNav(){
        return "fragments/navigator";
    }

    /**
     *
     * @return - searchbar fragment
     */
    @GetMapping("fragments/searchbar")
    public String getSearchbar(){
        return "fragments/searchbar";
    }

    /**
     *
     * @return - popmessage fragment
     */
    @GetMapping("fragments/popmessage")
    public String getPopMessage(){
        return "fragments/popmessage";
    }
}
