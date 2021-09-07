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
public class Promotion {
	
	private int id;
	
	
	private int productId;
	
	
	private int productImageId;
	
	@NonNull
	private String productImageUrl;
}
