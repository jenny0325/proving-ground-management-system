package com.hmg.pmg.auth.service;

import com.hmg.pmg.common.exception.NotFoundException;
import com.hmg.pmg.member.entity.Member;
import com.hmg.pmg.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
  private final MemberRepository memberRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return memberRepository.findByEmail(username)
        .map(this::createUserDetails)
        .orElseThrow(NotFoundException::new);
  }

  private UserDetails createUserDetails(Member member) {
    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getMemberType().toString());
    Set<GrantedAuthority> authorities = new HashSet<>();
    authorities.add(grantedAuthority);

    return new User(String.valueOf(member.getId()),
        member.getPassword(), authorities);
  }
}
