package com.zxd.springproperites.web;


import com.zxd.springproperites.data.IngredientRepository;
import com.zxd.springproperites.data.TacoRepository;
import com.zxd.springproperites.data.UserRepository;
import com.zxd.springproperites.model.Ingredient;
import com.zxd.springproperites.model.Ingredient.Type;
import com.zxd.springproperites.model.Order;
import com.zxd.springproperites.model.Taco;
import com.zxd.springproperites.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    private UserRepository userRepo;

    //end::ingredientRepoProperty[]
    private TacoRepository designRepo;

    @Autowired
    public DesignTacoController(
            IngredientRepository ingredientRepo,
            UserRepository userRepo, TacoRepository designRepo) {
        this.ingredientRepo = ingredientRepo;
        this.userRepo = userRepo;
        this.designRepo = designRepo;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }


    @GetMapping
    public String showDesignForm(Model model, Principal principal) {
        log.info("   --- Designing taco");
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        String username = principal.getName();
        User user = userRepo.findUserByUsername(username);
        model.addAttribute("user", user);

        return "design";
    }

    @PostMapping
    public String processDesign(
            @Valid Taco design, Errors errors,
            @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }

        Taco saved = designRepo.save(design);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }
}
