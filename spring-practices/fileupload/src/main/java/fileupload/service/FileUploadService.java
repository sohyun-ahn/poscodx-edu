package fileupload.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	private static String SAVE_PATH = "/Users/sohyunahn/ash/poscodx2024/fileupload-files"; // mac은 루트디렉터리 아래에 만들 수 없음
	private static String URL_PATH = "/images"; // 가상 url, save_path아래로 url-mapping 시킬예정 

	public String restore(MultipartFile file) {
		String url = null;
		try {
				File uploadDirectory = new File(SAVE_PATH);
				if (!uploadDirectory.exists()) {
					uploadDirectory.mkdirs(); // mkdir보다 mkdirs가 더 좋음 , 하위까지 한번에 만들기 가능
				}
		
				if (file.isEmpty()) {
					return url;
				}
		
				String originFileName = file.getOriginalFilename();
				String extName = originFileName.substring(originFileName.lastIndexOf(".") + 1); // 확장자 이름 가져오기
				String saveFileName = generateSaveFileName(extName);
				Long fileSize = file.getSize();
		
				System.out.println("#####" + originFileName);
				System.out.println("#####" + saveFileName);
				System.out.println("#####" + fileSize);
				
				byte[] data = file.getBytes();
				OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
				os.write(data);
				os.close();
				
				url = URL_PATH + "/" + saveFileName; // url mapping
				
			}catch(IOException ex) {
				throw new RuntimeException(ex);
			}
				
			return url; // 최종적으로 url을 만들어 내는게 목적
		}

	private String generateSaveFileName(String extName) {
		String filename = "";

		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);

		return filename;
	}
}
