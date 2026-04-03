package com.green.carproduct;

import lombok.Data;

//lombok을 이용해 DTO의 getter, setter 자동생성
@Data
public class carProductDTO {
	private int no;
	private String carName;
	private int price;
	private String company;
	private String img;
	private String info;
	
}
