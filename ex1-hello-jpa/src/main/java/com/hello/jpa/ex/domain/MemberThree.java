package com.hello.jpa.ex.domain;

import javax.persistence.*;

@Entity
@Table(name = "MBR3")
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1,
        allocationSize = 50)
public class MemberThree {

    /*
        <!-- @GeneratedValue -->
        - IDENTITY: 데이터베이스에 위임, MYSQL
            Insert문을 모아서 한 번에 보내는 버퍼링 방식에 적합하지 않다.
            왜냐하면 Persistence Context에 올라가려면 PK값이 있어야 하는데, PK값이 없다!
            그래서 Insert문을 tx.commit 시점에 쿼리를 DB에 보내는 것이 아니라 그 em.persist()문에서 쿼리를 보낸다.
            보낸 쿼리를 통해 디비를 통해서 ID(PK)값을 얻어올 수 있고 해당 값은 Persistence Context에 적재시킨다.
            그 후 member.getId()를 호출하면 Persistence Context에 적재된 Id, name 즉 Member 객체 정보를 가져올 수 있다.

        - SEQUENCE: 데이터베이스 시퀀스 오브젝트 사용, ORACLE
            Sequence 전략은 먼저 디비의 Seqcuense 영역에서 "call next value for MEMBER_SEQ"와 같이 먼저 값을 얻어오기 때문에
            IDENTITY 전략과 달리 Insert문을 모아서 보내는 버퍼링 방식도 가능하다.

            하지만 여기서 성능 최적화 문제를 생각해보면 결국엔 Sequence Value를 가져오기 위해 call next를 쓰면
            Network를 계속 통신해야하지 않나? 라는 의문이 들고 해당 문제를 해결하기 위해 Sequence값을 미리 당겨올 수 있다.
            @SequenceGenerator에서 allocationSize = 50(Default)을 설정하면 미리 디비에 50개의 시퀀스를 올려놓고
            jpa는 메모리에서 1씩 쓰게 해준다.

            처음 호출시
            DB_SEQ = 1
            두번째 호출시
            DB_SEQ = 51
            값이 지정되고 메모리단에서 관리하게 된다. DB에서 조회할땐 계속 51이었다가 어플리케이션 단에서 Sequence가 51이 되면 call next가 일어나서
            DB_SEQ는 101이 된다.

            단점은 어플리케이션 관리하다가 서버가 내려가게 되면 가져왔던 시퀀스 중 사용하지 않은 것들이 날라가게 된다!!!!
            시퀀스의 구멍이 생기는 부분이지만 크게 우려될 영역은 아니다.


            @SequenceGenerator 필요
            !!! Data Type이 String이면 안된다!!
            Sequence Object가 10억 넘어가면 돌기 때문에 Integer Type도 애매하고 Long을 쓰는게 좋다.
            10억이 넘어갈땐 Data Type 변경하는게 어렵다.

            @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")

            // Table 별로 시퀀스를 따로 주고 싶은 경우 아래와 같이 매핑하여 사용하면 된다.
            @SequenceGenerator(name = "MEMBER_SEQ_GENERATOR", sequenceName = "MEMBER_SEQ", initialValue = 1, allocationSize = 1)

        - TABLE: 키 생성용 테이블 사용, 모든 DB에서 사용
            Table에도 allocationSize 옵션은 Sequencse 전략과 동일한 의미이다.

            @TableGenerator 필요

            @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")
            @TableGenerator(name = "MEMBER_SEQ_GENERATOR", table = "MY_SEQUENCES", pkColumnValue = "MEMBER_SEQ", allocationSize = 1)

        - AUTO: 방언에 따라 자동 지정, 기본값
     */

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private String id;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

    public MemberThree() {
    }

    public MemberThree(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
