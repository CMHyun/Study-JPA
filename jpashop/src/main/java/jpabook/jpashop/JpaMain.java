package jpabook.jpashop;

import jpabook.jpashop.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx =  em.getTransaction();
        tx.begin();

        try {
            // Item 등록
            Item item = new Item(
                    "Box",
                    1000,
                    1
            );
            em.persist(item);


            // Member 등록
            Member member = new Member(
                    "TestUser",
                    "Seoul",
                    "Gangnam",
                    "456-789"
            );
            em.persist(member);


            // Order 등록(주문)
            Order order = new Order(
                    LocalDateTime.now(),
                    OrderStatus.ORDER,
                    member);

//            order.addOrderItemVer2(new OrderItem(order), em);
            order.addOrderItemVer2(new OrderItem(
                    1000,
                    1,
                    order,
                    item
                ), em);


//            order.addOrderItem(new OrderItem(
//                    1000,
//                    1,
//                    order,
//                    item), em);
            em.persist(order);


//            em.flush();
//            OrderItem orderItem = new OrderItem(
//                    1000,
//                    1,
//                    order,
//                    item
//            );
//            em.persist(orderItem);

//            em.flush();
//            em.clear();


//            Order findOrder = em.find(Order.class, order.getId());
//            List<OrderItem> orderItems = findOrder.getOrderItems();

            List<OrderItem> orderItems = order.getOrderItems();

            System.out.println("============================");
            for (OrderItem o:orderItems) {
                System.out.println(o.toString());
            }
            System.out.println("============================");

//            System.out.println("============================");
//            List<OrderItem> orderItems = order.getOrderItems();
//            for (OrderItem o:orderItems) {
//                System.out.println(o.toString());
//            }
//            System.out.println("============================");




            // 2
            // 양방향 연관관계가 아니여도 애플리케이션 개발에 문제는 없다.
//            OrderItem orderItem = new OrderItem();
//            orderItem.setOrder(order);
//            em.persist(orderItem);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
