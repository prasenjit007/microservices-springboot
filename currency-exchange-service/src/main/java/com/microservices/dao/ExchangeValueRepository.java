package com.microservices.dao;


import org.springframework.data.jpa.repository.JpaRepository;

//80 video
import com.microservices.bean.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
	ExchangeValue findByFromAndTo(String from, String to);
}
