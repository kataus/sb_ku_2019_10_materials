package ru.itvitality.sbrf.cu.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {

    @RequestMapping(value = "/")
    public String index( ModelMap modelMap ){
        modelMap.addAttribute( "message", "Hello world!" );
        return "index";
    }
}
