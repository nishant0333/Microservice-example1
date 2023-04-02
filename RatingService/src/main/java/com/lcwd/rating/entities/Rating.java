package com.lcwd.rating.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name ="RATING")
public class Rating {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private String ratingId;
	private String userId;
	private String hotelId;
	private String rating;private String feedback;
	
	
}
