package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS") // order가 데이터베이스의 예약어이기 때문에
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

//    @Column(name = "member_id")
//    private Long memberId;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne() // 주문한 입장에서는 주문자는 1명이니깐
    @JoinColumn(name = "member_id")
    private Member member;

    // 비즈니스 로직에서 보면 order가 중요할 수 있으니 양방향으로 만드는 경우가 많다.
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    // Constructor


    public Order() {
    }

    public Order(LocalDateTime orderDate, OrderStatus status) {
        this.orderDate = orderDate;
        this.status = status;
    }

    public Order(LocalDateTime orderDate, OrderStatus status, Member member) {
        this.orderDate = orderDate;
        this.status = status;
        this.member = member;
    }

    // Getter
    public Long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addOrderItem(OrderItem orderItem, EntityManager em) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
        em.persist(orderItem);
    }

    public void addOrderItemVer2(OrderItem orderItem, EntityManager em) {
        orderItems.add(orderItem);
        em.persist(orderItem);
    }


}
