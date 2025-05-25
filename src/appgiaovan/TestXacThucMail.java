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

    static public void main(String[] args) {
        String generatedCode = String.valueOf(new Random().nextInt(900000) + 100000);
        String mail = "someone@gmail.com";
       new EmailSender().sendMail(mail,generatedCode);
       
    }
}
