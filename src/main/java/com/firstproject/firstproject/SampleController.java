package com.firstproject.firstproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {
    @RequestMapping("/test")
    @ResponseBody
    public String testingEndpoint(){
        return "<h1>Test Endpoint</h1>";
    }
}
