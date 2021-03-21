package com.deloitte.todolist.service;

import com.deloitte.todolist.configuration.UserAccountDetails;
import com.deloitte.todolist.model.UserAccount;
import com.deloitte.todolist.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService implements UserDetailsService {

    private static final String usernameRegex = "[A-Za-z0-9_]+";

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //filters the username to ensure that it does not contain anything malicious
        if (!username.matches(usernameRegex)) {
            throw new UsernameNotFoundException(username);
        }

        UserAccount user = userAccountRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserAccountDetails(user);
    }

    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
