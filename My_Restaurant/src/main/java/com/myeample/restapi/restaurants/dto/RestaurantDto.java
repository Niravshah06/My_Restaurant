package com.myeample.restapi.restaurants.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
/**
 * data object for Restaurant
 * @author Nirav
 *
 */
@Setter
@Getter
public class RestaurantDto {
	private String restaurantName;
	private String owner;
	private String contactNumber;
	private List<String> cuisineType = new ArrayList<String>();
	private List<String> locations = new ArrayList<String>();
	private List<MenuDto> menuList = new ArrayList<MenuDto>();
}
