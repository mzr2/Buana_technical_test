package com.test.technical_mvn.service;


import com.test.technical_mvn.dto.request.CartRequest;
import com.test.technical_mvn.dto.response.TotalPriceResponse;
import com.test.technical_mvn.model.Cart;
import com.test.technical_mvn.model.Item;
import com.test.technical_mvn.repository.CartRepository;
import com.test.technical_mvn.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartService {
    final CartRepository cartRepository;
    final ItemRepository itemRepository;

    public List<Cart> getAll(){
        return cartRepository.findAll();
    }

    public Cart getCartById(CartRequest cart){
        return cartRepository.findById(cart.getIdCart()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart Not Found"));
    }

    public Cart insertCart(Cart cart){
        return cartRepository.save(cart);
    }

    public Cart insertToCart(CartRequest cartRequest) {
        Item ItemObj = itemRepository.findById(cartRequest.getIdItem()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Not Found"));
        ItemObj.setId(cartRequest.getIdItem());
        Cart cartObj = cartRepository.findById(cartRequest.getIdCart()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart Not Found"));
        List<Item> itemList = cartObj.getItems();
        itemList.add(ItemObj);
        cartObj.setItems(itemList);
        return cartRepository.save(cartObj);
    }

    public Cart removeItemFromCart(CartRequest cartRequest){
        Cart cart = cartRepository.findById(cartRequest.getIdCart()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart Not Found"));
        Item item = itemRepository.findById(cartRequest.getIdItem()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Not Found"));
        cart.getItems().remove(item);
        return cartRepository.save(cart);
    }

    public TotalPriceResponse calculateTotalPrice(CartRequest cartRequest){
        TotalPriceResponse totalPriceResponse = new TotalPriceResponse();
        double totalPrice = 0;
        Cart cart = cartRepository.findById(cartRequest.getIdCart()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart Not Found"));
        for (Item item : cart.getItems()) {
            totalPrice += item.getHarga();
        }

        return new TotalPriceResponse(cart.getId(), cart.getItems(), totalPrice);
    }
}
