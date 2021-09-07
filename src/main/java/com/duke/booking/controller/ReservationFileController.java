package com.duke.booking.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duke.booking.dto.FileInfo;
import com.duke.booking.service.CommentService;
import com.duke.booking.service.DisplayInfoService;
import com.duke.booking.service.ProductService;
import com.duke.booking.service.PromotionService;
import com.duke.booking.util.FileUtil;

@Controller
@RequestMapping(path="/download")
public class ReservationFileController {
	
	private final PromotionService promotionService;
	private final ProductService productService;
	private final DisplayInfoService displayInfoService;
	private final CommentService commentService;
	
	@Autowired
	public ReservationFileController(PromotionService promotionService, ProductService productService,
			DisplayInfoService displayInfoService, CommentService commentService) {
		
		this.promotionService = promotionService;
		this.productService = productService;
		this.displayInfoService = displayInfoService;
		this.commentService = commentService;
	}
	
	 @CrossOrigin
	  @GetMapping(path = "/promotions/{productImageId}")
	  public void getPromotionImage(@PathVariable(name = "productImageId") int productImageId,
	      HttpServletResponse response) {

	    FileInfo fileInfo = promotionService.getPromotionFileInfo(productImageId);
	    if (fileInfo == null) {
	    	throw new IllegalArgumentException();
	    }

	    boolean didDownload = FileUtil.download(fileInfo, response);
	    if (!didDownload) {
	    	throw new IllegalArgumentException();
	    }
	  }

	  @CrossOrigin
	  @GetMapping(path = "/products/{productImageId}")
	  public void getProductImage(@PathVariable(name = "productImageId") int productImageId,
	      HttpServletResponse response) {

	    FileInfo fileInfo = productService.getProductFileInfoByProductImgId(productImageId);

	    if (fileInfo == null) {
	    	throw new IllegalArgumentException();
	    }

	    boolean didDownload = FileUtil.download(fileInfo, response);
	    if (!didDownload) {

	    }
	  }

	  @CrossOrigin
	  @GetMapping(path = "/displays/{displayInfoImageId}")
	  public void getDisplayInfoImage(@PathVariable(name = "displayInfoImageId") int displayInfoImageId,
	      HttpServletResponse response) {

	    FileInfo fileInfo = displayInfoService.getFileInfoByDisplayInfoImageId(displayInfoImageId);

	    if (fileInfo == null) {
	    	throw new IllegalArgumentException();
	    }

	    boolean didDownload = FileUtil.download(fileInfo, response);
	    if (!didDownload) {

	    }
	  }

	  @CrossOrigin
	  @GetMapping(path = "/comments/{commentImageId}")
	  public void getCommentImage(@PathVariable(name = "commentImageId") int commentImageId,
	      HttpServletResponse response) {

	    FileInfo fileInfo = commentService.getFileInfoByCommentImageId(commentImageId);

	    if (fileInfo == null) {
	      throw new IllegalArgumentException();
	    }

	    boolean didDownload = FileUtil.download(fileInfo, response);
	    if (!didDownload) {
	    	throw new IllegalArgumentException();
	    }
	  }
	
	

}
