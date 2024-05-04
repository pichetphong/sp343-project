package com.example.utcckitchen.controllers;

import com.example.utcckitchen.models.Menu;
import com.example.utcckitchen.models.MenuDto;
import com.example.utcckitchen.services.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuRepository repo;

    @GetMapping({"","/"})
    public String showMenuList(Model model) {
        List<Menu> menu = repo.findAll();
        model.addAttribute("menu", menu);
        return "menus/index";
    }

    @GetMapping("/create")
    public String showCreateMenu(Model model){
        MenuDto menuDto = new MenuDto();
        model.addAttribute("menuDto", menuDto);
        return "menus/CreateMenu";
    }
}
