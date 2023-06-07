package com.rep.controller;

import com.rep.controller.main.Attributes;
import com.rep.model.*;
import com.rep.model.enums.OrderingStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orderings")
public class OrderingsCont extends Attributes {

    @GetMapping
    public String orderings(Model model) {
        AddAttributesOrderings(model);
        return "orderings";
    }

    @PostMapping("/add")
    public String orderingsAdd(@RequestParam String date) {
        Users user = getUser();
        user.addOrdering(new Orderings(date));
        usersRepo.save(user);
        return "redirect:/orderings";
    }

    @PostMapping("/edit/{id}")
    public String orderingsEdit(@RequestParam String date, @PathVariable Long id) {
        Orderings ordering = orderingsRepo.getReferenceById(id);
        ordering.setDate(date);
        orderingsRepo.save(ordering);
        return "redirect:/orderings";
    }

    @GetMapping("/delete/{id}")
    public String orderingsDelete(@PathVariable Long id) {
        Users user = getUser();
        user.removeOrdering(orderingsRepo.getReferenceById(id));
        usersRepo.save(user);
        return "redirect:/orderings";
    }

    @GetMapping("/order/{id}")
    public String orderingsOrder(@PathVariable Long id) {
        Orderings ordering = orderingsRepo.getReferenceById(id);
        ordering.setStatus(OrderingStatus.IN_WAITING);
        orderingsRepo.save(ordering);
        return "redirect:/orderings";
    }

    @GetMapping("/confirmed/{id}")
    public String orderingsConfirmed(@PathVariable Long id) {
        Orderings ordering = orderingsRepo.getReferenceById(id);
        ordering.setStatus(OrderingStatus.CONFIRMED);
        orderingsRepo.save(ordering);
        return "redirect:/orderings";
    }

    @GetMapping("/refused/{id}")
    public String orderingsUnconfirmed(@PathVariable Long id) {
        Orderings ordering = orderingsRepo.getReferenceById(id);
        ordering.setStatus(OrderingStatus.REFUSED);
        orderingsRepo.save(ordering);
        return "redirect:/orderings";
    }

    @GetMapping("/completed/{id}")
    public String orderingsCompleted(@PathVariable Long id) {
        Orderings ordering = orderingsRepo.getReferenceById(id);
        ordering.setStatus(OrderingStatus.COMPLETED);
        for (Details d : ordering.getDetails()){
            Stats stat = d.getService().getStats();
            stat.setIncome(stat.getIncome() + (d.getCount() * d.getService().getPrice()));
        }
        orderingsRepo.save(ordering);
        return "redirect:/orderings";
    }

}
