
package com.traffsys.stock.Repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.traffsys.stock.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	boolean existsByUserName(String userName);

	Optional<User> findByUserName(String userName);

	//Optional<org.springframework.security.core.userdetails.User> findByUseremail(String Useremail);

	//User findByUserName(String userName);
	

}
