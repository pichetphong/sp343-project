package com.example.utcckitchen.controllers;

import com.example.utcckitchen.models.Menu;
import com.example.utcckitchen.models.MenuDto;
import com.example.utcckitchen.services.MenuRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
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

    @PostMapping("/create")
    public String createMenu(
            @Valid @ModelAttribute MenuDto menuDto,
            BindingResult result
    ){
        if (menuDto.getImageFile().isEmpty()){
            result.addError(new FieldError("menuDto", "imageFile", "The image file is required"));
        }

        if (result.hasErrors()){
            return "menus/CreateMenu";
        }

        // save image
        MultipartFile image = menuDto.getImageFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

        try {
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = image.getInputStream()){
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception ex) {
            System.out.println("Exception: "+ ex.getMessage());
        }

        Menu menu = new Menu();
        menu.setName(menuDto.getName());
        menu.setPrice(menuDto.getPrice());
        menu.setDescription(menuDto.getDescription());
        menu.setCreatedAt(createdAt);
        menu.setImageFileName(storageFileName);

        repo.save(menu);

        return "redirect:/menus";
    }
}
