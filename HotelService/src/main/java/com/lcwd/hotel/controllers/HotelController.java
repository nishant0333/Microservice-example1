package com.lcwd.hotel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.hotel.entites.Hotel;
import com.lcwd.hotel.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	
	@Autowired
	private HotelService hotelService;
	//create
	
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
		
		Hotel hotel1 = hotelService.create(hotel);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
		//return new ResponseEntity<Hotel>(hotel1,HttpStatus.CREATED);
	}
	
	
	//getSingle
	@GetMapping("/{id}")
	 public ResponseEntity<Hotel> getHotel(@PathVariable String id){
		 
		 return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(id));
	 }
	
	//get All
	@GetMapping
	public ResponseEntity<List<Hotel>> getAll(){
		
		return ResponseEntity.ok(hotelService.getAll());
	}
	
	//delete hotel
	@DeleteMapping("/{id}")
	public ResponseEntity<Hotel> deleteHotel(@PathVariable String id){
		
		hotelService.deleteHotel(id);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
