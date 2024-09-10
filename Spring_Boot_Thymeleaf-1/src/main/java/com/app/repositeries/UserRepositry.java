package com.app.repositeries;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.User;

@Repository
public interface UserRepositry extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);

}
