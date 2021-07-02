package com.myweb.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class MemberVO implements UserDetails{
	private String email;
	private String pwd;
	private String nickname;
	private String regdate;
	private String auth;
	private boolean enabled;
	private int failcnt;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public int getFailcnt() {
		return failcnt;
	}
	public void setFailcnt(int failcnt) {
		this.failcnt = failcnt;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority(auth));
		return authList;
	}
	@Override
	public String getPassword() {
		return getPwd();
	}
	@Override
	public String getUsername() {
		return getEmail();
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
		return this.enabled;
	}
	
	// 개발 끝나면 지워야됨
	@Override
	public String toString() {
		return "MemberVO [email=" + email + ", pwd=" + pwd + ", nickname=" + nickname + ", regdate=" + regdate
				+ ", auth=" + auth + ", enabled=" + enabled + ", failcnt=" + failcnt + "]";
	}
	
}