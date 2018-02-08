package com.myeample.restapi.restaurants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.myeample.restapi.restaurants.dto.MenuItemDto;
import com.myeample.restapi.restaurants.models.MenuItem;
import com.myeample.restapi.restaurants.repo.MenuItemRepo;

/**
 * service for menu item
 * 
 * @author Nirav
 *
 */
@Service
public class MenuItemService {

	@Autowired
	private MenuItemRepo repo;

	@CacheEvict(value = "menuItem", key = "#menuItemDto.getName()")
	public MenuItem addMenuItem(MenuItemDto menuItemDto) {

		// do validations before storing to Db.

		if (!repo.exists(menuItemDto.getName().toLowerCase())) {
			MenuItem m = new MenuItem(menuItemDto);
			repo.save(m);
			return m;
		}
		return null;

	}

	@CachePut(value = "menuItem", key = "#menuItemDto.getName()")
	public MenuItem updateMenuItem(MenuItemDto menuItemDto) {

		// do validations before storing to Db.

		if (repo.exists(menuItemDto.getName().toLowerCase())) {
			MenuItem m = new MenuItem(menuItemDto);
			repo.save(m);
			return m;
		}
		return null;

	}

	@CacheEvict(value = "menuItem", key = "#menuItemName")
	public int deleteMenuItem(String menuItemName) {
		// TODO Auto-generated method stub
		int isDeleted = repo.deleteByname(menuItemName);
		return isDeleted;

	}

	@Cacheable(value = "menuItem")
	public MenuItem getMenuItem(String menuItemName) {
		// TODO Auto-generated method stub
		System.out.println("test menu Item cache ");
		return repo.findByname(menuItemName);

	}

}
