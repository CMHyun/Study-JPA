package jpabook.jpashop.domain;

import com.sun.tools.corba.se.idl.constExpr.Or;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String city;
    private String street;
    private String zipcode;

    // Member에 Orders를 넣는 것은 좋은 방식이 아니다.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<Order>();

    // Constructor
    public Member() {
    }

    public Member(String name, String city, String street, String zipcode) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    // Getter는 전부 만들고 Setter는 Code 추적을 당할 수 있기때문에 생성자(Constructor)에서
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
