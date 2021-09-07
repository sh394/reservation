package com.duke.booking.dto;

import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponse {
	  

	  
	  @Positive
	  private int commentId;
	  
	  
	  @Positive
	  private int productId;

	  
	  @Positive
	  private int reservationInfoId;

	  
	  private int score;

	  @NonNull
	  private String createDate;

	  @NonNull
	  private String modifyDate;
	  
	  @NonNull
	  private String comment;
	  
	  @NonNull
	  private CommentImage commentImage;
}
