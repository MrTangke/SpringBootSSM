package com.boot.service;

import java.util.List;
import java.util.Map;

import com.boot.entity.Foods;

public interface FoodService {

	List<Foods> selectFoodList(Map<String, Object> map);

	Integer seleFoodCount(Foods foods);

}
