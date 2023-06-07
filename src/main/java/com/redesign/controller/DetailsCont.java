package com.redesign.controller;

import com.redesign.controller.main.Attributes;
import com.redesign.model.Details;
import com.redesign.model.Orderings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orderings/{orderingId}/details")
public class DetailsCont extends Attributes {

    @GetMapping
    public String details(Model model, @PathVariable Long orderingId) {
        AddAttributesDetails(model, orderingId);
        return "details";
    }
    @GetMapping("/disabled")
    public String detailsDisabled(Model model, @PathVariable Long orderingId) {
        AddAttributesDetails(model, orderingId);
        return "detailsDisabled";
    }

    @PostMapping("/add")
    public String detailAdd(@PathVariable Long orderingId, @RequestParam Long serviceId, @RequestParam int count) {
        Orderings ordering = orderingsRepo.getReferenceById(orderingId);
        ordering.addDetail(new Details(count, servicesRepo.getReferenceById(serviceId)));
        orderingsRepo.save(ordering);
        return "redirect:/orderings/{orderingId}/details";
    }

    @PostMapping("/edit/{detailId}")
    public String detailEdit(@PathVariable Long orderingId, @RequestParam Long serviceId, @RequestParam int count, @PathVariable Long detailId) {
        Details detail = detailsRepo.getReferenceById(detailId);
        detail.setCount(count);
        detail.setService(servicesRepo.getReferenceById(serviceId));
        detailsRepo.save(detail);
        return "redirect:/orderings/{orderingId}/details";
    }

    @GetMapping("/delete/{detailId}")
    public String detailDelete(@PathVariable Long orderingId, @PathVariable Long detailId) {
        Orderings ordering = orderingsRepo.getReferenceById(orderingId);
        ordering.removeDetail(detailsRepo.getReferenceById(detailId));
        orderingsRepo.save(ordering);
        return "redirect:/orderings/{orderingId}/details";
    }

}
