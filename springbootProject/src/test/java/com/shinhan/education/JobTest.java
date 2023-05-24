package com.shinhan.education;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.JobsRepoditory;
import com.shinhan.education.vo.JobVO;

@SpringBootTest
public class JobTest {
	
	@Autowired
	JobsRepoditory repo;
	
	//@Test//D
	public void test6() {//CRUD...delete
		repo.deleteAll();
	}
	
	//@Test//D
	public void test5() {//CRUD...delete
		repo.findById("직책코드4").ifPresent(job->{
			//repo.delete(job);
			repo.deleteById("직책코드4");
		});
	}
	//@Test//U
	public void test4() {//CRUD...update
		repo.findById("직책코드3").ifPresent(job->{
			job.setJobTitle("마켓팅수정");
			job.setMaxSalary(10000);
			job.setMinSalary(2000);
			
			repo.save(job);
		});
	}
	
	//@Test//R(1)
	public void test3() {//CRUD...read
		 Optional<JobVO> jobOptional =  repo.findById("직책코드1");
		 if(jobOptional.isPresent()) {
			 JobVO job = jobOptional.get();
			 System.out.println(job);
		 }else {
			 System.out.println("존재하지안읍니다");
		 }
	}
	
	
	//@Test//R
	public void test2() {//CRUD...read
		Iterable<JobVO> datas =  repo.findAll();
		List<JobVO> joblist = (List<JobVO>)datas;
		for(JobVO job:joblist) {
			System.out.println(job);
		}
	}	
	
	@Test//C
	public void test1() {//CRUD...Create
	 
	 String arr[] = {"마켓팅","si개발자","sm개발자","매니저","aa",
			 "bb","ㅍㅍ","ㅇㅇ","ㅈㅈ","ㄷㄷ"};
	 	IntStream.range(1, 11).forEach(i->{
	 		JobVO job = JobVO.builder()
	 						.jobId("직책코드"+(10+i))
	 						.jobTitle(arr[i-1])
	 						.maxSalary(9000)
	 						.minSalary(110)
	 						.build();
	 		repo.save(job);
	 	});
 }
}
