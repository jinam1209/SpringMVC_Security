package com.myweb.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.myweb.domain.MemberVO;
import com.myweb.persistence.member.MemberDAORule;

public class MemberAuthDetailService implements UserDetailsService {
	private static Logger logger = LoggerFactory.getLogger(MemberAuthDetailService.class);

	@Inject
	private MemberDAORule mdao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		MemberVO mvo = mdao.selectOne(email);
		logger.info(">>> mvo : " + mvo.toString());
		return mvo;
	}

}
