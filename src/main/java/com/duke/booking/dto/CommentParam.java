package com.duke.booking.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentParam {
	@JsonIgnore
	private MultipartFile attachedImage;
	
	@NotBlank(message = "Please enter a review")
	private String comment;
	
	@NotNull
	@Positive
	private int productId;
	
	@NotNull
	@Positive
	private int reservationInfoId;
	
	@NotNull
	@PositiveOrZero
	private int score;

}
