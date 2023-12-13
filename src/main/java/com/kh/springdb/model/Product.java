package com.kh.springdb.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity

@Table(name="Productd")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="productd_seq")
	@SequenceGenerator(name = "productd_seq", sequenceName="productd_seq", allocationSize=1)
	private int id;
	
	private String name;
	
	private String text;
	
	private String price;
	
	private int count;
	
	private int stock;
	
	private int isSoldout;
	
	
	//댓글 작성을 위한 Comment
	//@OneToMany(mappedBy="products", cascade=CascadeType.ALL)
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL)
	private List<Comment> comments;
	
	//상품 이미지를 위한 필드 설정
	
	private String imgName;
	private String imgPath;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate createDate;
	
    @PrePersist // DB에 INSERT 되기 직전에 실행. 즉 DB에 값을 넣으면 자동으로 실행됨
    public void createDate() {
        this.createDate = LocalDate.now();
    }
    
    //제품에 대한 좋아요를 받고 싶다면 여기에 추천과 관련된 변수를 넣어줘도 됨 
	
    //private int likes;
    
    //좋아요를 받는 방법은 여러 방법이 있음
    // 1. 사용자 관계없이 카운트만 올라가게하기
    
 
    
    // 2. ManyToOne이나 OneToMany 이용해서 서로 카운트 주기
    
    
    
    
    
    
    
	
}
