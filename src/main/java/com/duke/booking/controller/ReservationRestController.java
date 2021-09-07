package com.duke.booking.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duke.booking.dto.Category;
import com.duke.booking.dto.CategoryResponse;
import com.duke.booking.dto.Comment;
import com.duke.booking.dto.CommentParam;
import com.duke.booking.dto.CommentResponse;
import com.duke.booking.dto.DisplayInfo;
import com.duke.booking.dto.DisplayInfoImage;
import com.duke.booking.dto.DisplayInfoResponse;
import com.duke.booking.dto.Product;
import com.duke.booking.dto.ProductImage;
import com.duke.booking.dto.ProductPrice;
import com.duke.booking.dto.ProductResponse;
import com.duke.booking.dto.Promotion;
import com.duke.booking.dto.PromotionResponse;
import com.duke.booking.dto.ReservationInfo;
import com.duke.booking.dto.ReservationInfoResponse;
import com.duke.booking.dto.ReservationParam;
import com.duke.booking.dto.ReservationResponse;
import com.duke.booking.service.CategoryService;
import com.duke.booking.service.CommentService;
import com.duke.booking.service.DisplayInfoService;
import com.duke.booking.service.ProductService;
import com.duke.booking.service.PromotionService;
import com.duke.booking.service.ReservationService;
import com.duke.booking.util.CollectionsUtil;
import com.duke.booking.util.DateUtil;

@RestController
@RequestMapping(path="/api")
public class ReservationRestController {
	private final PromotionService promotionService;
	private final CategoryService categoryService;
	private final ProductService productService;
	private final DisplayInfoService displayInfoService;
	private final CommentService commentService;
	private final ReservationService reservationService;
	
	@Autowired
	public ReservationRestController(PromotionService promotionService, CategoryService categoryService,
			ProductService productService, DisplayInfoService displayInfoService, CommentService commentService,
			ReservationService reservationService) {
		super();
		this.promotionService = promotionService;
		this.categoryService = categoryService;
		this.productService = productService;
		this.displayInfoService = displayInfoService;
		this.commentService = commentService;
		this.reservationService = reservationService;
	}
	 @CrossOrigin
	  @GetMapping("/promotions")
	  public Map<String, Object> getPromotions() {
	    List<Promotion> promotions = promotionService.getPromotions();

	    if (promotions.isEmpty()) {
	    	throw new IllegalArgumentException();
	    }

	    PromotionResponse promotionResponse = PromotionResponse.builder().items(promotions).build();

	    return CollectionsUtil.convertObjectToMap(promotionResponse);
	  }

	  @CrossOrigin
	  @GetMapping("/categories")
	  public Map<String, Object> getCategories() {
	    List<Category> categories = categoryService.getCategories();

	    if (categories.isEmpty()) {
	    	throw new IllegalArgumentException();
	    }

	    CategoryResponse categoryResponse = CategoryResponse.builder().items(categories).build();

	    return CollectionsUtil.convertObjectToMap(categoryResponse);
	  }

	  @CrossOrigin
	  @GetMapping("/products")
	  public Map<String, Object> getProducts(
	      @RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
	      @RequestParam(name = "start", required = false, defaultValue = "0") int start) {

	    List<Product> products;
	    int totalCount;

	    if (CategoryType.ALL.getCategoryId() == categoryId) {
	      products = productService.getProductsAll(start);

	      if (products.isEmpty()) {
	    	  throw new IllegalArgumentException();
	      }

	      totalCount = productService.getProductCountAll();
	    } else {
	      products = productService.getProductsByCategoryId(categoryId, start);

	      if (products.isEmpty()) {
	        throw new IllegalArgumentException();
	      }

	      totalCount = productService.getProductCountByCategoryId(categoryId);
	    }

	    ProductResponse productResponse =
	        ProductResponse.builder().items(products).totalCount(totalCount).build();

	    return CollectionsUtil.convertObjectToMap(productResponse);
	  }


	  @GetMapping("/products/{displayInfoId}")
	  @CrossOrigin
	  public Map<String, Object> getProducts(@PathVariable(name = "displayInfoId") int displayInfoId) {

	    DisplayInfo displayInfo = displayInfoService.getDisplayInfoById(displayInfoId);
	    DisplayInfoImage displayInfoImage = displayInfoService.getImagesByDisplayInfoId(displayInfoId);
	    List<Comment> comments = commentService.getCommentsByDisplayInfoId(displayInfoId);
	    List<ProductImage> productImages = productService.getImagesByDisplayInfoId(displayInfoId);
	    List<ProductPrice> productPrices = productService.getPricesByDisplayInfoId(displayInfoId);

	    if (displayInfo == null) {
	      throw new IllegalArgumentException();
	    }

	    if (displayInfoImage == null) {
	    	throw new IllegalArgumentException();
	    }

	    if (!comments.isEmpty()) {
	      for (Comment comment : comments) {
	        int commentId = comment.getCommentId();
	        comment.setCommentImages(commentService.getCommentImages(displayInfoId, commentId));
	      }
	    }

	    double averageScore = commentService.getAveScoreByDisplayInfoId(displayInfoId);
	    String reservationDate = reservationService.getRandomReservationDate();

	    DisplayInfoResponse displayInfoResponse =
	        DisplayInfoResponse.builder().displayInfo(displayInfo).displayInfoImage(displayInfoImage)
	            .productImages(productImages).productPrices(productPrices).comments(comments)
	            .averageScore(averageScore).reservationDate(reservationDate).build();

	    return CollectionsUtil.convertObjectToMap(displayInfoResponse);
	  }

	  @CrossOrigin
	  @GetMapping("/reservations")
	  public Map<String, Object> getReservations(@RequestParam(name = "reservationEmail",
	      required = true, defaultValue = "") String reservationEmail) {

	    List<ReservationInfo> reservationInfos =
	        reservationService.getReservationInfoByEmail(reservationEmail);
	    if (reservationInfos.isEmpty()) {
	      throw new IllegalArgumentException();
	    }

	    for (ReservationInfo reservationInfo : reservationInfos) {
	      int displayInfoId = reservationInfo.getDisplayInfoId();
	      int productId = reservationInfo.getProductId();
	      String reservationDate = reservationInfo.getReservationDate();

	      DisplayInfo displayInfo = displayInfoService.getDisplayInfoById(displayInfoId);
	      if (displayInfo == null) {
	    	  throw new IllegalArgumentException();
	      }
	      reservationInfo.setDisplayInfo(displayInfo);

	      int totalPrice = reservationService.getTotalPrice(reservationEmail, productId, displayInfoId,
	          reservationDate);
	      reservationInfo.setTotalPrice(totalPrice);
	    }

	    ReservationInfoResponse reservationInfoResponse = ReservationInfoResponse.builder()
	        .reservations(reservationInfos).size(reservationInfos.size()).build();

	    return CollectionsUtil.convertObjectToMap(reservationInfoResponse);
	  }

	  @CrossOrigin
	  @PutMapping(path = "/reservations/{reservationId}")
	  public Map<String, Object> putReservations(
	      @PathVariable(name = "reservationId") int reservationId) {

	    boolean cancelFlag = true;
	    String modifyDate = DateUtil.getNowDate();
	    ReservationResponse reservationResponse =
	        reservationService.changeCancelFlagById(reservationId, cancelFlag, modifyDate);

	    return CollectionsUtil.convertObjectToMap(reservationResponse);
	  }

	  @CrossOrigin
	  @PostMapping(path = "/reservations")
	  public Map<String, Object> postReservations(
	      @RequestBody(required = true) @Valid ReservationParam reservationParam) {

	    ReservationResponse reservationResponse = reservationService.addReservation(reservationParam);

	    return CollectionsUtil.convertObjectToMap(reservationResponse);
	  }

	  @CrossOrigin
	  @PostMapping(path = "/reservations/{reservationInfoId}/comments")
	  public Map<String, Object> postComments(@ModelAttribute @Valid CommentParam commentParam) {

	    CommentResponse commentResponse = commentService.addComment(commentParam);

	    return CollectionsUtil.convertObjectToMap(commentResponse);
	  }
	  
	  
	  public enum CategoryType {
		  ALL(0), DISPLAY(1), MUSICAL(2), CONCERT(3), CLASSIC(4), PLAY(5);

		  private final int categoryId;

		  private CategoryType(int categoryId) {
		    this.categoryId = categoryId;
		  }

		  public int getCategoryId() {
		    return this.categoryId;
		  }
		  
	  }
	
	

}
