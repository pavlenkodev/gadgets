package tech.itpark.marketplace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import tech.itpark.marketplace.model.Description;
import tech.itpark.marketplace.service.DescriptionService;

@Controller
public class DescriptionController {

    private final DescriptionService descriptionService;

    public DescriptionController(DescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }

    @GetMapping("/descriptions")
    public String findAll(Model model) {
        model.addAttribute("descriptions", descriptionService.findAll());
        return "description/description-list";

    }

    @GetMapping("/description-create")
    public String createDescriptionForm(Description description) {
        return "description/description-create";
    }

    @PostMapping("/description-create")
    public String createDescription(Description description) {
        descriptionService.save(description);
        return "redirect:/descriptions";
    }

    @GetMapping("/description-update/{id}")
    public String updateDescriptionForm(
            @PathVariable("id") Long id,
            Model model
    ) {
        model.addAttribute("description", descriptionService.findById(id));
        return "description/description-update";
    }

    @PostMapping("/description-update")
    public String updateDescription(Description description) {
        descriptionService.save(description);
        return "redirect:/descriptions";
    }

    @GetMapping("description-delete/{id}")
    public String removeById(@PathVariable("id") Long id) {
        descriptionService.removeById(id);
        return "redirect:/descriptions";
    }

}
