package with.travel.withTravel.Member;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class SignUpForm {

    @NotBlank
    @Pattern( regexp = "^[ㄱ-ㅎ가-힣a-z0-9A-Z_-]{3,20}$")
    @Length(min=3, max=20)
    private String nickname;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Length(min=8, max=50)
    private String password;
}
