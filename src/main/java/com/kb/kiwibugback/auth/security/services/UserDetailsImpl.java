package com.kb.kiwibugback.auth.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.kb.kiwibugback.employee.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private final Long id;

	private final String username;

	private final String email;

	private final String employeeName;

	@JsonIgnore
	private final String password;

	private final Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(final Long id, final String username, final String email, final String employeeName, final String password,
						   final Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.employeeName = employeeName;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(final Employee employee) {
		final List<GrantedAuthority> authorities = employee.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				employee.getEmployeeId(),
				employee.getUsername(),
				employee.getEmail(),
				employee.getEmployeeName(),
				employee.getPassword(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public Long getId() {
		return this.id;
	}

	public String getEmail() {
		return this.email;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	public String getName() {
		return this.employeeName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || this.getClass() != o.getClass())
			return false;
		final UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(this.id, user.id);
	}
}
