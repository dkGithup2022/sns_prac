package com.dk0124.sns.fixtureFactory;

import static org.jeasy.random.FieldPredicates.*;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import com.dk0124.sns.model.user.UserEntity;

public class UserEntityFixtureFactory {

	public static UserEntity createUserRoleUser() {
		var param = new EasyRandomParameters()
			.excludeField(named("id"))
			.excludeField(named("deletedAt"))
			.excludeField(named("createdAt"))
			.excludeField(named("updatedAt"))
			.stringLengthRange(5, 20);

		return new EasyRandom(param).nextObject(UserEntity.class);

	}

}
