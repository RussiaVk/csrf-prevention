package org.deking.security;
@FunctionalInterface
public interface ISaltProvider {
	String getSalt();
}
