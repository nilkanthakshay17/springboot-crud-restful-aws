package com.axee.springboot.api;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import com.axee.springboot.api.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
class SpringbootCrudRestfulWebservicesApplicationTests {


	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	ObjectMapper om=new ObjectMapper();
	
	
	@BeforeEach
	public void setUp() {
		mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void createUserTest() throws Exception {
		User u=new User();
		u.setFirstName("Akshay");
		u.setLastName("Nilkanth");
		u.setEmail("nilkanthakshay17@gmail.com");
		
		
		String jsonRequest=om.writeValueAsString(u);
		MvcResult result=mockMvc.perform(post("/api/v1/users")
				.content(jsonRequest)
				.contentType("application/json")
				.accept("application/json"))
				.andExpect(status().isCreated())
				.andReturn();
		String resultContent=result.getResponse().getContentAsString();
		
		User response=om.readValue(resultContent, User.class);
		
		assertTrue(u.getEmail().equals(response.getEmail() ));
		
	}

	// @Test
	// public void updateUserTest() throws Exception {
	// 	User u=new User();
	// 	u.setFirstName("Akshay222");
	// 	u.setLastName("Nilkanth222");
	// 	u.setEmail("axee222@gmail.com");
	// 	int id=2;
		
	// 	URI uri=new URI("/api/v1/users/"+id);
	// 	System.out.println(uri );
	// 	String jsonRequest=om.writeValueAsString(u);
	// 	MvcResult result=mockMvc.perform(put(uri)
	// 			.content(jsonRequest)
	// 			.contentType("application/json")
	// 			)
	// 			.andExpect(status().isOk())
	// 			.andReturn();
	// 	String resultContent=result.getResponse().getContentAsString();
		
	// 	User response=om.readValue(resultContent, User.class);
		
	// 	assertTrue(id==response.getId());
		
	// }
	
	// @Test
	// public void getUserByIdTest() throws Exception {
		
	// 	int id=2;
	// 	URI uri=new URI("/api/v1/users/"+id);

	// 	MvcResult result=mockMvc.perform(get(uri)
	// 			.content(MediaType.APPLICATION_JSON_VALUE))
	// 			.andExpect(status().isOk())
	// 			.andReturn();
	// 	String resultContent=result.getResponse().getContentAsString();
		
	// 	User response=om.readValue(resultContent, User.class);
		
	// 	assertTrue(id==response.getId());
		
	// }

//	@Test
//	public void DeleteUserTest() throws Exception {
//		
//		int id=2;
//		URI uri=new URI("/api/v1/users/"+id);
//
//		MvcResult result=mockMvc.perform(delete(uri)
//				.content(MediaType.APPLICATION_JSON_VALUE))
//				.andExpect(status().isOk())
//				.andReturn();
//		String resultContent=result.getResponse().getContentAsString();
//		
//		User response=om.readValue(resultContent, User.class);
//		
//		assertTrue(id==response.getId());
//		
//	}
	
	@Test
	public void getAllUsersTest() throws Exception {
	
		
		MvcResult result=mockMvc.perform(get("/api/v1/users")
				.content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andReturn();
		String resultContent=result.getResponse().getContentAsString();
		
		User[] responseList=om.readValue(resultContent, User[].class);
		
		for(User er:responseList)
			assertTrue(er.getId()>0);
		
	}

}
