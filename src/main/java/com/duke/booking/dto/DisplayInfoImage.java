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
public class DisplayInfoImage {
	
	private int displayInfoId;
	
	
	private int fileId;
	
	
	private int displayInfoImageId;
	
	@NonNull
	private String contentType;
	
	@NonNull
	private String createDate;
	
	@NonNull
	private String fileName;
	
	@NonNull
	private String modifyDate;
	
	@NonNull
	private String saveFileName;
	
	
	private boolean deleteFlag;
}
