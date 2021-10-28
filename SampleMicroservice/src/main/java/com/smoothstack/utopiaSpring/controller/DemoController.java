package com.smoothstack.utopiaSpring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.utopiaSpring.model.Card;
import com.smoothstack.utopiaSpring.model.CardForm;
import com.smoothstack.utopiaSpring.model.Loan;
import com.smoothstack.utopiaSpring.model.LoanForm;

/*
 * this controller is a mock REST API for testing the front end.
 * it is not intended for production use.
 */

@RestController
@CrossOrigin
@RequestMapping("/utopia")
public class DemoController {

	@GetMapping("/test")
	public ResponseEntity<String> readTest() {
		return new ResponseEntity<>("Hello World!", HttpStatus.OK);
	}

	// send mock card data to front end
	@GetMapping(path = "/cards", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Card>> readCards() {
		List<Card> list = new ArrayList<>();
		list.add(new Card(1L, "Debit", 0f, 0f, 0f, 0f, 0f));
		list.add(new Card(2L, "Basic Credit", 0f, 0.15f, 0f, 0.03f, 29f));
		list.add(new Card(3L, "Platinum Credit", 200f, 0.15f, 0f, 0.08f, 29f));
		list.add(new Card(4L, "Plus Credit", 0f, 0.0499f, 0f, 0.01f, 29f));
		list.add(new Card(5L, "Foodies Credit", 0f, 0.0499f, 0.004f, 0.01f, 29f));
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// send mock loan data to front end
	@GetMapping(path = "/loans", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Loan>> readLoans() {
		List<Loan> list = new ArrayList<>();
		list.add(new Loan(1L, "Mortgage", 500000.00f, 2800.00f, 25.00f, true, 0.004f));
		list.add(new Loan(2L, "Auto Loan", 30000.00f, 420.00f, 5.00f, true, 0.00499f));
		list.add(new Loan(3L, "Student Loan", 50000.00f, 540.00f, 10.00f, false, 0.0035f));
		list.add(new Loan(4L, "Personal Loan", 30000.00f, 500.00f, 4.00f, false, 0.01499f));
		list.add(new Loan(5L, "Payday Loan", 800.00f, 260.00f, 0.5f, false, 0.325f));
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// receive card application form & print to console
	@PostMapping(path = "/cards", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> applyForCard(@RequestBody CardForm newCardForm) {
		System.out.println("Received new card application form:");
		System.out.println(newCardForm.toString());
		delay();
		return new ResponseEntity<>("", HttpStatus.CREATED);
	}

	// receive loan application form & print to console
	@PostMapping(path = "/loans", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> applyForLoan(@RequestBody LoanForm newLoanForm) {
		System.out.println("Received new loan application form:");
		System.out.println(newLoanForm.toString());
		delay();
		return new ResponseEntity<>("", HttpStatus.CREATED);
	}

	// pretend to think for a few seconds while processing the form
	private void delay() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
}
