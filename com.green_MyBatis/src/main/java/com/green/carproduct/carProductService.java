package com.green.carproduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.carproduct.mapper.carProductMapper;

@Service
public class carProductService {
	
	@Autowired
	carProductMapper carproductmapper;
	
	public List<carProductDTO> getAllCarProduct(){
		return carproductmapper.getAllCarProduct();
	};
	
	public void insertCarProduct(carProductDTO dto) {
		carproductmapper.insertCarProduct(dto);
	};
}
