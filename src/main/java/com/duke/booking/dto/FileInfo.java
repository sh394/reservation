package com.duke.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileInfo {
	
	private int id;
	
	@NonNull
	private String fileName;
	
	@NonNull
	private String saveFileName;
	
	@NonNull
	private String contentType;
	
	@NonNull
	private String createDate;
	
	@NonNull
	private String modifyDate;
	
	
	private boolean deleteFlag;
	
	

}
