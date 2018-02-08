package com.myeample.restapi.restaurants.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.myeample.restapi.restaurants.dto.MenuDto;
import com.myeample.restapi.restaurants.models.Menu;
import com.myeample.restapi.restaurants.service.MenuService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MenuController.class, secure = false)
public class MenuControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MenuService menuService;

	private String exampleJSON = "{\r\n" + "					\"id\":\"menu1\",\r\n"
			+ "					\"type\":\"dinner\",\r\n" + "					\"menuItemList\":[\r\n"
			+ "									]				\r\n" + "					}";

	@Test
	public void testAddMenu() throws Exception {
		Menu m1 = new Menu(dummyMenuDto());
		Mockito.when(menuService.addMenu(Mockito.anyObject())).thenReturn(m1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/menu/addMenu").accept(MediaType.APPLICATION_JSON)
				.content(exampleJSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertTrue(response.getContentAsString().contains("menu1"));
		assertTrue(response.getContentAsString().contains("breakfast"));

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	private static MenuDto dummyMenuDto() {
		// TODO Auto-generated method stub
		MenuDto m = new MenuDto();
		m.setId("menu1");
		m.setType("breakfast");
		m.setMenuItemList(null);
		return m;
	}

	@Test
	public void testDeleteMenu() throws Exception {
		Mockito.when(menuService.deleteMenu("menu1")).thenReturn(1);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/menu/deleteMenu/menu1");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertTrue(response.getContentAsString().contains(("Menu Deleted")));
	}

	@Test
	public void testGetMenu() throws Exception {
		Mockito.when(menuService.findeMenu("menu1")).thenReturn(new Menu(dummyMenuDto()));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/menu/getMenu/menu1");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertTrue(response.getContentAsString().contains("breakfast"));
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testChangeMenu() throws Exception {
		Menu m1 = new Menu(dummyMenuDto());
		Mockito.when(menuService.updateMenu(Mockito.anyObject())).thenReturn(m1);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/menu/changeMenu")
				.accept(MediaType.APPLICATION_JSON).content(exampleJSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertTrue(response.getContentAsString().contains("breakfast"));
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

}
