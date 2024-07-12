package com.api.gw.task;


import com.api.gw.model.ProductBean;
import com.api.gw.service.ShoppingCartServiceEjb;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Timer;

@Singleton
@Startup
public class ScheduleProductPriceUpdate {

    @EJB
    ShoppingCartServiceEjb shoppingCartEjb;

    @Schedule(second = "*/30", minute = "*", hour = "*", persistent = false)
    public void executeTask(Timer timer) {
        updateProducts();
    }


    public void updateProducts() {
        try {
            String productsList = shoppingCartEjb.callRestApi("localhost:8082/products");
            Random random = new Random();
            ObjectMapper mapper = new ObjectMapper();
            List<ProductBean> products = mapper.readValue(productsList, new TypeReference<ProductBean>() {});
            for (ProductBean product : products) {
                double newPrice = random.nextDouble();
                product.setProductPrice(newPrice);
            }
            shoppingCartEjb.callRestApi("localhost:8082/updateprice");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Something went wrong in updating products price", e.getCause());
        }
    }
}
