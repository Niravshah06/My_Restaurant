package com.myeample.restapi.restaurants.dto;

import lombok.Getter;
import lombok.Setter;
/**
 * data objectb from menu item
 * @author Nirav
 *
 */
@Setter
@Getter
public class MenuItemDto {

	private String description;

	private String name;

	private Double cost;

		//Italian,Chinese,chat etc
	private String category;
}
