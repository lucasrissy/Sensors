package com.example.sensor.service;

import com.example.sensor.dto.AuthenticatorDto;
import com.example.sensor.entity.User;
import com.example.sensor.mapper.AuthenticationMapper;
import com.example.sensor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public void save (User dto){
        repository.save(dto);
    }
}
