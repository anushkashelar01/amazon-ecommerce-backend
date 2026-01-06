package com.shopease.controller;

import com.shopease.entity.Cart;
import com.shopease.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // ADD TO CART
    @PostMapping("/add")
    public Cart addToCart(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity) {

        return cartService.addToCart(userId, productId, quantity);
    }

    // VIEW CART
    @GetMapping("/view")
    public Cart viewCart(@RequestParam Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @DeleteMapping("/remove")
    public String removeItem(
            @RequestParam Long userId,
            @RequestParam Long productId) {

        cartService.removeItem(userId, productId);
        return "Item removed from cart successfully";
    }

    // CLEAR CART
    @DeleteMapping("/clear")
    public String clearCart(@RequestParam Long userId) {
        cartService.clearCart(userId);
        return "Cart cleared successfully";
    }
}