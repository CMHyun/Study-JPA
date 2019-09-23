package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain3_Flush {

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
            // Flush !!
//            Member member = new Member(200L, "member200");
//            em.persist(member);
//
//            // 위 입력한 멤버 데이터를 트랜잭션 커밋 되기 전까지 볼 수 없어서
//            // 미리 데이터베이스에 저장하거나 아니면 쿼리를 보고 싶다면
//            // 강제 호출
//            em.flush();
//
//            System.out.println("=========================");

            Member memberA = new Member(210L, "member210");
            Member memberB = new Member(220L, "member220");
            Member memberC = new Member(230L, "member230");

            em.persist(memberA);
            em.persist(memberB);
            em.persist(memberC);
            // 이때까진 실제 쿼리가 날라가진 않음.
            // 하지만 아래에서 select를 하면 없는 데이터를 조회하게 되므로 문제가 발생하게 되고 JPA는 그러한 문제를 방지하기 위해서
            // JPQL를 사용할땐 Flush가 자동으로 호출되게 설정되어있다.
            // 해당 Flush Mode를 변경하고 싶다면 em.setFlushMode(FlushModeType.COMMIT); 이러한 형태로 변경이 가능하다.
            // FlushModeType.AUTO = Commit이나 Query를 실행할 때 Flush (기본값)
            // FlushModeType.COMMIT = Commit할 때만 Flush
            // (COMMIT Mode는 전혀 관련이 없는 다른 테이블을 조회할 때 사용될 수 있다. 왜냐하면 플러시를 해서 얻을 수 있는 이점이 없어서)

            // 중간에 JPQL 실행
            List<Member> members = em.createQuery("select m from Member as m", Member.class).getResultList();
            for (Member mem : members) {
                System.out.println("mem: " + mem.getName());
            }

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
