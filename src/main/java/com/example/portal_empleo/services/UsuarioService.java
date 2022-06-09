package com.example.portal_empleo.services;

import com.example.portal_empleo.domain.Usuario;
import com.example.portal_empleo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario findByUsername(String username){
        Optional<Usuario> user= usuarioRepository.findByUsername(username);
        if (user.isPresent()){
            return user.get();
        }
        return null;
    }

    public List<Usuario> findAllByActive(){
        List<Usuario> users = usuarioRepository.findAllByActive();
        return users;
    }

    public UserDetails loadUserByUsername(String username) {
        Optional<Usuario> resp = usuarioRepository.findByUsername(username);
        if(resp.isPresent()) {
            Usuario usuario = resp.get();
            List<GrantedAuthority> roles = new ArrayList<>();
            GrantedAuthority p1 = new SimpleGrantedAuthority(usuario.getRol());
            roles.add(p1);
            //esto me permite guardar el usuario logueado para usarlo mas tarde
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession();
            session.setAttribute("usuariossession", usuario);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(usuario.getPassword());

            User user = new User(usuario.getUsername(), encodedPassword ,roles);
            return user;
        }
        return null;
    }

}
