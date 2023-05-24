package com.shinhan.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.shinhan.education.vo.PDSBoard;

public interface PDSBoardRepository extends CrudRepository<PDSBoard, Long>{
	
	//Query는 select 만 가는ㅇ. dml사용시 @modyfying붙이기
	//여기에 있으면 commit된다 
	@Modifying
	@Query("UPDATE FROM PDSFile f set f. pdsfilename = ?2 WHERE f.fno = ?1 ")
	int updateFile(Long fno,String newFileName);
	
	
	@Query("select b.pname, b.pwriter, f.pdsfilename "
			+ " from PDSBoard b left outer join b.files2 f "
			+ " where b.pid = ?1 order by b.pid ")
			public List<Object[]> getFilesInfo(long pid);

			//fatch가 lazy면
		@Query(value="select b.pid, count(*)"
				+ "from tbl_pdsboard b left outer join tbl_pdsfiles f "
				+ " on(b.pid = f.pdsno)"
				+ " group by b.pid",nativeQuery = true)	
		public List<Object[]> getFilesCount();
}
