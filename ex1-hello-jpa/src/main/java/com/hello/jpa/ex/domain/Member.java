package com.hello.jpa.ex.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    // ManyToOne의 fetch의 Default는 EAGER인데, LAZY로 변경하면 퀴리가 분리되서 나간다.(Member따로 Team따로)
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Member() {
    }

    // Getter Setter

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;

        // 연관관계 편의 메소드
        // 여기에서의 this는 Member 자신
        team.getMembers().add(this);
    }

    // Getter Setter 관례가 아니라 직관적으로 알 수 있게 이름을 다른 방식으로
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
