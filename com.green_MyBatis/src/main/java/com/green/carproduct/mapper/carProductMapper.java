package com.green.carproduct.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.carproduct.carProductDTO;

@Mapper
public interface carProductMapper {
	public List<carProductDTO> getAllCarProduct();
	
	// insert => List 사용 못함
	public void insertCarProduct(carProductDTO dto);
}
