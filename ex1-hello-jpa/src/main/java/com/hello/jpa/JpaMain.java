package com.hello.jpa;

import com.hello.jpa.service.JpaService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {

        // 만드는 순간 데이터베이스와 연결됨. 딱 1개만 만들어야함.
        // 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 한 트랜잭션을 하기 위해서는 필수로 생성
        // 엔티티 매니저는 쓰레드간에 공유X (사용하고 버려야 한다).
        EntityManager em = emf.createEntityManager();

        // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        // 쉽게 데이터베이스 커넥션을 하나 얻었다고 생각하자.
        EntityTransaction tx =  em.getTransaction();

        // Start
        tx.begin();

        // Interface Service
        JpaService service = new JpaService();

        try {
//            service.basic(em);
            service.primaryKey(em);

            // Commit 필수
            // Error: Connection leak detected: there are 1 unclosed connections upon shutting down pool 발생
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }


        // 실제 Application이 끝나면 EntityManagerFactory를 닫아줘야한다!
        // was가 내려갈때도 필수로!!
        emf.close();
    }

}
