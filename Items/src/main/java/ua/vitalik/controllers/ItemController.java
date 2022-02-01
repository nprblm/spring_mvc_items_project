package ua.vitalik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.vitalik.dao.ItemDAO;
import ua.vitalik.models.Item;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemDAO itemDAO;

    @Autowired
    public ItemController(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("item", itemDAO.index());
        return "items/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("item", itemDAO.show(id));
        return "items/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("item") Item person) {
        return "items/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("item") @Valid Item person,
                         BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors())
            return "items/new";

        itemDAO.save(person);
        return "redirect:/items";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("item", itemDAO.show(id));
        return "items/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("item") @Valid Item person, BindingResult bindingResult,
                         @PathVariable("id") int id) throws IOException {
        if (bindingResult.hasErrors())
            return "items/edit";

        itemDAO.update(id, person);
        return "redirect:/items/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        itemDAO.delete(id);
        return "redirect:/items";
    }
}
