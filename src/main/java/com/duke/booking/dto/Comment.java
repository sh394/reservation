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
public class Comment {
	
	private int commentId;
	
	
	private int productId;
	
	
	private int reservationInfoId;
	
	
	private double score;
	
	@NonNull
	private String comment;
	
	@NonNull
	private String createDate;
	
	@NonNull
	private String modifyDate;
	
	@NonNull
	private String reservationDate;
	
	@NonNull
	private String reservationEmail;
	
	@NonNull
	private String reservationName;
	
	@NonNull
	private String reservationTelephone;
	
	@NonNull
	private List<CommentImage> commentImages;
	
	
}
