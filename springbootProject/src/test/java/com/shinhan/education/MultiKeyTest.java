package com.shinhan.education;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.EnumTypeVORepository;
import com.shinhan.education.repository.MultiKeyARepository;
import com.shinhan.education.repository.MultiKeyBRepository;
import com.shinhan.education.vo.MemberRole;
import com.shinhan.education.vo2.EnumTypeVO;
import com.shinhan.education.vo2.MultiKeyADTO;
import com.shinhan.education.vo2.MultiKeyB;
import com.shinhan.education.vo2.MultiKeyBDTO;

@SpringBootTest
public class MultiKeyTest {

	@Autowired
	MultiKeyARepository ARepo;
	@Autowired
	MultiKeyBRepository BRepo;
	@Autowired
	EnumTypeVORepository ERepo;
	
	//@Test
	void test5() {
		ERepo.findById("asd").ifPresent(m->{
			System.out.println(m);
			for(MemberRole role:m.getMrole()) {
				System.out.println(role.name());
			}
		});

	}
	
	//@Test
	void test4() {
		ERepo.findAll().forEach(m->{
			System.out.println(m);
		});
	}
	
	@Test
	void test3() {
		Set<MemberRole> mrole = new HashSet<>();
		mrole.add(MemberRole.ADMIN);
		mrole.add(MemberRole.USER);
		
		EnumTypeVO vo = EnumTypeVO.builder()
				.mid("asdsd")
				.mname("gg")
				.mpass("aszx")
				.mrole(mrole)
				.build();
		ERepo.save(vo);
	}
	
	
	//@Test
	void test2() {
		MultiKeyB id = MultiKeyB.builder()
				.id1(1)
				.id2(2)
				.build();
		
		MultiKeyBDTO B = MultiKeyBDTO.builder()
				.id(id)
				.username("지만이")
				.address("홍대")
				.build();
		BRepo.save(B);
	}
	
	//@Test
	void test1() {
		MultiKeyADTO musing = MultiKeyADTO.builder()
				.id1(10)
				.id2(10)
				.userName("지만이")
				.address("홍대")
				.build();
		ARepo.save(musing);
		MultiKeyADTO musing2 = new MultiKeyADTO();
				musing.setId1(1);
				musing.setId2(2);
				musing.setUserName("wlaksdl");
				musing.setAddress("hongdae");
				
		ARepo.save(musing2);
	}
}
