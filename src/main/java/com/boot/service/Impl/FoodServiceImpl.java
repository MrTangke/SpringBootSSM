package com.boot.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.entity.Foods;
import com.boot.mapper.FoodsMapper;
import com.boot.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired(required = false)
	private FoodsMapper foodsMapper ;

	@Override
	public List<Foods> selectFoodList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return foodsMapper.selectFoodList(map);
	}

	@Override
	public Integer seleFoodCount(Foods foods) {
		// TODO Auto-generated method stub
		return foodsMapper.selectFoodsCount(foods);
	}

	
	
}
