package com.resturant.restapi.auth;

import com.resturant.restapi.Model.Users;
import com.resturant.restapi.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user=usersRepository.getUsersByUSERNAME(username).get();

        if(user==null){
            throw new UsernameNotFoundException("could not found user");
        }

        return new UserDetailsImpl(user);
    }
}
