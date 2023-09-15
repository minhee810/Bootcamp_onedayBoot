package com.springboot.relationship.data.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "product_detail")
@Getter
@Setter
@ToString(callSuper = true) // BaseEntity에 선언한 것 까지 모두 적용하기 위해
@EqualsAndHashCode(callSuper = true)
public class ProductDetail extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToOne
    @JoinColumn(name = "product_number")   // 외래키 이름 데이터 베이스 내에서 PK_라는 상수가 만들어짐. 그와같은 이름을 만들어놓은 것임.
    private Product product;
}
