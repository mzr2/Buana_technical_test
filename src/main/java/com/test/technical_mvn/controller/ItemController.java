package com.test.technical_mvn.controller;

import com.test.technical_mvn.dto.request.SearchRequest;
import com.test.technical_mvn.model.Item;
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
public class ItemController {
    final ItemService itemService;

    @GetMapping("/item")
    public ResponseEntity<List<Item>> getAll(){
        return new ResponseEntity<>(itemService.getAll(), HttpStatus.OK);
    }


    @PostMapping("/item")
    public ResponseEntity<Item> insertItem(@RequestBody Item item){
        try {
            return new ResponseEntity<>(itemService.insertItem(item), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public List<Item> searchItem(@RequestBody SearchRequest searchRequest){
        System.out.println(searchRequest.getKeyword());
        return itemService.searchItem(searchRequest.getKeyword());
    }
}
