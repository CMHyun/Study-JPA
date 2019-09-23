package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain_2 {

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
            // 비영속(New) - 객체 상태
//            Member member = new Member();
//            member.setId(10L);
//            member.setName("HelloJPA");
//
//            // 영속(Managed) 상태
//            // DB에 Query가 날라가서 저장되는 시점이 아님
//            // Before과 After가 먼저 출력 후 SQL문이 출력되는 것을 알 수 있다.
//            System.out.println("=== Before ===");
//            em.persist(member);
//            System.out.println("=== After ===");

            // 영속성 컨텍스트에서 제거
//            em.detach(member);

            // Remove는 실제 DB에 데이터 삭제 요청
//            em.remove(member);

            // --------------------------------------------------------------------------------------------------------
            // 1차 캐시와 엔티티 동일성 비교
//            Member a = em.find(Member.class, 1L);
//            System.out.println("a.name = " + a.getName());
//            Member b = em.find(Member.class, 1L);
//            System.out.println("b.name = " + b.getName());
//            System.out.println(a == b); // 동일성 비교 True

            // --------------------------------------------------------------------------------------------------------
//            Member member1 = new Member(170L, "C");
//            Member member2 = new Member(180L, "D");
//
//            // Query를 한 번 한 번 수행하는게 아니라 한 번에 보내는 방법은 persistence.xml에 batch_size Option을 주면 된다.
//            // batch_size Option을 주면 옵션에 준 사이즈만큼 모아서 한 번에 쿼리를 보내게 된다.
//            em.persist(member1);
//            em.persist(member2);
//            System.out.println("===================");

            // --------------------------------------------------------------------------------------------------------
            // JPA의 목적은 Java Collection처럼 다루기 위함이다. 즉 RDB의 패래다임과 Java의 OOP 패러다임 사이의 중간 역활을 하는 것.
            // Update문에서 em.persist()는 아무런 의미 없는 로직이다.
            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZ");

            // --------------------------------------------------------------------------------------------------------
            // 삭제
//            Member memberA = em.find(Member.class, 180L);
//            em.remove(memberA);

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
