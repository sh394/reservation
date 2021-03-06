package com.duke.booking.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.duke.booking.dto.FileInfo;

public class FileUtil {
	  public static final String ROOT_DIR_FOR_WINDOW = "c:/tmp/";
	  public static final String REVIEW_IMG_FILE_PATH = "review_img/";
	  private static final String FILE_NAME_PATTERN = "([a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]+)(.(png|jpg))";

	  public static boolean download(FileInfo fileInfo, HttpServletResponse response) {
	    boolean didDownload = false;

	    String fileName = fileInfo.getFileName();
	    String saveFileName = ROOT_DIR_FOR_WINDOW + fileInfo.getSaveFileName();
	    String contentType = fileInfo.getContentType();

	    try (FileInputStream fis = new FileInputStream(saveFileName);
	        OutputStream out = response.getOutputStream();) {

	      int readCount = 0;
	      byte[] buffer = new byte[1024];
	      while ((readCount = fis.read(buffer)) != -1) {
	        out.write(buffer, 0, readCount);
	      }

	      response.setHeader("Content-Length", "" + readCount);
	      response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
	      response.setHeader("Content-Transfer-Encoding", "binary");
	      response.setHeader("Content-Type", contentType);
	      response.setHeader("Pragma", "no-cache;");
	      response.setHeader("Expires", "-1;");

	      didDownload = true;
	    } catch (Exception e) {
	     
	    }

	    return didDownload;
	  }

	  public static String upload(MultipartFile file, String rootPath, String uploadPath) {
	    
	    File dir = new File(rootPath + uploadPath);
	    checkDirectory(dir);

	    
	    String fileName = file.getOriginalFilename();
	    String filePath = rootPath + uploadPath + fileName;
	    if (isFileExist(filePath)) {
	      fileName = changeFileName(fileName);
	      filePath = rootPath + uploadPath + fileName;
	    }

	    try (FileOutputStream fos = new FileOutputStream(filePath);
	        InputStream is = file.getInputStream();) {
	      int readCount = 0;
	      byte[] buffer = new byte[1024];
	      while ((readCount = is.read(buffer)) != -1) {
	        fos.write(buffer, 0, readCount);
	      }

	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }

	    return uploadPath + fileName;
	  }

	  private static void checkDirectory(File dir) {
	    if (!dir.isDirectory()) {
	      dir.mkdirs();
	    }
	  }

	  private static boolean isFileExist(String filePath) {
	    return new File(filePath).exists();
	  }

	  private static String changeFileName(String fileName) {
	    long nowTime = System.currentTimeMillis();
	    return fileName.replaceFirst(FILE_NAME_PATTERN, "$1_" + nowTime + "$2");
	  }
}
