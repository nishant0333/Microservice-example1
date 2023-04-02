package com.lcwd.user.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;



@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		
		//generate unique userId
		String randomUserId = UUID.randomUUID().toString();
		
		user.setUserid(randomUserId);
		
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		
		List<User> findAllUserList = userRepository.findAll();
		
		
		
		
		return findAllUserList;
	}

	
	//get single user
	@Override
	public User getUser(String userId) {
		User user= userRepository.findById(userId)
			.orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server !! :" +userId));
		//fetch the rating of the above user from RATING SERVICE
		//http://localhost:8083/ratings/users/1d7a376e-a5a8-40cb-a756-13e404596421
		
		Rating [] arrayofRatingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserid(), Rating [].class);
		
		List<Rating> ratingofUser = Arrays.asList(arrayofRatingOfUser);
		
		logger.info("{} ",ratingofUser);
		
		List<Rating> ratingList = ratingofUser.stream().map(rating -> {
			//api call to hotel service to get hotel
			//http://localhost:8082/hotels/33b9a02d-a102-431c-925b-535e641ff431
			
//			ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//			
//			Hotel hotel = forEntity.getBody();
//			
//			logger.info("Responce status code: {} ",forEntity.getStatusCode());
			
			Hotel hotel=hotelService.getHotel(rating.getHotelId());
			
			//set the hotel to rating
			
			rating.setHotel(hotel);
			
			//return the rating
			
			return rating;
			
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		return user;
	}

}
