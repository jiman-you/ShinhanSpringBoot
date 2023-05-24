package com.shinhan.education;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.MemberRepository;
import com.shinhan.education.repository.ProfileRepository;
import com.shinhan.education.vo.MemberDTO;
import com.shinhan.education.vo.MemberRole;
import com.shinhan.education.vo.ProfileDTO;

import lombok.extern.java.Log;

@SpringBootTest
@Log
public class ManyToOneTest {

	@Autowired
	MemberRepository mrepo;
	@Autowired
	ProfileRepository prepo;

	//member의 profile갯수를 얻가
	//@Test
	public void getprofileCount() {
		List<Object> result = prepo.getMemberWithProfileCount("user");
		result.forEach(arr->log.info(Arrays.toString((long[]) arr)));
	}
	
	//@Test
	void addmember() {
		IntStream.range(1, 4).forEach(i->{
			MemberDTO member = MemberDTO.builder()
					.mid("manager-"+i)
					.mname("매니저"+i)
					.mpassword("8888")
					.mrole(MemberRole.MANAGER)
					.build();
			
			mrepo.save(member);
		});
	}
	
	//해당 profile의 memeber정보 알아내기
	@Test
	public void getMemberByProfile() {
		Long pno = 106L;
		prepo.findById(pno).ifPresent(p->{
			MemberDTO member = p.getMember();
			log.info(p.isCurrentYn()+":"+member.getMname()+"----"+member.getMrole());
		});
	}
	
	
	//특정 멤버의 profile조회하기
	@Test
	public void profileSerch() {
		MemberDTO member = mrepo.findById("user1").orElse(null);
		if(member != null) {
			List<ProfileDTO> plist =prepo.findByMember(member);
			plist.forEach(profile->log.info(profile.toString()));
		}
		
	}
	
	
	
	//@Test
	public void profileInsertTest() {
		//'user1'의 profile 5건 입력
		MemberDTO member = mrepo.findById("user1").orElse(null);
		MemberDTO member2 = mrepo.findById("user2").orElse(null);
		/*
		if(member!=null) {
			log.info(member.toString());
			
			IntStream.range(1, 6).forEach(i->{
				ProfileDTO profile = ProfileDTO.builder()
						.fname("face-"+i+".jpg")
						.currentYn(i==5?true:false)
						.member(member)
						.build();
				prepo.save(profile);
			});
		}*/
		if(member2!=null) {
			log.info(member2.toString());
			
			IntStream.range(1, 6).forEach(i->{
				ProfileDTO profile = ProfileDTO.builder()
						.fname("hair-"+i+".jpg")
						.currentYn(i==5?true:false)
						.member(member2)
						.build();
				prepo.save(profile);
			});
		}
		prepo.findAll().forEach(profile->log.info(profile.toString()));
	}

	// @Test
	public void memberSelectAll() {
		mrepo.findAll().forEach(member -> log.info(member.toString()));
	}

	// memberTable에 member10명 입력하기
	// @Test
	public void inmember10() {
		IntStream.range(1, 10).forEach(i -> {
			MemberDTO member = MemberDTO.builder().mid("user" + i).mname("멤버" + i).mpassword("1234")
					.mrole(MemberRole.USER).build();
			if (i <= 5) {
				member.setMrole(MemberRole.USER);
			} else {
				member.setMrole(MemberRole.ADMIN);
			}
			mrepo.save(member);
		});
	}

}
