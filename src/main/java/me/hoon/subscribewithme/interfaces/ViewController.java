package me.hoon.subscribewithme.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/hello")
    public String getHello(){

        return "hello";
    }
}
