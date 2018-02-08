package com.myeample.restapi.restaurants.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

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

import com.myeample.restapi.restaurants.dto.RestaurantDto;
import com.myeample.restapi.restaurants.models.Restaurant;
import com.myeample.restapi.restaurants.service.RestaurantService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RestaurantController.class, secure = false)
public class RestaurantControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RestaurantService restaurantService;

	private String exampleJSON = "{\r\n" + "	\"restaurantName\":\"dominos\",\r\n"
			+ "	\"owner\":\"Nirav Shah new\",\r\n" + "	\"contactNumber\":\"8888\",\r\n"
			+ "	\"cuisineType\":[\"nnnn\",\"mmm\"],\r\n" + "	\"locations\":[\"NYC\",\"NJ\",\"AAA\"],\r\n"
			+ "	\"menuList\":[\r\n" + "					{\r\n" + "					\"id\":\"menu1\",\r\n"
			+ "					\"type\":\"dinner\",\r\n" + "					\"menuItemList\":[\r\n"
			+ "								{\r\n" + "						\"description\":\"new description2\",\r\n"
			+ "						\"name\":\"new name2\",\r\n" + "						\"cost\":10,\r\n"
			+ "						\"category\":\"new1\"\r\n" + "								},\r\n"
			+ "								{\r\n"
			+ "								\"description\":\"new description24545453\",\r\n"
			+ "								\"name\":\"new name23\",\r\n"
			+ "								\"cost\":1,\r\n"
			+ "								\"category\":\"new2\"\r\n" + "								}\r\n"
			+ "									]				\r\n" + "					},\r\n"
			+ "					{\r\n" + "							\"id\":\"menu2\",\r\n"
			+ "			\"type\":\"new dinner\",\r\n" + "	\"menuItemList\":[\r\n" + "		{\r\n"
			+ "\"description\":\"vvvv\",\r\n" + "\"name\":\"xxqqqx\",\r\n" + "\"cost\":100,\r\n"
			+ "\"category\":\"new indian\"\r\n" + "}]\r\n" + "\r\n" + "}]\r\n" + "}\r\n" + "\r\n" + "";

	 @Test
	public void testGetRestaurantByName() throws Exception {
		Mockito.when(restaurantService.getrestaurantByName("Dominos")).thenReturn(new Restaurant(dummyRestaurantDto()));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restaurant/getRestaurantByName/Dominos");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertTrue(response.getContentAsString().contains("88888"));
		assertTrue(response.getContentAsString().contains("NIrav"));
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	private RestaurantDto dummyRestaurantDto() {
		// TODO Auto-generated method stub
		RestaurantDto r = new RestaurantDto();
		r.setRestaurantName("Dominos");
		r.setContactNumber("88888");
		r.setCuisineType(Arrays.asList("Italian", "Asian"));
		r.setLocations(Arrays.asList("NJ", "NY"));
		r.setOwner("NIrav");
		r.setMenuList(null);
		return r;
	}

	@Test
	public void testAddRestaurant() throws Exception {
		Restaurant m1 = new Restaurant(dummyRestaurantDto());
		Mockito.when(restaurantService.add(Mockito.anyObject())).thenReturn(m1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/restaurant/addRestaurant")
				.accept(MediaType.APPLICATION_JSON).content(exampleJSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertTrue(response.getContentAsString().contains("dominos"));
		assertTrue(response.getContentAsString().contains("NIrav"));

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testChnageRestaurant() throws Exception {
		Restaurant m1 = new Restaurant(dummyRestaurantDto());
		Mockito.when(restaurantService.update(Mockito.anyObject())).thenReturn(m1);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/restaurant/changeRestaurant")
				.accept(MediaType.APPLICATION_JSON).content(exampleJSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertTrue(response.getContentAsString().contains("dominos"));
		;
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testDeleteRestaurant() throws Exception {
		Mockito.when(restaurantService.deleteRestaurant("Dominos")).thenReturn(1);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/restaurant/deleteRestaurant/Dominos");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertTrue(response.getContentAsString().contains(("Restaurant Deleted")));
	}

}
