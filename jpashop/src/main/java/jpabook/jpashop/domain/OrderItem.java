package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

//    @Column(name = "order_id")
//    private Long orderId;
//
//    @Column(name = "item_id")
//    private Long itemId;

    @Column(name = "order_price")
    private int orderPrice;

    private int count;


    // Mapping
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;




    // Constructor
    public OrderItem() {
    }

    public OrderItem(int orderPrice, int count) {
//        this.orderId = orderId;
//        this.itemId = itemId;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    /*
        Insert Constructor
     */
    public OrderItem(int orderPrice, int count, Order order, Item item) {
        this.orderPrice = orderPrice;
        this.count = count;
        this.order = order;
        this.item = item;
    }

    // 양방향 편의 메소드를 위한 Constructor
    public OrderItem(Order order) {
        this.order = order;
    }





    // Setter
    public void setOrder(Order order) {
        this.order = order;
    }

    // Getter
    public Long getId() {
        return id;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public int getCount() {
        return count;
    }

    public Order getOrder() {
        return order;
    }

    public Item getItem() {
        return item;
    }


    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderPrice=" + orderPrice +
                ", count=" + count +
                '}';
    }
}
