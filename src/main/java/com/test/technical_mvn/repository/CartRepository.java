package com.test.technical_mvn.repository;

import com.test.technical_mvn.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
