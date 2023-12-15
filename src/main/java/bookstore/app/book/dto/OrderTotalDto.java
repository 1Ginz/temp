package bookstore.app.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class OrderTotalDto {
    private Long id;

    private BigDecimal amountTotal;

    private UserDto user;


    private Set<OrderDetailDto> orderDetails;

    public void addOrderDetails(OrderDetailDto orderDetail) {
        this.orderDetails.add(orderDetail);
        orderDetail.setOrderTotal(this);
    }

    public OrderTotalDto() {
        this.orderDetails = new LinkedHashSet<>();
        this.amountTotal = BigDecimal.valueOf(0.0);
    }
}
