package com.kb.kiwibugback.auth.payload.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String name;
	private String email;
	private final List<String> roles;

	public JwtResponse(final String accessToken, final Long id, final String username, final String name, final String email, final List<String> roles) {
		token = accessToken;
		this.id = id;
		this.username = username;
		this.name = name;
		this.email = email;
		this.roles = roles;
	}

	public String getAccessToken() {
		return this.token;
	}

	public void setAccessToken(final String accessToken) {
		token = accessToken;
	}

	public String getTokenType() {
		return this.type;
	}

	public void setTokenType(final String tokenType) {
		type = tokenType;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return this.roles;
	}
}
