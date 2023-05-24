package com.shinhan.education.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shinhan.education.UploadFileUtils;

 
@Controller
public class UploadController {
	
	@Value("${spring.servlet.multipart.location}")
	String uploadPath ;
	
	@GetMapping("/shop/uploadTest")
	public void upload() {
		
	}
	
	@PostMapping("/shop/register")
	public String register(@RequestParam MultipartFile[] files, HttpServletRequest req) throws IOException, Exception {
		String imgUploadPath = uploadPath + File.separator + "upload";
		
		//업로드 폴더 생성하기
		File file = new File(imgUploadPath);
		if(!file.exists()) {
			file.mkdir();
		}
		
		//폴더생성
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
		String fileName= null;
		for(MultipartFile f:files) {
			String originFileName = f.getOriginalFilename();
			System.out.println("originFileName:" + originFileName);
			if(originFileName!=null && !originFileName.equals("")) {
				fileName = UploadFileUtils.fileUpload(imgUploadPath, originFileName,
						f.getBytes(), ymdPath);
				
			}else {
				fileName = File.separator + "images" + File.separator + "hide.png";
			}
			System.out.println(fileName);
		}
		return "redirect:uploadTest";
	}
}