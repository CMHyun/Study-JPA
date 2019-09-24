package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "order_price")
    private int orderPrice;

    private int count;

    // Constructor
    public OrderItem(Long id, Long orderId, Long itemId, int orderPrice, int count) {
        this.id = id;
        this.orderId = orderId;
        this.itemId = itemId;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    // Getter
    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getItemId() {
        return itemId;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public int getCount() {
        return count;
    }
}
