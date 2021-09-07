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
public class ProductImage {
	
	@NonNull
	private String contentType;
	
	@NonNull
	private String createDate;
	
	@NonNull
	private String fileName;
	
	@NonNull
	private String modifiyDate;
	
	@NonNull
	private String saveFileName;
	
	@NonNull
	private String type;
	
	
	
	
	
	private int fileInfoId;
	
	
	private int productId;
	
	
	private int productImageId;
	
	
	
	
	
	private boolean deleteFlag;

}
