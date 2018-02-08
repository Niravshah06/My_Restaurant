package com.myeample.restapi.restaurants.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myeample.restapi.restaurants.dto.MenuItemDto;
import com.myeample.restapi.restaurants.models.MenuItem;
import com.myeample.restapi.restaurants.service.MenuItemService;
/**
 * 
 * @author Nirav
 *
 */
@Controller
@RequestMapping("/menuItem")
public class MenuItemController {

	@Autowired
	private MenuItemService menuItemService;
/**
 * add menu item
 * @param menuItemDto
 * @return
 */
	@RequestMapping(value = "/addMenuItem", method = RequestMethod.POST)
	  @ResponseBody
	public ResponseEntity<?> addMenuItem(@RequestBody MenuItemDto menuItemDto) {
		if (!StringUtils.isEmpty(menuItemDto.getName())) {
			MenuItem result=menuItemService.addMenuItem(menuItemDto);
			if(null!=result)
				return new ResponseEntity<MenuItem>(result, HttpStatus.OK);
	}
		   return new ResponseEntity<String>("Menu Item already exist/MEnu Item Empty", HttpStatus.CONFLICT);

	}
/**
 * change menu item
 * @param menuItemDto
 * @return
 */
	@RequestMapping(value = "/changeMenuItem", method = RequestMethod.PUT)
	  @ResponseBody
	public ResponseEntity<?> changeMenuItem(@RequestBody MenuItemDto menuItemDto) {
		if (!StringUtils.isEmpty(menuItemDto.getName())) {
			MenuItem result=menuItemService.updateMenuItem(menuItemDto);
			if(null!=result)
				return new ResponseEntity<MenuItem>(result, HttpStatus.OK);
	}
		   return new ResponseEntity<String>("Error", HttpStatus.BAD_REQUEST);

	}

	
/**
 * delete menu item
 * @param menuItemName
 * @return
 */
	@RequestMapping(value = "/deleteMenuItemByName/{menuItemName}", method = RequestMethod.DELETE)
	  @ResponseBody
	public ResponseEntity<?> deleteMenuItemByName(@PathVariable String menuItemName) {
		if (!StringUtils.isEmpty(menuItemName)) {
			if(1==menuItemService.deleteMenuItem(menuItemName))
				 return new ResponseEntity<String>("Menu Item Deleted", HttpStatus.OK);}
		 return new ResponseEntity<String>("Menu Item does not exist", HttpStatus.NOT_FOUND);

	}
/**
 * chnage menu item
 * @param menuItemName
 * @return
 */
	@RequestMapping(value = "/getMenuItemByName/{menuItemName}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getMenuItemByName(@PathVariable String menuItemName) {
		if (!StringUtils.isEmpty(menuItemName) ){
			MenuItem result= menuItemService.getMenuItem(menuItemName);
		if(null!=result)
			return new ResponseEntity<MenuItem>(result, HttpStatus.OK);
}
	   return new ResponseEntity<String>("Menu Item does not exist", HttpStatus.NOT_FOUND);
	}

}
