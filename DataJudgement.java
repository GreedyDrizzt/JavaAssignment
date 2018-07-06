
import java.lang.annotation.Retention;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
* judge the format of name(first name and given name)  valid or not.
* */
public class DataJudgement {
	public boolean Judge_name(String name) {
		for (int j = 0; j < name.length(); j++) {
			int a = (int) name.charAt(j);
			if (!Character.isAlphabetic(a)) {
				return false;
			}
		}
		return true;
	}
	/**
	* judge the format of mobile valid or not.
	* */
	public boolean Judge_MobileNum(String mobile) {
		if (mobile.length() != 8) {
			return false;

		} else {

			for (int j = 0; j < mobile.length(); j++) {

				if (!Character.isDigit(mobile.charAt(j))) {
					return false;

				}
			}
			return true;
		}
	}
	/**
	* judge the format of pass valid or not.
	* */
	public boolean Judge_Pass(String pass) {
		if (pass.equalsIgnoreCase("Bronze") || pass.equalsIgnoreCase("Gold") || pass.equalsIgnoreCase("Silver")) {

			return true;
		}
		return false;
	}

	/**
	* judge the format of Fee valid or not.
	* */
	public boolean Judge_Fee(String fee) {
		int flag = 0;
		if (fee.charAt(0) != '$') {
			return false;

		} else {
			for (int j = 1; j < fee.length(); j++) {

				if (!Character.isDigit(fee.charAt(j)) && fee.charAt(j) != '.') {
					return false;

				} else if (fee.charAt(j) == '.') {
					flag++;
				}
			}
			if (flag <= 1)
				return true;
			else
				return false;
		}
	}
	/*
	 judge the format of E-mail valid or not.
	 */
	public boolean Judge_Email(String Email) {
		int flag = 0;
		for (int j = 0; j < Email.length(); j++) {

			if (Email.charAt(j) == ' ') {
				return false;

			} else if (Email.charAt(j) == '@') {
				flag++;
			}
		}
		if (flag == 1) {
			return true;
		} else
			return false;
	}
	/**
	* judge the format of birthday valid or not.
	* */
	public boolean Judge_Birthday(String birthday) {
		// set the format to use as a constructor argument
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		if (birthday.trim().length() != dateFormat.toPattern().length())
			return false;

		dateFormat.setLenient(false);

		try {
			// parse the inDate parameter
			dateFormat.parse(birthday.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}
	
	/**
	* standardized format for birthday:dd-MM-yyyy.
	* */
	public String Birthday_format(String birthday) {
		String new_birthday = "";
		birthday = birthday.replace("/", "-");
		if (birthday.length() == 10)
			return birthday;
		else {
			String[] temp = birthday.split("-");
			if (Integer.parseInt(temp[0]) < 10 && temp[0].length() == 1)
				new_birthday = new_birthday + "0" + temp[0] + "-";
			else
				new_birthday += temp[0] + "-";
			if (Integer.parseInt(temp[1]) < 10 && temp[1].length() == 1)
				new_birthday = new_birthday + "0" + temp[1] + "-";
			else
				new_birthday += temp[1] + "-";
			new_birthday += temp[2];
		}
		return new_birthday;

	}
	/**
	* judge the format of Address valid or not.
	* */
	public boolean Judge_Address(String address) {

		String temp = address.replaceAll(",", "");

		String[] aStrings = temp.split(" ");

		for (int i = 0; i < aStrings[aStrings.length - 1].length(); i++) {
			char a = aStrings[aStrings.length - 1].charAt(i);
			if (!Character.isDigit(a)) {
				return false;
			}
		}
		return true;
	}
}