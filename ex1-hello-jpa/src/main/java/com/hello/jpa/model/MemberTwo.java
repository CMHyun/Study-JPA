package com.hello.jpa.model;

import com.hello.jpa.value.RoleType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "MBR2")
public class MemberTwo {

    @Id
    private Long id;

    @Column(name = "name", nullable = false, unique = true) // DB에는 name으로 쓰고 싶은 경우
    private String username;

    @Column(columnDefinition = "varchar(100) default 1", length = 100)
    private Integer age; // int대신 Integer도 가능

    @Column(precision = 19, scale = 2)
    private BigDecimal parse;

    // ENUM Type에는 ORDINAL과 STRING 2가지가 있는데, ORDINAL은 절대 사용X
    // Default값이 ORDINAL이기 때문에 STRING으로 변경!!!!
    // ORDINAL: enum 순서를 데이터베이스에 저장 ex: USER를 넣어도 0
    // STRING: enum 이름을 데이터베이스에 저장
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // DB는 날짜, 시간, 날짜+시간을 구분해서 사용하기 때문에 아래의 어노테이션을 지정해줘야한다.
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // Java8 이상에서는 Class를 보고 데이터 타입이 자동으로 들어가기 때문에 위와 같이 작성할 필요없다.
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    // @Lob: Varchar를 넘어서 큰 컨텐츠를 넣고 싶을떄 사용
    // Lob에는 BLOB CLOB이 있는데, defualt는 CLOB이다.
    // 매핑하는 필드 타입이 문자면 CLOB, 나머지는 BLOB
    @Lob
    private String decription;

    // DB랑 상관없이 매핑하지 않고 메모리에서만 사용할때
    @Transient
    private int temp;

    public MemberTwo() {
    }

}
