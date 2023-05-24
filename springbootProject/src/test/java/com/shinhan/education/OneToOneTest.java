package com.shinhan.education;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.UserVORepository;
import com.shinhan.education.repository.UserVORepository2;
import com.shinhan.education.repository.UserCellPhoneVoRepository;
import com.shinhan.education.repository.UserCellPhoneVoRepository2;
import com.shinhan.education.repository.UserVO3Repository;
import com.shinhan.education.vo2.UserVO;
import com.shinhan.education.vo2.UserVO2;
import com.shinhan.education.vo2.UserVO3;
import com.shinhan.education.vo2.UserCellPhoneVO;
import com.shinhan.education.vo2.UserCellPhoneVO2;
import com.shinhan.education.vo2.UserCellPhoneVO3;

@SpringBootTest
public class OneToOneTest {

	@Autowired
	UserVORepository urepo;
	@Autowired
	UserCellPhoneVoRepository prepo;
	@Autowired
	UserVORepository2 urepo2;
	@Autowired
	UserCellPhoneVoRepository2 prepo2;
	@Autowired
	UserVO3Repository urepo3;
	@Autowired
	UserVO3Repository prepo3;
	
	@Test
	void test3(){
		UserCellPhoneVO3 phone = UserCellPhoneVO3.builder().phoneNumber("010101")
				.model("as").build();
		UserVO3 user = UserVO3.builder().userid("hong22").username("gildong")
				.phone(phone) .build();
		phone.setUser(user);
		urepo3.save(user);
		
	}
	
	//@Test
	void test2() {
		UserVO2 user = UserVO2.builder().
				userid("지만이")
				.username("지만")
				.build();
		
		UserCellPhoneVO2 phone = UserCellPhoneVO2.builder()
				.phoneNumber("01056210690")
				.model("아이폰")
				.user(user)
				.build();
		prepo2.save(phone);
	}
	
	//@Test
	void test1() {
		UserCellPhoneVO phone = UserCellPhoneVO.builder()
				.phoneNumber("01056210690")
				.model("아이폰")
				.build();
		prepo.save(phone);
		
		UserVO user = UserVO.builder()
				.userid("지만이")
				.username("지만")
				.phone(phone)
				.build();
		urepo.save(user);
	}
}
