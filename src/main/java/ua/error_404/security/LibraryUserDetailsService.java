package ua.error_404.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.error_404.entity.Author;
import ua.error_404.service.AuthorService;

import java.util.HashSet;
import java.util.Set;

@Service("LibraryUserDetailsService")
public class LibraryUserDetailsService implements UserDetailsService {

    @Autowired
    AuthorService authorService;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Author author = authorService.findById(Long.parseLong(id));

        Set<GrantedAuthority> authorities = new HashSet();
        authorities.add(new SimpleGrantedAuthority("USER"));

        return new User(id, author.getName(), authorities);
    }
}
