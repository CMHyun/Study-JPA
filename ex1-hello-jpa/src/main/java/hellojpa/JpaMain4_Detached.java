package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain4_Detached {

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

        // Transaction Start
        tx.begin();

        try {
            /*
            영속 상태는 JPA가 관리할 수 있는 1차 캐시 즉 Persistence Context에 올라와진 상태를 의미한다.

            * Persistence가 되는 경우
            1. em.persist 직접 영속 상태로 변경한 경우
            2. em.find를 했을때 데이터가 없는 경우 DB에서 조회를 하고 Persistence Context에 적재되어 Persistence 상태가 된다.
            3. JPQL를 통해 조회 헀을때도

            * Persistence -> Detached
            - 영속성 컨텍스트에서 분리되어 영속성 컨텍스트에서 제공하는 기능(update(set), Dirty Checking 등등)을 사용할 수 없음.
             */

            // 이것은 영속 상태이다. 왜냐하면 데이터가 없어서 가져와서 영속성 컨텍스트에 적재하기 때문에
            Member member = em.find(Member.class, 150L);

            // Dirty Checking
            member.setName("AAAAA");

            /*
                em.detach(member);
                특정 엔티티만 준영속 상태로 전환

                em.clear();
                영속성 컨텍스트를 완전히 초기화

                em.close() ;
                영속성 컨텍스트를 종료
             */

            // 영속성 컨텍스트에서 분리 시키므로 Update Query가 나가지 않는다.
//            em.detach(member);

            // 1차 캐시를 완전 초기화
            em.clear();

            // 다시 영속성 컨텍스트에 올라간다.
            Member member2 = em.find(Member.class, 150L);


            // Commit
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        // 실제 Application이 끝나면 EntityManagerFactory를 닫아줘야한다!
        // was가 내려갈때도 필수로!!
        emf.close();
    }

}
