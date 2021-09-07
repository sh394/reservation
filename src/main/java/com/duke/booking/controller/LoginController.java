package com.duke.booking.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.duke.booking.dto.ReservationInfo;
import com.duke.booking.service.ReservationService;

@Controller
@SessionAttributes("reservationEmail")
public class LoginController {
	  private final ReservationService reservationService;
	  
	  public LoginController(ReservationService reservationService) {
		  this.reservationService = reservationService;
	  }
	
	  @GetMapping(path = "/bookingLoginForm")
	  public String bookingLoginForm() {
	    return "bookingLoginForm";
	  }


	  @GetMapping(path = "/bookingLogin")
	  public String bookingLogin(
	      @RequestParam(name = "resrv_email", required = true) String reservationEmail,
	      HttpSession session) {

	    List<ReservationInfo> reservationInfo =
	        reservationService.getReservationInfoByEmail(reservationEmail);
	    if (!reservationInfo.isEmpty()) {
	      session.setAttribute("reservationEmail", reservationEmail);
	    }

	    return "redirect:/myreservation";
	 }
}
