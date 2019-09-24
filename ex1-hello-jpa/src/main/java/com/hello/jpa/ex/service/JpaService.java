package com.hello.jpa.ex.service;

import com.hello.jpa.ex.domain.Member;
import com.hello.jpa.ex.domain.MemberOne;
import com.hello.jpa.ex.domain.MemberThree;
import com.hello.jpa.ex.domain.Team;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaService implements IJpa {

    @Override
    public void basic(EntityManager em) {
            // code(DB 동작에 관련)
            // Insert
            MemberOne member = new MemberOne();
            member.setId(1L);
            member.setName("HelloA");
            em.persist(member);

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
//        List<MemberOne> result = em.createQuery("select m from MemberOne as m", MemberOne.class)
//                .setFirstResult(1) // Page
//                .setMaxResults(10)
//                .getResultList();
//        for (MemberOne member : result) {
//            System.out.println("member.name = " + member.getName());
//        }
    }

    @Override
    public void persistenceContext(EntityManager em) {

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
//            Member member = em.find(Member.class, 150L);
//            member.setName("ZZZZZ");

    }

    @Override
    public void flush(EntityManager em) {

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

        MemberOne memberA = new MemberOne(210L, "member210");
        MemberOne memberB = new MemberOne(220L, "member220");
        MemberOne memberC = new MemberOne(230L, "member230");

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
        List<MemberOne> members = em.createQuery("select m from Member as m", MemberOne.class).getResultList();
        for (MemberOne mem : members) {
            System.out.println("mem: " + mem.getName());
        }
    }

    @Override
    public void remove(EntityManager em) {
        MemberOne member = em.find(MemberOne.class, 180L);
        em.remove(member);
    }

    @Override
    public void detached(EntityManager em) {
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
        MemberOne member = em.find(MemberOne.class, 150L);

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
        MemberOne member2 = em.find(MemberOne.class, 150L);
    }

    @Override
    public void entityMapping(EntityManager em) {

    }

    @Override
    public void primaryKey(EntityManager em) {
//        MemberThree member = new MemberThree();
//        // @ID Annotation만 있을 경우 Primary Key를 설정할 수 있다.
////        member.setId("ID_A");
//        member.setUsername("Test_A");

//        System.out.println("====================");
//        em.persist(member);
//        System.out.println(member.getId());
//        System.out.println("====================");


        MemberThree member1 = new MemberThree();
        member1.setUsername("Test_A");

        MemberThree member2 = new MemberThree();
        member2.setUsername("Test_B");

        MemberThree member3 = new MemberThree();
        member3.setUsername("Test_C");

        System.out.println("====================");
        em.persist(member1); // DB_SEQ = 1
        em.persist(member2); // MEMORY
        em.persist(member3); // MEMORY

        System.out.println(member1.getId());
        System.out.println(member2.getId());
        System.out.println(member3.getId());

    }

    @Override
    public void tableModeling(EntityManager em) {
        //팀 저장 
//        Team team = new Team("test3");
//        em.persist(team);

//        Member member = new Member();
//        member.setName("C");
//        member.setTeam(team);
//        em.persist(member);

        // 이전 방식
//        // 조회
//        Member findMember = em.find(Member.class, member.getId());
//
//        // 연관관계가 없음
//        Team findTeam = em.find(Team.class, team.getId());

        // 나는 영속성 컨텍스트에서 1차 캐시로 가져온 정보 말고 DB의 진짜 쿼리를 보고 싶다.
        // 현재 영속성 컨텍스트를 날리고 DB와 싱크를 맞추는 작업
//        em.flush(); // 현재 영속성 컨텍스트의 쿼리를 날리고
//        em.clear(); // 영속성 컨텍스트를 초기화

        // 객체를 참조에 맞춰서
        Member findMember = em.find(Member.class, 2L);
        System.out.println("member_id: " + findMember.getId());

        Team findTeam = em.find(Team.class, findMember.getTeam().getId());
        System.out.println(findTeam.getName());

        Team testTeam = em.find(Team.class, 3L);
        findMember.setTeam(testTeam);

    }


}
