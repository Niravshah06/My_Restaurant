package com.myeample.restapi.restaurants.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myeample.restapi.restaurants.models.Menu;

/**
 * repo for menu
 * 
 * @author Nirav
 *
 */
@Repository
public interface MenuRepo extends JpaRepository<Menu, String> {

}
