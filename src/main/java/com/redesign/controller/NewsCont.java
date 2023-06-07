package com.redesign.controller;

import com.redesign.controller.main.Attributes;
import com.redesign.model.News;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/news")
public class NewsCont extends Attributes {
    @GetMapping
    public String news(Model model) {
        AddAttributesNews(model);
        return "news";
    }

    @GetMapping("/delete/{id}")
    public String newsDelete(@PathVariable Long id) {
        newsRepo.deleteById(id);
        return "redirect:/news";
    }

    @PostMapping("/add")
    public String newsAdd(Model model, @RequestParam String name, @RequestParam MultipartFile photo, @RequestParam String text) {
        String res = "";
        if (photo != null && !Objects.requireNonNull(photo.getOriginalFilename()).isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = "news/" + uuidFile + "_" + photo.getOriginalFilename();
                    photo.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (Exception e) {
                model.addAttribute("message", "Некорректный данные!");
                AddAttributesNews(model);
                return "news";
            }
        }

        newsRepo.save(new News(name, text, res));
        return "redirect:/news";
    }
}
