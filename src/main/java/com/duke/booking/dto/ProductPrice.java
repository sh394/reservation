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
public class ProductPrice {
	 @NonNull
	  private String createDate;

	 @NonNull
	  private String priceTypeName;

	  @NonNull
	  private String modifiyDate;

	 
	  private int price;

	  
	  private int productId;

	  
	  private int productPriceId;
	  
	  
	  private double discountRate;
}
