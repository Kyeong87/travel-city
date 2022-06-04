package com.travel.city.controller;

import com.travel.city.config.JwtTokenProvider;
import com.travel.city.domain.MemberForm;
import com.travel.city.domain.Member;
import com.travel.city.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log
public class MemberController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody MemberForm login){
        Member member = memberRepository.findById(login.getId()).orElseThrow(()->
                new IllegalArgumentException("아이디를 확인해주세요."));
        //비밀번호 암호화
        if(!passwordEncoder.matches(login.getPassword(), member.getPassword())){
            throw new IllegalArgumentException("비밀번호를 확인해주세요.");
        }

        Map<String, String> response = new HashMap<>();
        // 토큰 생성
        response.put("token", jwtTokenProvider.createToken(member.getId()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}