package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	
	Faker faker;
	User userPayload;
	
	
	public Logger logger;
	
	
	@BeforeClass
	 public void setUp() {
		 
		 
		 faker = new Faker();
		 userPayload = new User();
		 
		 
		 userPayload.setId(faker.idNumber().hashCode());
		 userPayload.setUsername(faker.name().username());
		 userPayload.setFirstname(faker.name().firstName());
		 userPayload.setLastname(faker.name().lastName());
		 userPayload.setEmail(faker.internet().safeEmailAddress());
		 userPayload.setPassword(faker.internet().password(5, 10));
		 userPayload.setPhone(faker.phoneNumber().cellPhone());
		 
		 
		 
		 logger = LogManager.getLogger(this.getClass());
		 
	 }
	 @Test(priority=1)
	 public void testPostUser() {
		 
		 logger.info("******************  creating user     ******************");
		 
		Response response =UserEndpoints.createUser(userPayload);
		 response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(), 200);
		 
		 logger.info("******************   user is created      ******************");
		 
	 }
	 
	 @Test(priority=2)
	 public void testGetUserByName() {
		 
		 logger.info("******************  get the  user info     ******************");
		 
		Response response = UserEndpoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		 logger.info("******************  Sucessfully get the  user info     ******************");
			 
		 
		  }
	 
	 @Test(priority=3)
	 public void testUpdateUserByName() {
		 
		 //update data using payload
		 userPayload.setFirstname(faker.name().firstName());
		 userPayload.setLastname(faker.name().lastName());
		 userPayload.setEmail(faker.internet().safeEmailAddress());
		 
		 logger.info("******************  update  the  user info     ******************");
		 
		Response response =UserEndpoints.updateUser(this.userPayload.getUsername(),userPayload);
		 response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(), 200);
		 
		 //checking data after update 
		 Response responseafter = UserEndpoints.readUser(this.userPayload.getUsername());
		 Assert.assertEquals(responseafter.getStatusCode(), 200);
		 
		 logger.info("******************  Sucessfully update  the  user info     ******************");
				 
    }
	 
	 @Test(priority=4)
		 
		 public void deleteUserByName() {
		 
		 logger.info("******************  delete  the  user info     ******************");
		 
		Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		 logger.info("******************  sucessfully delete  the  user info     ******************");
		
		 
		 
		 
		 
	 }
	 
	 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
	 
	  
}
