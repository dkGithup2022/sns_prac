package com.dk0124.sns.repository.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dk0124.sns.model.user.User;
import com.dk0124.sns.model.user.UserEntity;
import com.dk0124.sns.model.user.UserRole;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class UserCacheRepositoryTest {

	@Autowired
	private UserCacheRepository userCacheRepository;

	@Autowired
	private UserEntityRepository userEntityRepository;

	@Test
	public void saveAndGet(){
		var entity = UserEntity.builder().email("email").password("pw").userRole(UserRole.USER).build();
		entity = userEntityRepository.save(entity);

		var user = User.fromEntity(entity);

		var user2 = userCacheRepository.save(user);
		var user3 = userCacheRepository.findByEmail(entity.getEmail());
		var user4 = userCacheRepository.findById(user.getId());
	}


}