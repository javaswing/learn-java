package com.zxd.demo.controller;

import com.zxd.demo.model.Ingredient;
import com.zxd.demo.model.Ingredient.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList(new Ingredient("FLTO", "面粉玉米饼", Type.WRAP), new Ingredient("COTO", "玉米饼", Type.WRAP), new Ingredient("GRBF", "碎牛肉", Type.PROTEIN));

        Type[] types = Type.values();
        Map<String, List> allMap = new HashMap<String, List>();

        for (Type type : types) {
            String s = type.toString().toLowerCase();
            allMap.put(s, filterByType(ingredients, type));

        }
        model.addAllAttributes(allMap);
//        model.addAllAttributes("design", new Taco());
        return "design";
    }


    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }
}
