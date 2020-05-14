package com.saurav.flashsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saurav.flashsale.entity.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{

}
