package com.myeample.restapi.restaurants.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myeample.restapi.restaurants.dto.MenuDto;
import com.myeample.restapi.restaurants.dto.RestaurantDto;

import lombok.Getter;

/**
 * 
 * @author Nirav
 *
 */
@Entity
@Table(name = "Restaurants")
@Getter
public class Restaurant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String restaurantName;
	private String owner;
	private String contactNumber;

	@ElementCollection
	@JsonIgnore
	private List<String> cuisineType = new ArrayList<String>();
	@ElementCollection
	@JsonIgnore
	private List<String> locations = new ArrayList<String>();

	@Temporal(TemporalType.DATE)
	private Date started;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Menu> menuList = new ArrayList<Menu>();

	public Restaurant() {
	}

	public Restaurant(RestaurantDto restaurantDto) {
		this.contactNumber = restaurantDto.getContactNumber();
		this.cuisineType = restaurantDto.getCuisineType();
		this.locations = restaurantDto.getLocations();
		this.owner = restaurantDto.getOwner();
		this.restaurantName = restaurantDto.getRestaurantName().toLowerCase();// to make id unique and error free from
																				// casing mistake
		this.started = new Date();
		if (null != restaurantDto.getMenuList()) {
			for (MenuDto menuDto : restaurantDto.getMenuList()) {
				Menu menu = new Menu(menuDto);
				this.menuList.add(menu);

			}
		}

	}

	@Override
	public String toString() {

		return "R:-" + restaurantName + owner + started + contactNumber;
	}

}