package com.urban_computing.sound_monitor.controller;


import com.urban_computing.sound_monitor.service.ReadingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private final ReadingService readingService;

    public ViewController(ReadingService readingService) {
        this.readingService = readingService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("readings", readingService.getAllReadings());
        return "readings";
    }
}
