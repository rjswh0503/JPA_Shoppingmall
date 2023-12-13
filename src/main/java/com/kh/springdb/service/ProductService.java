package com.kh.springdb.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.springdb.model.Product;
import com.kh.springdb.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
private final ProductRepository productRepository;
	
	//모든 상품 리스트 불러오기
	public List<Product> allProductView(){
		return productRepository.findAll();
	}
	
	
	//pagination add
	public Page<Product> getList(int page){
										//page = 페이지 값 , 1 = 페이지당 보여줄 목록 갯수
		Pageable pageable = PageRequest.of(page, 1);
		return productRepository.findAll(pageable);
		
	}

	//상품을 등록할 수 있는 메서드
	// 기존에는 상품명이나 글자로 이루어진 내용을 넣었지만
	//이미지를 넣어주기 위해서 파일을 파라미터에 받겠다 작성해줄것
	public void saveProduct(Product product,MultipartFile imgFile) throws IllegalStateException, IOException {
		String originName = imgFile.getOriginalFilename();
		String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/img/";
		File saveFile = new File(projectPath, originName);

		imgFile.transferTo(saveFile);
		product.setImgName(projectPath);
		product.setImgPath("/img/" + originName);
		productRepository.save(product);
	}
	
	// 아이템 상세보기나 수정하기를 할 수 있는 메서드 작성
	public Product getProductById(int id) {
		return productRepository.findProductById(id);
	}
	
	
	//제품의 좋아요를 받을 수 있도록 서비스 만들어줌
	
//	public void likeProduct(int productId) {
//		Product product = productRepository.findById(productId).orElse(null);
//		if(product != null) {
//			product.setLikes(product.getLikes() + 1);
//			productRepository.save(product);
//		}
//	}
	
	
	
	
}
