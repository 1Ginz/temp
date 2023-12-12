package bookstore.app.book.dto;

import bookstore.app.book.entity.Checkout;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private String gender;
    private String avatar;
    private OrderTotalDto orderTotal;
    private List<CheckoutDto> checkouts;
}
