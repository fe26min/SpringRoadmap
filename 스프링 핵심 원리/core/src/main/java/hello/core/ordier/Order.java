package hello.core.ordier;

public class Order {
    private Long memberId;
    private String itemName;
    private int itempPrice;
    private int discountPrice;

    public Order(Long memberId, String itemName, int itempPrice, int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itempPrice = itempPrice;
        this.discountPrice = discountPrice;
    }

    public int calculatePrice() {
        return itempPrice - discountPrice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItempPrice() {
        return itempPrice;
    }

    public void setItempPrice(int itempPrice) {
        this.itempPrice = itempPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itempPrice=" + itempPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
