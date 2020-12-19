package tech.itpark.marketplace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import tech.itpark.marketplace.model.Description;
import tech.itpark.marketplace.manager.DescriptionManager;

@Controller
public class DescriptionController {

    private final DescriptionManager descriptionManager;

    public DescriptionController(DescriptionManager descriptionManager) {
        this.descriptionManager = descriptionManager;
    }

    @GetMapping("/descriptions")
    public String findAll(Model model) {
        model.addAttribute("descriptions", descriptionManager.findAll());
        return "description/description-list";

    }

    @GetMapping("/description-create")
    public String createDescriptionForm(Description description) {
        return "description/description-create";
    }

    @PostMapping("/description-create")
    public String createDescription(Description description) {
        descriptionManager.save(description);
        return "redirect:/descriptions";
    }

    @GetMapping("/description-update/{id}")
    public String updateDescriptionForm(
            @PathVariable("id") Long id,
            Model model
    ) {
        model.addAttribute("description", descriptionManager.findById(id));
        return "description/description-update";
    }

    @PostMapping("/description-update")
    public String updateDescription(Description description) {
        descriptionManager.save(description);
        return "redirect:/descriptions";
    }

    @GetMapping("description-delete/{id}")
    public String removeById(@PathVariable("id") Long id) {
        descriptionManager.removeById(id);
        return "redirect:/descriptions";
    }

}
