package com.test.technical_mvn.repository;

import com.test.technical_mvn.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    //    Optional<Item> findByName(String name);
    @Query("SELECT e FROM Item e WHERE e.nama_item LIKE %:keyword%")
    List<Item> findByNameContaining(String keyword);
}