package com.smoothstack.utopiaSpring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Loan {

	private Long id;
	private String loanName;
	private Float principal, installmentPayments, yrsTerms, interestRate;
	private Boolean isSecured;

	public Loan(Long id, String loanName, Float principal, Float installmentPayments, Float yrsTerms, Boolean isSecured,
			Float interestRate) {
		super();
		this.id = id;
		this.loanName = loanName;
		this.principal = principal;
		this.installmentPayments = installmentPayments;
		this.yrsTerms = yrsTerms;
		this.isSecured = isSecured;
		this.interestRate = interestRate;
	}

}
