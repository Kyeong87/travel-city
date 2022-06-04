package com.travel.city.config;

import com.travel.city.domain.Member;
import com.travel.city.repository.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Log
@Component
public class ApplicationRunner implements org.springframework.boot.ApplicationRunner {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args){

        // member 테이블에 데이터 insert
        String passwordEncode = passwordEncoder.encode("test1234"); //암호화처리
        Member member = new Member("pyk", passwordEncode,"박연경",10);
        memberRepository.save(member);

        log.info("member, data insert!");
    }
}
