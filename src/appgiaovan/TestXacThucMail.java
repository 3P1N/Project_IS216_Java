/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan;

import java.util.Random;

/**
 *
 * @author HP
 */
public class TestXacThucMail {

    public static void main(String[] args) {
        String generatedCode = String.valueOf(new Random().nextInt(900000) + 100000);
        String mail = "nhantn248@gmail.com";
        EmailSender.sendEmail(mail, generatedCode);
        new VerificationForm(generatedCode,"").setVisible(true);
    }
}
