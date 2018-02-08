package com.myeample.restapi.restaurants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myeample.restapi.restaurants.models.Restaurant;

/**
 * repo for Restaurant
 * 
 * @author Nirav
 *
 */
@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, String> {

	@Transactional
	public Restaurant findByrestaurantName(@Param("restaurantName") String restaurantName);

	@Transactional
	public void deleteByrestaurantName(@Param("restaurantName") String restaurantName);
}