package com.shinhan.education;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.DeptVORepository;
import com.shinhan.education.repository.EmpVORepository;
import com.shinhan.education.vo2.DeptVO;
import com.shinhan.education.vo2.EmpVO;

@SpringBootTest
public class emptest {

	
	@Autowired
	EmpVORepository erepo;
	@Autowired
	DeptVORepository drepo;
	
	@Test
	void test1() {
		DeptVO dept = DeptVO.builder().department_name("IT").manager_id(200).location_id(2000).build();
		drepo.save(dept);
		EmpVO emp = EmpVO.builder().firsr_name("Steven").last_name("king").email("asd@asd").phone_number("010-101-010")
				.hire_date("03-06-05").job_id("AD VP").salary(2000).commission_pct(0.2).manager_id(50).dept(dept).build();
		erepo.save(emp);
	}
}
