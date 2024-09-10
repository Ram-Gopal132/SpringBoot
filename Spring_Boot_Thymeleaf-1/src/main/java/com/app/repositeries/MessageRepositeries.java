package com.app.repositeries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Message;

@Repository
public interface MessageRepositeries extends JpaRepository<Message, Integer> {

}
