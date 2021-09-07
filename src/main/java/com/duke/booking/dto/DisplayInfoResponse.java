package com.duke.booking.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DisplayInfoResponse {
	  
	  private double averageScore;
	  
	  @NonNull
	  private DisplayInfo displayInfo;

	  @NonNull
	  private DisplayInfoImage displayInfoImage;

	  @NonNull
	  private List<ProductImage> productImages;

	  @NonNull
	  private List<ProductPrice> productPrices;

	  @NonNull
	  private List<Comment> comments;



	  private String reservationDate;
}
