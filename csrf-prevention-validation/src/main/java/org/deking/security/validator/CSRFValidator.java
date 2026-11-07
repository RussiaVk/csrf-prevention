package org.deking.security.validator;

import java.util.function.BiPredicate;

import javax.enterprise.context.ApplicationScoped;

import com.google.common.cache.Cache;

@ApplicationScoped
public class CSRFValidator implements BiPredicate<Cache<String, Boolean>, String> {
	@Override
	public boolean test(Cache<String, Boolean> csrfPreventionSaltCache, String salt) {
		return csrfPreventionSaltCache != null && salt != null
				&& ((Cache<String, Boolean>) csrfPreventionSaltCache).getIfPresent(salt) != null;
	}
}
