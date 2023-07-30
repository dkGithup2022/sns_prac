package com.dk0124.sns.repository.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dk0124.sns.model.user.User;

@Repository
public interface UserCacheRepository extends CrudRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
