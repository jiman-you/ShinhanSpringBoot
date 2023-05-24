package com.shinhan.education;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.shinhan.education.repository.PDSBoardRepository;
import com.shinhan.education.repository.PDSFIleRepository;
import com.shinhan.education.vo.PDSBoard;
import com.shinhan.education.vo.PDSFile;

import oracle.net.aso.f;

@SpringBootTest
@Commit
public class OneToManyTest2 {

	@Autowired
	PDSFIleRepository fRepo;

	@Autowired
	PDSBoardRepository bRepo;
	
	//@Test
	void boardFileInsert() {
		bRepo.findById(3L).ifPresent(board->{
			List<PDSFile> files2 = board.getFiles2();
			PDSFile file1 = PDSFile.builder()
					.pdsfilename("추가1@!@@!")
					.build();
			PDSFile file2 = PDSFile.builder()
					.pdsfilename("추가1@!@@!")
					.build();
			files2.add(file1);files2.add(file2);
			bRepo.save(board);
		});
	}
	
	
	//board를 이용해 file이름 수정하기
	//@Test
	void boardFileUpdate() {
		bRepo.findById(3L).ifPresent(board->{
			List<PDSFile> files2 = board.getFiles2();
			files2.forEach(f->{
				f.setPdsfilename("수정~");
				fRepo.save(f);
			});
		});
	}
	
	//PDSBoardRepodisto을 이용해 file이름 수정
	//@modifting을 사용후 Trasactiont q반드시 사용
	//test에 @transactional 은 TEST환경은 롤백처리
	@Transactional
	//@Test
	void test7() {
		bRepo.updateFile(4L,"풍경사진");
	}
	
	
	
	
	//1-n 부모에서 자식을 insert
	//@Test
	void test6() {
		
		List<PDSFile> files = new ArrayList<PDSFile>();
		PDSFile file1 = PDSFile.builder().pdsfilename("얼굴사진").build();
		files.add(file1);
		
		PDSFile file2 = PDSFile.builder().pdsfilename("얼굴사진").build();
		files.add(file2);
		
		PDSFile file3 = PDSFile.builder().pdsfilename("얼굴사진").build();
		files.add(file3);
		
		PDSBoard board = PDSBoard.builder()
				.pname("월요일이다")
				.pwriter("진우")
				.files2(files)
				.build();
		bRepo.save(board);
	}
	
	
	//자바에서 힘들다...자바에 칼럼이 없으므로 다음 방법은 불가
	//@Test
	void test5() {
		PDSFile file = fRepo.findById(1L).orElse(null);
	
		if(file!=null) { 
			//PDSBoard board =  bRepo.findById(2L).orElse(null);
			file.setPdsfilename("파일이름수정");
			
			fRepo.save(file);
		}
		
		
	}
	
	//@Transactional//lazy일때도 자식에게 접근하기 위해 사용
	//@Test
	void test4() {
		bRepo.findAll().forEach(b->System.out.println(b));
	}
	
	
	
	//부모님 인서트
	//@Test
	void test3() {
		PDSBoard board = PDSBoard.builder()
				.pname("게시글")
				.pwriter("작성자")
				.build();
				bRepo.save(board);
	}
	
	//@Test
	void test2() {
		fRepo.findAll().forEach(f->System.out.println(f));
	}
	//자식만 인서트
	//@Test
	public void test1() {
		PDSFile file = PDSFile.builder()
				.pdsfilename("첨부파일1")
				.build();
			fRepo.save(file);
	
	}
	
	
}
