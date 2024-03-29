package com.shinhan.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.shinhan.education.vo.MemberDTO;
import com.shinhan.education.vo.ProfileDTO;

public interface ProfileRepository 
	extends CrudRepository<ProfileDTO, Long>{
		 List<ProfileDTO> findByMember(MemberDTO member);

		 @Query("select m.mid, count(p)"
				 +" from MemberDTO m left join ProfileDTO p on(m.mid = p.member)"
				+" where m.mid like %?1% group by m.mid" )
		 List<Object> getMemberWithProfileCount(String mid);
}

