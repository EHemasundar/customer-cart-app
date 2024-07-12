package com.api.shoppingcard.controller;

import com.api.shoppingcard.model.ShoppingCard;
import com.api.shoppingcard.service.ShoppingCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShoppingCardController {

    @Autowired
    private ShoppingCardService shoppingCardService;

    @GetMapping("/shoppingcarts/{customerId}")
    public List<ShoppingCard> getShoppingCarts(@PathVariable int customerId) {

        return shoppingCardService.getShoppingCards(customerId);
    }

}
