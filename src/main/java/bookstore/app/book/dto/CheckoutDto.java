package bookstore.app.book.dto;

import bookstore.app.book.entity.CheckoutDetail;
import bookstore.app.book.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutDto {
    private Long id;

    private LocalDate orderDate;

    private LocalDate deliveryDate;

    private String status;

    private BigDecimal total;

    private String paymentMethod;

    private boolean isAccept;

    private UserDto user;

    private List<CheckOutDetailDto> checkoutDetails;
}
