package com.api.gw.service;

import com.api.gw.model.ShoppingCart;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Stateless
@LocalBean
public class ShoppingCartServiceEjb {

    public String getShoppingCartbyId(int customerId) {
        List<ShoppingCart> shoppingCartList = new ArrayList<>();
        String data = null;
        try {
            String productRes = callRestApi("localhost:8082/products");
            TimeUnit.SECONDS.sleep(2);
            String cartRes = callRestApi("localhost:8081/shoppingcarts/" + customerId);

            if (productRes != null && cartRes != null) {
                ShoppingCart shoppingCart = new ShoppingCart();
                JSONArray prodObj = new JSONArray(productRes);
                JSONArray cartObj = new JSONArray(cartRes);
                //JSONObject prodjson = new JSONObject(productRes);
                for (int i = 0; i < prodObj.length(); i++) {
                    shoppingCart.setProductId(prodObj.getJSONObject(i).getInt("productId"));
                    shoppingCart.setProductName(prodObj.getJSONObject(i).getString("productName"));
                    shoppingCart.setPrice(prodObj.getJSONObject(i).getDouble("productPrice"));
                }
                for (int i = 0; i < prodObj.length(); i++) {
                    shoppingCart.setCustomerId(cartObj.getJSONObject(i).getInt("customerId"));
                    shoppingCart.setCustomerId(cartObj.getJSONObject(i).getInt("cartId"));
                    shoppingCart.setCustomerId(cartObj.getJSONObject(i).getInt("productId"));
                    shoppingCart.setCustomerId(cartObj.getJSONObject(i).getInt("quantity"));
                }
                shoppingCartList.add(shoppingCart);
            }
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.writeValueAsString(shoppingCartList);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("something went wrong", e);
        }

        return data;
    }


    public String callRestApi(String url) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).build();
        HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return httpResponse.body();
    }
}
