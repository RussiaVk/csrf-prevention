package org.deking.security;

import java.security.SecureRandom;

import javax.enterprise.context.ApplicationScoped;

import org.apache.commons.lang3.RandomStringUtils;

@ApplicationScoped
public class SaltProviderImpl implements ISaltProvider{

	@Override
	public String getSalt() {
		String salt = RandomStringUtils.random(20, 0, 0, true, true, null, new SecureRandom());
		return salt;
	}

}
