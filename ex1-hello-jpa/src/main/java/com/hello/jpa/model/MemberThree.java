package com.hello.jpa.model;

import javax.persistence.*;

@Entity
@Table(name = "MBR3")
public class MemberThree {

    /*
        <!-- @GeneratedValue -->
        - IDENTITY: 데이터베이스에 위임, MYSQL
        - SEQUENCE: 데이터베이스 시퀀스 오브젝트 사용, ORACLE
            @SequenceGenerator 필요
        - TABLE: 키 생성용 테이블 사용, 모든 DB에서 사용
            @TableGenerator 필요
        - AUTO: 방언에 따라 자동 지정, 기본값
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "name", nullable = false)
    private String username;

    public MemberThree() {
    }

    public MemberThree(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
