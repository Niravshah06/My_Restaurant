package com.myeample.restapi.restaurants.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.myeample.restapi.restaurants.dto.MenuItemDto;

import lombok.Getter;

/**
 * 
 * @author Nirav
 *
 */
@Entity
@Table(name = "MenuItem")
@Getter
public class MenuItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String description;

	@Id
	private String name;

	private Double cost;

	private Boolean available;

	// Italian,Chinese,chat etc
	private String category;

	@Temporal(TemporalType.DATE)
	private Date created;

	public MenuItem() {
	}

	public MenuItem(MenuItemDto menuItemDto) {
		this.available = true;
		this.category = menuItemDto.getCategory();
		this.cost = menuItemDto.getCost();
		this.description = menuItemDto.getDescription();
		this.name = menuItemDto.getName().toLowerCase();
		this.created = new Date();

	}

}
