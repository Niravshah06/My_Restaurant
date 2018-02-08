package com.myeample.restapi.restaurants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myeample.restapi.restaurants.models.MenuItem;

/**
 * repo for menu item
 * 
 * @author Nirav
 *
 */
@Repository
public interface MenuItemRepo extends JpaRepository<MenuItem, String> {

	@Transactional
	public MenuItem findByname(@Param("name") String name);

	@Transactional
	public int deleteByname(@Param("name") String name);

}
