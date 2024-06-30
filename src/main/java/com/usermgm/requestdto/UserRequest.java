package com.usermgm.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

	@NotNull(message = "UserName can not be null")
	@NotBlank(message = "UserName can not be blank")
//	@NotEmpty(message = "UserName can not be null and empty") // space allowed
	private String username;

	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Password must"
			+ " contain at least one letter, one number, one special character")
	@NotBlank(message = "Password can not be blank")
	private String password;

	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email ")
	@NotBlank(message = "Email can not be blank")
	private String email;

	@Min(value = 6000000000l, message = "Invalid phone number")
	@Max(value = 9999999999l, message = "Invalid phone number")
	@NotNull(message = "Contact number can not be null")
	private Long contactNumber;
}
