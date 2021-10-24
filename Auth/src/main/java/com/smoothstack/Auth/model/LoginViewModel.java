package com.smoothstack.Auth.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginViewModel {
	private String username, password, portal;

	public LoginViewModel(String username, String password, String portal) {
		this.username = username;
		this.password = password;
		this.portal = portal;
	}

}
