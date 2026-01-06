package com.shopease.service;

import com.shopease.entity.Cart;

public interface CartService {

    Cart addToCart(Long userId, Long productId, int quantity);

    Cart getCartByUserId(Long userId);

    void clearCart(Long userId);
void removeItem(Long userId,Long productId);
}