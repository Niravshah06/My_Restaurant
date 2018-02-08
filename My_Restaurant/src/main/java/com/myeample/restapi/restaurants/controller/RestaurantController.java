package com.myeample.restapi.restaurants.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myeample.restapi.restaurants.dto.RestaurantDto;
import com.myeample.restapi.restaurants.models.Restaurant;
import com.myeample.restapi.restaurants.service.RestaurantService;
/**
 * restaurant @Controller class
 * @author Nirav
 *
 */
@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
/**
 * get Restaurant
 * @param restaurantName
 * @return
 */
	@CrossOrigin
	@RequestMapping(value = "/getRestaurantByName/{restaurantName}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getRestaurantByName(@PathVariable String restaurantName) {
		if (!StringUtils.equals(restaurantName, null)) {
			Restaurant r = restaurantService.getrestaurantByName(restaurantName);
			if (r != null)
				return new ResponseEntity<Restaurant>(r, HttpStatus.OK);
		}
		   return new ResponseEntity<String>("Restaurant does not exist", HttpStatus.NOT_FOUND);

	}
/**
 * add Restaurant
 * @param restaurantDto
 * @return
 */
	@RequestMapping(value = "/addRestaurant", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> addRestaurant(@RequestBody RestaurantDto restaurantDto) {
		if (null != restaurantDto && !StringUtils.equals(restaurantDto.getRestaurantName(), null)) {
			Restaurant result=restaurantService.add(restaurantDto);
			if(null!=result)
				return new ResponseEntity<Restaurant>(result, HttpStatus.OK);
	}
		   return new ResponseEntity<String>("Restaurant already exist/invalid data", HttpStatus.CONFLICT);

	}
	/**
	 * chnage Restaurant
	 * @param restaurantDto
	 * @return
	 */
	@RequestMapping(value = "/changeRestaurant", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> chnageRestaurant(@RequestBody RestaurantDto restaurantDto) {
		if (null != restaurantDto && !StringUtils.equals(restaurantDto.getRestaurantName(), null)) {
			Restaurant result=restaurantService.update(restaurantDto);
			if(null!=result)
				return new ResponseEntity<Restaurant>(result, HttpStatus.OK);
	}
		   return new ResponseEntity<String>("Restaurant already exist/invalid data", HttpStatus.NOT_IMPLEMENTED);

	}

	
/**
 * delete Restaurant
 * @param restaurantName
 * @return
 */
	@RequestMapping(value = "/deleteRestaurant/{restaurantName}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<?> deleteRestaurant(@PathVariable String restaurantName) {
		if (null != restaurantName) {
			if(1==restaurantService.deleteRestaurant(restaurantName)) {
				 return new ResponseEntity<String>("Restaurant Deleted", HttpStatus.OK);}
		}
		 return new ResponseEntity<String>("Restaurant not exist", HttpStatus.NOT_FOUND);


	}

}

