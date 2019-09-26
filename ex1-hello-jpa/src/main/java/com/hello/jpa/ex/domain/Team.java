package com.hello.jpa.ex.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    // 생성할 때 ArrayList로 바로 초기화 하는 이유는 관례상 이렇게 작성하는데, 나중에 Add할때 NullPointException이 나지 않게 하기 위해
    // Member에서 Team으로 갈땐 다대일
    // Team에서 Member로 갈땐 일대다이므로 @OneToMany를 선언해준다.
    @OneToMany(mappedBy = "team") //Member Domain에 있는 team 변수명
    private List<Member> members = new ArrayList<Member>();

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Member> getMembers() {
        return members;
    }

    // Member의 연관관계 편의 메소드인 changeTeam 메소드와 동일한 역할을 하는 메소드이므로
    // 한 쪽에서만 사용하기 위해 changeTeam을 사용하거나 addMember를 쓰거나 둘 중 하나로 정한다.
    public void addMember(Member member) {
        member.setTeam(this);
        members.add(member);
    }
}
