package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS") // order가 데이터베이스의 예약어이기 때문에
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // Constructor
    public Order(Long id, Long memberId, LocalDateTime orderDate, OrderStatus status) {
        this.id = id;
        this.memberId = memberId;
        this.orderDate = orderDate;
        this.status = status;
    }

    // Getter
    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
