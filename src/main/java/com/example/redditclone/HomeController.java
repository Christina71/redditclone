package com.example.redditclone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    SitelinkRepository sitelinkRepository;

    @RequestMapping("/")
    public String listSitelink(Model model) {
        model.addAttribute("sitelinks", sitelinkRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String sitelinkForm(Model model){
        model.addAttribute("sitelink", new Sitelink());
        return "sitelinkform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Sitelink sitelink, BindingResult result){
        if (result.hasErrors()){
            return "sitelinkform";

        }
        sitelinkRepository.save(sitelink);
        return "redirect:/";
    }
}