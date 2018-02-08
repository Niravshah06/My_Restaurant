package com.myeample.restapi.restaurants.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.myeample.restapi.restaurants.dto.MenuDto;
import com.myeample.restapi.restaurants.dto.MenuItemDto;

import lombok.Getter;

/**
 * 
 * @author Nirav
 *
 */
@Entity
@Table(name = "Menu")
@Getter
public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String menuId;

	// breakfast,dinner,lunch etc
	private String type;

	@Temporal(TemporalType.DATE)
	private Date created;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<MenuItem> menuItemList = new ArrayList<MenuItem>();

	public Menu() {
	}

	public Menu(MenuDto menuDto) {

		this.type = menuDto.getType();
		this.menuId = menuDto.getId();
		this.created = new Date();
		if (null != menuDto.getMenuItemList()) {
			for (MenuItemDto m : menuDto.getMenuItemList()) {
				MenuItem item = new MenuItem(m);
				this.menuItemList.add(item);
			}

		}
	}
}
