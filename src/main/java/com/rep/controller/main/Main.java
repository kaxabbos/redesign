package com.rep.controller.main;

import com.rep.model.Users;
import com.rep.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Main {
    @Autowired
    protected UsersRepo usersRepo;
    @Autowired
    protected DetailsRepo detailsRepo;
    @Autowired
    protected OrderingsRepo orderingsRepo;
    @Autowired
    protected ServicesRepo servicesRepo;
    @Autowired
    protected StatsRepo statsRepo;
    @Autowired
    protected ReviewsRepo reviewsRepo;
    @Autowired
    protected NewsRepo newsRepo;
    @Value("${upload.img}")
    protected String uploadImg;

    protected String DateNow() {
        return LocalDateTime.now().toString().substring(0, 10);
    }

    protected Users getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            return usersRepo.findByUsername(userDetail.getUsername());
        }
        return null;
    }

    protected String getRole() {
        Users users = getUser();
        if (users == null) return "NOT";
        return users.getRole().toString();
    }
}