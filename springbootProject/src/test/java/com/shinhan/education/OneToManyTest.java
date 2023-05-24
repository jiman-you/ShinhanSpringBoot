package com.shinhan.education;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.PDSBoardRepository;
import com.shinhan.education.repository.PDSFIleRepository;
import com.shinhan.education.vo.PDSBoard;
import com.shinhan.education.vo.PDSFile;

import lombok.extern.java.Log;

@Log
@SpringBootTest
public class OneToManyTest {

		@Autowired
		PDSBoardRepository boardRepo;
		@Autowired
		PDSFIleRepository fileRepo;

		//보드별 파일의 건수출력
		@Test
		void fileCount() {
			boardRepo.findAll().forEach(board->{
				log.info("보드번호"+board.getPid()+"의 파일 수: "+board.getFiles2().size());
			});
			
			//lazy
			List<Object[]> blist = boardRepo.getFilesCount();
			blist.forEach(arr->{
				System.out.println(Arrays.toString( arr));
			});
		}
		
		
		//@Test
		public void test3() {
			boardRepo.getFilesInfo(155L).forEach(arr -> {
				System.out.println(Arrays.toString(arr));
				});
			
			PDSBoard board = boardRepo.findById(155L).orElse(null);
			if(board!=null) {
				System.out.println(board.getPname());
				}
			}
		
		
		//@Test
		void deleteByBoard() {
			Long[] arr = {117L,124L};
		Arrays.stream(arr).forEach(bno->{
			boardRepo.deleteById(bno);
		});
		}
		
		
		//@Test
		void deleteByFile(){
			Long[] arr = {144L,158L,162L,136L};
			Arrays.stream(arr).forEach(bno->{
				fileRepo.deleteById(bno);
			});
		}
		
		
		//@Test
		void selectAllBoard() {
			boardRepo.findAll().forEach(board->{
				log.info("보드이름:"+board.getPname()+":작성자"+board.getPwriter()+":첨부파일"+board.getFiles2().size()+"건");
			});
		}
		
		
		//@Test
		void inserAll() {
			IntStream.range(20, 30).forEach(j->{
				List<PDSFile> flist = new ArrayList<>();
				IntStream.range(1, 6).forEach(i->{

					PDSFile f = PDSFile.builder()
							.pdsfilename("shinfile-"+i+".java")
							.build();
					flist.add(f);
				});
				PDSBoard board = PDSBoard.builder()
				         .pname("SpringFramework---"+j)
				         .pwriter("은빈")
				         .files2(flist)
						.build();
				boardRepo.save(board);
			});
			
		}
}

