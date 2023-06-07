package com.rep.controller;

import com.rep.controller.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stats")
public class StatsCont extends Attributes {
    @GetMapping
    public String Statics(Model model) {
        AddAttributesStats(model);
        return "stats";
    }
}
