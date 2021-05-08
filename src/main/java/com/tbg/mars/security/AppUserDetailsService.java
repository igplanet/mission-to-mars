package com.tbg.mars.security;

import com.tbg.mars.entity.Colonist;
import com.tbg.mars.repo.ColonistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private ColonistRepo colonistRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Colonist colonist = colonistRepo.findByColonistId(username);

        if (colonist == null) {
            throw new UsernameNotFoundException("Colonist '" + username + "' not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(colonist.getPassword())
                .authorities(colonist.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}
