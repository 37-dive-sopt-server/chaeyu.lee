package org.sopt.domain.member.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class MemberCreateRequestDto {

    @NotBlank(message = "이름은 필수 입력값이며 공백일 수 없습니다.")
    private final String name;

    @Pattern(regexp = "^\\d{8}$", message = "생년월일은 8자리 숫자로 입력해야 합니다.")
    private final String birth;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "유효하지 않은 이메일 형식입니다.")
    private final String email;

    @NotBlank(message = "성별은 필수입니다.")
    private final String  gender;

    public MemberCreateRequestDto(String name, String birth, String email, String gender) {
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.gender = gender;
    }
}
