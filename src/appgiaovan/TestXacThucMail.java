/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan;

import java.io.File;
import java.util.Random;

/**
 *
 * @author HP
 */
public class TestXacThucMail {

    public static void main(String[] args) throws Exception {
       // String generatedCode = String.valueOf(new Random().nextInt(900000) + 100000);
        String mail = "datbac3b1@gmail.com";
        EmailSender.sendMail(mail,new File("E:/JAVA/Project_IS216_Java/exportpdf/hoadon_103.pdf"), "xin chào");
        //new VerificationForm("xin chào").setVisible(true);
    }
}
