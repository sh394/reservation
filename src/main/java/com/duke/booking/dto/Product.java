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
public class Product {
	
	
	private int displayInfoId;
	
	
	private int productId;
	
	
	private int productImageId;
	
	@NonNull
	private String placeName;
	
	@NonNull
	private String productContent;
	
	@NonNull
	private String productDescription;
	
	@NonNull
	private String productImageUrl;
	
	

}
