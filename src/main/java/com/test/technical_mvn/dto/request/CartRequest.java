package com.test.technical_mvn.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    private Long idCart;
    private Long idItem;
}
