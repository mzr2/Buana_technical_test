package com.test.technical_mvn.service;

import com.test.technical_mvn.model.Item;
import com.test.technical_mvn.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {
    final ItemRepository itemRepository;

    public List<Item> getAll(){
        return itemRepository.findAll();
    }

    public Item insertItem(Item item){
        return itemRepository.save(item);
    }

    public List<Item> searchItem(String keyword){
        return itemRepository.findByNameContaining(keyword);
    }
}
