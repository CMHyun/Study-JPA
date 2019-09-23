package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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

        // 정석 코드(실제에서는 스프링이 호출해지니깐 사라진다.)
        try {
            // code(DB 동작에 관련)
            // Insert
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);

            // Select
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());

            // Delete
//            Member findMember = em.find(Member.class, 1L);
//            em.remove(findMember);

            // Update (em.persist를 안해도 된다. / Java Collection을 다루는 것과 같이 설계되어서)
            // JPA를 통해서 값을 가져오면 JPA가 데이터를 관리하게 되고 JPA는 변경내용을 체크하여 변경된 값을 업데이트 할 수 있게 된다.
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");

            // JPQL
            // 테이블이 아닌 객체를 대상으로 검색하는 객체 지향 쿼리
            // ex) 나이가 18살 이상인 회원을 모두 검색하고 싶다면?
            // Member 객체를 다 가져와 / 대상이 테이블이 아니고 객체가 된다.
            // select m은 멤버 엔티티를 선택한 것이다.
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1) // Page
                    .setMaxResults(10)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
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
