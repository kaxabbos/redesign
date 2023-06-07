package com.redesign.controller;

import com.redesign.controller.main.Attributes;
import com.redesign.model.Reviews;
import com.redesign.model.enums.Classifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
public class ReviewsCont extends Attributes {
    @GetMapping
    public String reviews(Model model) {
        AddAttributesReviews(model);
        return "reviews";
    }

    @PostMapping("/add")
    public String reviewsAdd(@RequestParam String review, @RequestParam Classifier classifier) {
        reviewsRepo.save(new Reviews(getUser().getUsername(), review, DateNow(), classifier));
        return "redirect:/reviews";
    }
}
