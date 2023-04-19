package hello.core.ordier;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
