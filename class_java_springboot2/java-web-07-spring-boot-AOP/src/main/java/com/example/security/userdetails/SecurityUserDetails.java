package com.example.security.userdetails;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SecurityUserDetails implements UserDetails{
	// 클래스명인 SecurityUserDetails 빨간줄 명령어 add unimplemented method 하면
	//  Collection<? extends GrantedAuthority> getAuthorities()
	// isAccountNonExpired()
	// isAccountNonLocked()
	// isCredentialsNonExpired()
	// isEnabled()
	// 추가됨
	
	private final int memberSeq;
	private final String username;
	private final String password;
	private final String nickname;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 권한을 추가해줘야 로그인 이후 오류 발생x
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	
}


