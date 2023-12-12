package bookstore.app.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    private Long id;

    private int quantity;

    private BigDecimal total;

    private BookDto book;

    private OrderTotalDto orderTotal;


}
