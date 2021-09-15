package com.smoothstack.Auth.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginViewModel {
	private String username, password;

	public LoginViewModel(String username, String password) {
		this.username = username;
		this.password = password;
	}

}
