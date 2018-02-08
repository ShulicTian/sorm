package shulictian.ssm.util;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import shulictian.ssm.po.User;

public class ValidatorReg implements org.springframework.validation.Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return User.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", null, "用户名不能为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passWord", null, "密码不能为空");
		
	}
}

