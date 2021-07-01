package com.myweb.orm;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myweb.domain.FilesVO;
import com.myweb.persistence.files.FilesDAORule;

@Component
public class FileSweeper { // 파일 청소
	private static Logger logger = LoggerFactory.getLogger(FileSweeper.class);
	private final String BASE_PATH = "C:\\_javaweb\\_spring\\workspace\\upload\\";
	
	@Inject
	private FilesDAORule fdao;
	
	// 초 분 시 일 월 요일 년도(생략가능)
	
	@Scheduled(cron = "35 35 17 * * *")
	public void fileSweep() throws Exception {
		logger.info(">>> FileSweeper > fileSweep() - Running Start");
		logger.info(">>> Running Start Time : " + LocalDateTime.now());
		
		List<FilesVO> dbFileList = fdao.selectList(); // 전체 파일 리스트 가지고나와야됨
		
		// DB에 등록된 파일의 저장경로
		ArrayList<String> currFiles = new ArrayList<String>();
		for (FilesVO fvo : dbFileList) {
			String file_path = fvo.getSavedir() + "\\" + fvo.getUuid() + "_";
			String file_name = fvo.getFname();
			currFiles.add(BASE_PATH + file_path + file_name);
			if(fvo.getFtype() > 0 ) { // image냐?
				// 이미지 파일의 썸네일 경로
				currFiles.add(BASE_PATH + file_path + "th_" + file_name);
			}
		}
		for (String filePath : currFiles) { // 확인용
			logger.info(">>> Stored File Path : " + filePath);
		}
		
		// 저장되어 있는 모든 파일 검색
		LocalDate now = LocalDate.now();
		String today = now.toString();
		today = today.replace("-", File.separator);
		
		File dir = Paths.get(BASE_PATH + today).toFile();
		File[] allFileObjs = dir.listFiles();
		
		for (File file : allFileObjs) {
			String StredFileName = file.toPath().toString();
			if(!currFiles.contains(StredFileName)) file.delete();
		}
	}
}
