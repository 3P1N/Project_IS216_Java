/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan;

/**
 *
 * @author HP
 */
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
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"); // Kiểm tra email
    }
}
