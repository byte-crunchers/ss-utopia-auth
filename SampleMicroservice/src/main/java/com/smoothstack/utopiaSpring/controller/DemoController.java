package com.smoothstack.utopiaSpring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.utopiaSpring.model.Card;
import com.smoothstack.utopiaSpring.model.Loan;

@RestController
@CrossOrigin
@RequestMapping("/utopia")
public class DemoController {

	@GetMapping("/test")
	public ResponseEntity<String> readTest() {
		return new ResponseEntity<>("Hello World!", HttpStatus.OK);
	}

	@GetMapping("/cards")
	public ResponseEntity<List<Card>> readCards() {
		List<Card> list = new ArrayList<>();
		list.add(new Card(1L, "Debit", 0f, 0f, 0f, 0f, 0f));
		list.add(new Card(2L, "Basic Credit", 0f, 0.15f, 0f, 0.03f, 29f));
		list.add(new Card(3L, "Platinum Credit", 200f, 0.15f, 0f, 0.08f, 29f));
		list.add(new Card(4L, "Plus Credit", 0f, 0.0499f, 0f, 0.01f, 29f));
		list.add(new Card(5L, "Foodies Credit", 0f, 0.0499f, 0.004f, 0.01f, 29f));
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/loans")
	public ResponseEntity<List<Loan>> readLoans() {
		List<Loan> list = new ArrayList<>();
		list.add(new Loan(1L, "Mortgage", 500000.00f, 2800.00f, 25.00f, true, 0.004f));
		list.add(new Loan(2L, "Auto Loan", 30000.00f, 420.00f, 5.00f, true, 0.00499f));
		list.add(new Loan(3L, "Student Loan", 50000.00f, 540.00f, 10.00f, false, 0.0035f));
		list.add(new Loan(4L, "Personal Loan", 30000.00f, 500.00f, 4.00f, false, 0.01499f));
		list.add(new Loan(5L, "Payday Loan", 800.00f, 2800.00f, 0.03833f, false, 200.00f));
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
