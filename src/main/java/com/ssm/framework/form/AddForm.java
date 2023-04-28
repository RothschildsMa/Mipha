package com.ssm.framework.form;

import java.io.Serializable;
import java.time.LocalDate;

import com.ssm.framework.validator.DateRange;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddForm implements Serializable {
	//社員ID
	private String employeeId;

	//パスワード
	private String password;

	//private String joinDate;

	//入社年月日
	@NotNull(message = "※年月日を入力してください")
	@DateRange(startDate = "2023-01-01", endDate = "2023-12-31")
	private LocalDate joinDate;

	//社員名称漢字
	@NotBlank(message = "※名前漢字を入力してください")
	@Size(max = 20, message = "20文字以内で入力してください。")
	@Pattern(regexp = "^$|^[\u4E00-\u9FFF]+$", message = "漢字のみ入力してください。")
	private String employeeName;

	//社員名称カナ
	@NotBlank(message = "※名前カナを入力してください")
	@Size(max = 60, message = "※60文字以内で入力してください")
	@Pattern(regexp = "^$|^[ァ-ヶー]+$", message = "※カタカナのみ入力してください")
	private String employeeNameKana;

	//性別ID
	@NotBlank(message = "※性別を選択してください。")
	private String employeeGenderId;

	//年齢
	@Min(message = "※20～100で入力してください。", value = 20)
	@Max(message = "※20～100で入力してください。", value = 100)
	private int employeeAge;

	//所属ID
	private String employeeDepatmentId;

	//電話番号
	@NotBlank(message = "※電話番号を入力してください")
	@Pattern(regexp = "^0\\d{9,10}$", message = "※正しい電話番号を入力してください。")
	private String employeePhoneNumber;

	//社員メールアドレス
	@NotBlank(message = "※メールアドレスを入力してください")
	@Email(message = "※有効なメールアドレスを入力してください")
	private String employeeMail;

	private String updateDate;

	private String updateUser;

	private String createUser;

}
