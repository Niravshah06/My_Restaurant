package com.myeample.restapi.restaurants.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
/**
 * data object for menu
 * @author Nirav
 *
 */
@Setter
@Getter
public class MenuDto{

	private String id;
	private String type;
	private List<MenuItemDto> menuItemList=new ArrayList<MenuItemDto>();
	
	
	
}
