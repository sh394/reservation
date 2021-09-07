package com.duke.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.duke.booking.dao.CommentDao;
import com.duke.booking.dao.CommentImageDao;
import com.duke.booking.dao.FileInfoDao;
import com.duke.booking.dto.Comment;
import com.duke.booking.dto.CommentImage;
import com.duke.booking.dto.CommentParam;
import com.duke.booking.dto.CommentResponse;
import com.duke.booking.dto.FileInfo;
import com.duke.booking.util.DateUtil;

import com.duke.booking.util.FileUtil;

@Service
public class DefaultCommentService implements CommentService {
	private CommentDao commentDao;
	private CommentImageDao commentImageDao;
	private FileInfoDao fileInfoDao;
	
	@Autowired
	public DefaultCommentService(CommentDao commentDao, CommentImageDao commentImageDao, FileInfoDao fileInfoDao) {
		this.commentDao = commentDao;
		this.commentImageDao = commentImageDao;
		this.fileInfoDao = fileInfoDao;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Comment> getCommentsByDisplayInfoId(int displayInfoId) {
		return commentDao.selectByDisplayInfoId(displayInfoId);
	}

	@Override
	@Transactional(readOnly = true)
	public double getAveScoreByDisplayInfoId(int displayInfoId) {
		return commentDao.selectAvgScoreByDisplayInfoId(displayInfoId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommentImage> getCommentImages(int displayInfoId, int commentId) {
		return commentImageDao.selectByDisplayInfoId(displayInfoId, commentId);
	}

	@Override
	@Transactional(readOnly = true)
	public FileInfo getFileInfoByCommentImageId(int commentImageId) {
		return fileInfoDao.selectByCommentImageId(commentImageId);
	}

	@Override
	public CommentResponse addComment(CommentParam commentParam) {
		int commentId = commentDao.insert(commentParam);

//	    MultipartFile file = commentParam.getAttachedImage();
//	    if (file != null) {
//	      String saveFileName =
//	          FileUtil.upload(file, FileUtil.ROOT_DIR_FOR_WINDOW, FileUtil.REVIEW_IMG_FILE_PATH);
//
//	      FileInfo fileInfo = FileInfo.builder().fileName(file.getOriginalFilename())
//	          .saveFileName(saveFileName).contentType(file.getContentType()).deleteFlag(false)
//	          .createDate(DateUtil.getNowDate()).modifyDate(DateUtil.getNowDate()).build();
//
//	      int fileInfoId = fileInfoDao.insert(fileInfo);
//
//	      int commentImgId =
//	          commentImageDao.insert(commentParam.getReservationInfoId(), commentId, fileInfoId);
//	    }

	    
	    CommentResponse commentResponse = CommentResponse.builder().comment("").commentId(commentId)
	        .commentImage(new CommentImage()).createDate(DateUtil.getNowDate())
	        .modifyDate(DateUtil.getNowDate()).productId(1).reservationInfoId(1).score(5).build();

	    return commentResponse;
	}

}
