package com.lcwd.rating.services;

import java.util.List;

import com.lcwd.rating.entities.Rating;

public interface RatingService {

	
	
	//create
	
	Rating create(Rating rating);
	
	//get all rating
	
	List<Rating> getRating();
	
	
	
	
	//get All by UserId
	
	
	List<Rating> getRatingByUserId(String userId);
	
	
	//get all by hotel
	
	List<Rating> getRatingByHotelId(String hotelid);
	
}
