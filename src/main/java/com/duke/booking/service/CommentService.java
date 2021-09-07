package com.duke.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duke.booking.dto.Comment;
import com.duke.booking.dto.CommentImage;
import com.duke.booking.dto.CommentParam;
import com.duke.booking.dto.CommentResponse;
import com.duke.booking.dto.FileInfo;

@Service
public interface CommentService {
	
	  public List<Comment> getCommentsByDisplayInfoId(int displayInfoId);

	  public double getAveScoreByDisplayInfoId(int displayInfoId);

	  public List<CommentImage> getCommentImages(int displayInfoId, int commentId);

	  public FileInfo getFileInfoByCommentImageId(int commentImageId);

	  public CommentResponse addComment(CommentParam commentParam);

}
