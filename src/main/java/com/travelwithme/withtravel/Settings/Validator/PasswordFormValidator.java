package com.travelwithme.withtravel.Settings.Validator;


import com.travelwithme.withtravel.Settings.Form.Password;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PasswordFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {        return Password.class.isAssignableFrom(clazz);    }

    @Override
    public void validate(Object target, Errors errors) {
        Password password = (Password)target;
        if(!password.getPassword().equals(password.getPasswordCheck())){
            errors.rejectValue("password", "wrong.value", "입력한 새 패스워드가 일치하지 않습니다.");
        }
    }
}
