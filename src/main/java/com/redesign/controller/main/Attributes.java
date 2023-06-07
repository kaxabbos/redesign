package com.redesign.controller.main;

import com.redesign.model.Orderings;
import com.redesign.model.enums.Classifier;
import com.redesign.model.enums.OrderingStatus;
import com.redesign.model.enums.Role;
import org.springframework.ui.Model;

import java.util.List;

public class Attributes extends Main {
    protected void AddAttributes(Model model) {
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
    }

    protected void AddAttributesUsers(Model model) {
        AddAttributes(model);
        model.addAttribute("users", usersRepo.findAll());
        model.addAttribute("roles", Role.values());
    }

    protected void AddAttributesIndex(Model model) {
        AddAttributes(model);
        model.addAttribute("services", servicesRepo.findAll());
    }

    protected void AddAttributesStats(Model model) {
        AddAttributes(model);
        model.addAttribute("stats", statsRepo.findAll());
    }

    protected void AddAttributesReviews(Model model) {
        AddAttributes(model);
        model.addAttribute("reviews", reviewsRepo.findAll());
        model.addAttribute("classifiers", Classifier.values());
    }
    protected void AddAttributesNews(Model model) {
        AddAttributes(model);
        model.addAttribute("news", newsRepo.findAll());
    }

    protected void AddAttributesServiceEdit(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("service", servicesRepo.getReferenceById(id));
    }

    protected void AddAttributesDetails(Model model, Long orderingId) {
        AddAttributes(model);
        model.addAttribute("services", servicesRepo.findAll());
        model.addAttribute("orderingId", orderingId);
        model.addAttribute("orderingDetails", orderingsRepo.getReferenceById(orderingId).getDetails());
    }

    protected void AddAttributesOrderings(Model model) {
        AddAttributes(model);
        model.addAttribute("statuses", OrderingStatus.values());
        if (getRole().equals(Role.CLIENT.toString())) {
            List<Orderings> designList = orderingsRepo.findAllByStatusAndOwner_Id(OrderingStatus.IN_THE_DESIGN, getUser().getId());
            if (designList.size() > 0) model.addAttribute("designList", designList);
            List<Orderings> orderingsClient = orderingsRepo.findAllByStatusNotAndOwner_Id(OrderingStatus.IN_THE_DESIGN, getUser().getId());
            if (orderingsClient.size() > 0) model.addAttribute("orderingsClient", orderingsClient);
        } else if (getRole().equals(Role.MANAGER.toString())) {
            List<Orderings> orderingsManager = orderingsRepo.findAllByStatusNot(OrderingStatus.IN_THE_DESIGN);
            if (orderingsManager.size() > 0) model.addAttribute("orderingsManager", orderingsManager);
        }
    }
}
