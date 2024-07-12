package com.api.gw.controller;

import com.api.gw.service.ShoppingCartServiceEjb;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/customer")
public class ShoppingApplication {

    @EJB
    ShoppingCartServiceEjb scs;
    @GET
    @Path("/shoppingcart/{customer_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String retrieveCustomerCarts(@PathParam("customer_id") int customer_id) {

        return scs.getShoppingCartbyId(customer_id);
    }

//    @PUT
//    @Path("/update/product")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String updateProduct(@RequestBody Product product) {
//
//        return null;
//    }
}
