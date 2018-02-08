package com.myeample.restapi.restaurants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myeample.restapi.restaurants.dto.MenuDto;
import com.myeample.restapi.restaurants.models.Menu;
import com.myeample.restapi.restaurants.service.MenuService;

/**
 * menu controller
 * 
 * @author Nirav
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	/**
	 * add menu method to db
	 * 
	 * @param menuDto
	 * @return
	 */
	@RequestMapping(value = "/addMenu", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> addMenu(@RequestBody MenuDto menuDto) {
		if (null != menuDto) {
			Menu result = menuService.addMenu(menuDto);
			if (null != result) {
				return new ResponseEntity<Menu>(result, HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("Menu  already exist/MEnu  Empty", HttpStatus.CONFLICT);

	}

	/**
	 * delete menu method to db
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteMenu/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<?> deleteMenu(@PathVariable String id) {
		if (null != id) {
			if (1 == menuService.deleteMenu(id))
				return new ResponseEntity<String>("Menu Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Menu does not exist", HttpStatus.NOT_FOUND);

	}

	/**
	 * get menu method to db
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getMenu/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getMenu(@PathVariable String id) {
		if (null != id) {
			Menu result = menuService.findeMenu(id);
			if (null != result)
				return new ResponseEntity<Menu>(result, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Menu does not exist", HttpStatus.NOT_FOUND);
	}

	/**
	 * change menu to db
	 * 
	 * @param menuDto
	 * @return
	 */
	@RequestMapping(value = "/changeMenu", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> changeMenu(@RequestBody MenuDto menuDto) {
		if (null != menuDto) {
			Menu result = menuService.updateMenu(menuDto);
			if (null != result)
				return new ResponseEntity<Menu>(result, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Error", HttpStatus.BAD_REQUEST);

	}

}
