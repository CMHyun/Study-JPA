package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // JPA가 관리할 객체
// @Table
public class Member {

    @Id // 데이터베이스 PK와 매핑
    private Long id;

    // @Column
    private String name;

    // Default Constructor
    // 존재 이유: JPA는 내부적으로 리플렉션을 쓰기 때문에 동적으로 객체를 생성해내야 한다. 그래서 기본 생성자가 필요하다.
    private Member() {
    }

    // Constructor
    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    // Getter, Setter
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
