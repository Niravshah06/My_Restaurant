package com.myeample.restapi.restaurants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.myeample.restapi.restaurants.dto.RestaurantDto;
import com.myeample.restapi.restaurants.models.Restaurant;
import com.myeample.restapi.restaurants.repo.RestaurantRepo;

/**
 * repo for Restaurant
 * 
 * @author Nirav
 *
 */
@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepo repo;

	@CacheEvict(value = "restaurant", key = "#restaurantDto.getRestaurantName()")
	public Restaurant add(RestaurantDto restaurantDto) {
		// TODO Auto-generated method stub
		if (!repo.exists(restaurantDto.getRestaurantName().toLowerCase())) {
			Restaurant r = new Restaurant(restaurantDto);
			repo.save(r);
			return r;
		}
		return null;

	}

	@CachePut(value = "restaurant", key = "#restaurantDto.getRestaurantName()")
	public Restaurant update(RestaurantDto restaurantDto) {
		// TODO Auto-generated method stub
		if (repo.exists(restaurantDto.getRestaurantName().toLowerCase())) {
			Restaurant r = new Restaurant(restaurantDto);
			repo.save(r);
			return r;
		}
		return null;
	}

	@CacheEvict(value = "restaurant", key = "#restaurantName")
	public int deleteRestaurant(String restaurantName) {
		// TODO Auto-generated method stub

		Restaurant r = getrestaurantByName(restaurantName);
		if (r != null) {
			repo.deleteByrestaurantName(restaurantName);
			return 1;
		}
		return 0;

	}

	@Cacheable(value = "restaurantName")
	public Restaurant getrestaurantByName(String restaurantName) {
		// TODO Auto-generated method stub
		System.out.println("test rest cache");
		return repo.findByrestaurantName(restaurantName);
	}

}
