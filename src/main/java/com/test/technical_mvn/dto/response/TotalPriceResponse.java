package com.test.technical_mvn.dto.response;


import com.test.technical_mvn.model.Item;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TotalPriceResponse {
    private Long idCart;
    private List<Item> items;
    private double totalPrice;
}
