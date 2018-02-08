package com.myeample.restapi.restaurants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.myeample.restapi.restaurants.dto.MenuDto;
import com.myeample.restapi.restaurants.models.Menu;
import com.myeample.restapi.restaurants.repo.MenuRepo;

/**
 * service for menu
 * 
 * @author Nirav
 *
 */
@Service
public class MenuService {

	@Autowired
	private MenuRepo menuRepo;

	@CacheEvict(value = "menu", key = "#menuDto.getId()")
	public Menu addMenu(MenuDto menuDto) {
		// TODO Auto-generated method stub
		if (!menuRepo.exists(menuDto.getId())) {
			Menu menu = new Menu(menuDto);
			menuRepo.save(menu);
			return menu;
		}
		return null;

	}

	@CachePut(value = "menu", key = "#menuDto.getId()")
	public Menu updateMenu(MenuDto menuDto) {

		// do validations before storing to Db.

		if (menuRepo.exists(menuDto.getId())) {
			Menu menu = new Menu(menuDto);
			menuRepo.save(menu);
			return menu;
		}
		return null;

	}

	@CacheEvict(value = "menu", key = "#id")
	public int deleteMenu(String id) {
		// TODO Auto-generated method stub

		Menu menu = findeMenu(id);
		if (menu != null) {
			menuRepo.delete(id);
			return 1;
		}
		return 0;
	}

	@Cacheable(value = "menu")
	public Menu findeMenu(String id) {
		// TODO Auto-generated method stub
		System.out.println("test menu  cache ");
		return menuRepo.findOne(id);
	}

}
