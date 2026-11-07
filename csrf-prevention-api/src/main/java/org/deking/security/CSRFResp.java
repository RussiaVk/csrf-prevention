package org.deking.security;

public record CSRFResp(boolean success, String message, boolean data) {
}