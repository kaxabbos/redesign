package com.rep.controller;

import com.rep.controller.main.Attributes;
import com.rep.model.Services;
import com.rep.model.Stats;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/services")
public class ServicesCont extends Attributes {
    @GetMapping("/add")
    public String serviceAdd(Model model) {
        AddAttributes(model);
        return "serviceAdd";
    }

    @GetMapping("/edit/{id}")
    public String serviceEdit(Model model, @PathVariable Long id) {
        AddAttributesServiceEdit(model, id);
        return "serviceEdit";
    }

    @GetMapping("/delete/{id}")
    public String serviceDelete(@PathVariable Long id) {
        servicesRepo.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String serviceAddNew(@RequestParam String name, @RequestParam String unit, @RequestParam int price) {
        Services service = new Services(name, unit, price);
        Stats stats = new Stats();
        service.setStats(stats);
        stats.setService(service);
        servicesRepo.save(service);
        return "redirect:/services/add";
    }

    @PostMapping("/edit/{id}")
    public String serviceEditOld(@RequestParam String name, @RequestParam String unit, @RequestParam int price, @PathVariable Long id) {
        Services service = servicesRepo.getReferenceById(id);
        service.setName(name);
        service.setUnit(unit);
        service.setPrice(price);
        servicesRepo.save(service);
        return "redirect:/";
    }
}
