package com.kb.kiwibugback.auth.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kb.kiwibugback.employee.EmployeeRepository;
import com.kb.kiwibugback.employee.Employee;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  EmployeeRepository employeeRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    final Employee employee = this.employeeRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.build(employee);
  }

}
