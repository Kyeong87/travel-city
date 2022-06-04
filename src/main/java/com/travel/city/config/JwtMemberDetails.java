package com.travel.city.config;

import com.travel.city.domain.Member;
import com.travel.city.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtMemberDetails implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Member> result = memberRepository.findById(s);
        return result.orElseThrow(()->new UsernameNotFoundException("User " + s + " not found"));
    }
}
