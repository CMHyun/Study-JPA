package com.hello.jpa.ex.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
    @Table(name = "MBR")
    - Table 명을 매핑하기 위해서 사용될 수 있다.
    - name에 이어서 아래와 같은 옵션들을 지정해줄수도 있다.
    catalog: 데이터베이스에 catalog 매핑

    schema: 데이터베이스 schema 매핑

    uniqueConstraints(DDL): DDL 생성 시에 유니크 제약 조건 생성
    ex)
    @Table(uniqueConstraints = {@UniqueConstraint(name = "NAME_AGE_UNIQUE", columnNames = {"NAME", "AGE"})})
 */
@Entity
@Table(name = "MBR1")
public class MemberOne {

    @Id // 데이터베이스 PK와 매핑
    private Long id;
    private String name;
    private int age;

    // < Default Constructor >
    /*
        - 존재 이유: JPA는 내부적으로 리플렉션을 쓰기 때문에 동적으로 객체를 생성해내야 한다. 그래서 기본 생성자가 필요하다.
     */
    public MemberOne() {
    }

    // < Constructor >
    public MemberOne(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // < Getter and Setter >
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
