
package appgiaovan;


public class CheckFormat {

    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        return phone.matches("^0\\d{9,10}$"); // Hỗ trợ 10 hoặc 11 số
    }

    public static boolean isValidCCCD(String cccd) {
        if (cccd == null) return false;
        return cccd.matches("^\\d{12}$"); // CCCD có đúng 12 chữ số
    }

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zAZ0-9.-]+\\.[a-zA-Z]{2,}$"); // Kiểm tra email
    }

    public static boolean isValidDate(String date) {
        if (date == null) return false;
        return date.matches("^([0-2][0-9]|(3)[0-1])-(0[1-9]|1[0-2])-[0-9]{4}$"); // Kiểm tra ngày định dạng dd-MM-yyyy
    }
}

