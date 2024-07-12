package com.api.shoppingcard.service;

import com.api.shoppingcard.model.ShoppingCard;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCardService {


    public List<ShoppingCard> getShoppingCards(int customerId) {

        List<ShoppingCard> shoppingCards = new ArrayList<>();
        shoppingCards.add(new ShoppingCard(1,10,5,123));
        shoppingCards.add(new ShoppingCard(2,11,3,123));
        shoppingCards.add(new ShoppingCard(3,12,4,123));

        return shoppingCards.stream().filter(s -> customerId == s.getCustomerId()).collect(Collectors.toList());
    }
}
