package com.lcwd.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lcwd.hotel.entites.Hotel;


@Repository
public interface HotelRepository extends JpaRepository<Hotel,String> {

}
