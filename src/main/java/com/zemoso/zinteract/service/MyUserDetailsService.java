package com.zemoso.zinteract.service;


import com.zemoso.zinteract.model.MyUserDetails;
import com.zemoso.zinteract.model.User;
import com.zemoso.zinteract.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  /**
   * loads user by name and supplies to web security Configurer adapter
   *
   * @param userName login user name
   * @return Userdetails
   * @throws UsernameNotFoundException if data not found
   */
  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUserName(userName);
    user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
    return user.map(MyUserDetails::new).get();
  }
}
