package com.test.technical_mvn.controller;

import com.test.technical_mvn.dto.request.CartRequest;
import com.test.technical_mvn.dto.response.TotalPriceResponse;
import com.test.technical_mvn.model.Cart;
import com.test.technical_mvn.model.Item;
import com.test.technical_mvn.service.CartService;
import com.test.technical_mvn.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RelationSupport;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
public class CartController {
    final CartService cartService;

    @GetMapping("/cart")
    public ResponseEntity<List<Cart>> getAll() {
        return new ResponseEntity<>(cartService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/cart_by_id")
    public ResponseEntity<Cart> getCartById(@RequestBody CartRequest cart) {
        try {
            return new ResponseEntity<>(cartService.getCartById(cart), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/cart")
    public ResponseEntity<Cart> insertItem(@RequestBody Cart cart) {
        try {
            return new ResponseEntity<>(cartService.insertCart(cart), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/add_to_cart")
    public ResponseEntity<Cart> insertItemToCart(@RequestBody CartRequest cart) {
        try {
            return new ResponseEntity<>(cartService.insertToCart(cart), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/remove_item_from_cart")
    public ResponseEntity<Cart> removeItemFromCart(@RequestBody CartRequest cart) {
        try {
            return new ResponseEntity<>(cartService.removeItemFromCart(cart), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/cart/total_price")
    public ResponseEntity<TotalPriceResponse> calculatePrice(@RequestBody CartRequest cartRequest){
        try {
            return new ResponseEntity<>(cartService.calculateTotalPrice(cartRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
