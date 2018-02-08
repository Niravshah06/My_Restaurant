package com.myeample.restapi.restaurants.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
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

import com.myeample.restapi.restaurants.dto.MenuItemDto;
import com.myeample.restapi.restaurants.models.MenuItem;
import com.myeample.restapi.restaurants.service.MenuItemService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MenuItemController.class, secure = false)
public class MenuItemControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MenuItemService menuItemService;

	@Before
	public void setUp() throws Exception {
	}

	private MenuItemDto dummyMenuItemDto() {
		MenuItemDto m = new MenuItemDto();
		m.setCategory("german");
		m.setCost(200d);
		m.setDescription("description2");
		m.setName("Nirav");
		return m;
	}

	private MenuItemDto dummyChnagedMenuItemDto() {
		MenuItemDto m = new MenuItemDto();
		m.setCategory("italian");
		m.setCost(200d);
		m.setDescription("changed");
		m.setName("Nirav changed");
		return m;
	}

	@Test
	public void testAddMenuItemPositive() throws Exception {

		MenuItem m1 = new MenuItem(dummyMenuItemDto());
		Mockito.when(menuItemService.addMenuItem(Mockito.anyObject())).thenReturn(m1);

		String exampleMenuItemJson = "{\r\n" + "						\"description\":\"description2\",\r\n"
				+ "						\"name\":\"Nirav\",\r\n" + "						\"cost\":200,\r\n"
				+ "						\"category\":\"german\"\r\n" + "								}";

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/menuItem/addMenuItem")
				.accept(MediaType.APPLICATION_JSON).content(exampleMenuItemJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertTrue(response.getContentAsString().contains("description2"));
		assertTrue(response.getContentAsString().contains("german"));

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testAddMenuItemNegative() throws Exception {
		String exampleMenuItemJson = "{\r\n" + "						\"description\":\"description2\",\r\n"
				+ "						\"cost\":200,\r\n" + "						\"category\":\"german\"\r\n"
				+ "								}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/menuItem/addMenuItem")
				.accept(MediaType.APPLICATION_JSON).content(exampleMenuItemJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());

		assertTrue(response.getContentAsString().contains(("Menu Item already exist/MEnu Item Empty")));
		// body and object asset

	}

	@Test
	public void testChangeMenuItem() throws Exception {
		MenuItem m1 = new MenuItem(dummyChnagedMenuItemDto());
		Mockito.when(menuItemService.updateMenuItem(Mockito.anyObject())).thenReturn(m1);

		String exampleMenuItemJson = "{\r\n" + "						\"description\":\"description2\",\r\n"
				+ "						\"name\":\"Nirav\",\r\n" + "						\"cost\":200,\r\n"
				+ "						\"category\":\"german\"\r\n" + "								}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/menuItem/changeMenuItem")
				.accept(MediaType.APPLICATION_JSON).content(exampleMenuItemJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		// System.out.println(response.getContentAsString());
		assertTrue(response.getContentAsString().contains("changed"));
		assertTrue(response.getContentAsString().contains("italian"));

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testDeleteMenuItemByName() throws Exception {
		Mockito.when(menuItemService.deleteMenuItem("Nirav")).thenReturn(1);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/menuItem/deleteMenuItemByName/Nirav");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertTrue(response.getContentAsString().contains(("Menu Item Deleted")));

	}

	@Test
	public void testGetMenuItemByName() throws Exception {
		Mockito.when(menuItemService.getMenuItem("Nirav")).thenReturn(new MenuItem(dummyMenuItemDto()));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/menuItem/getMenuItemByName/Nirav");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertTrue(response.getContentAsString().contains("description2"));
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

}
