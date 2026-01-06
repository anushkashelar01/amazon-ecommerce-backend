package com.shopease.repository;

import com.shopease.entity.Cart;
import com.shopease.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Get all items by cart
    List<CartItem> findByCart(Cart cart);

    // DELETE all cart items by cart
    void deleteByCart(Cart cart);
}