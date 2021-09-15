package com.smoothstack.utopiaSpring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Card {

	private Long id;
	private String cardName;
	private Float annualFee, apr, foodiesPointsPercentages, cashBack, lateFee;

	public Card(Long id, String cardName, Float annualFee, Float apr, Float foodiesPointsPercentages, Float cashBack,
			Float lateFee) {
		super();
		this.id = id;
		this.cardName = cardName;
		this.annualFee = annualFee;
		this.apr = apr;
		this.foodiesPointsPercentages = foodiesPointsPercentages;
		this.cashBack = cashBack;
		this.lateFee = lateFee;
	}

}
