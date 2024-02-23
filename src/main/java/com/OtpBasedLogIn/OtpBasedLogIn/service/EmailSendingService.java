package com.OtpBasedLogIn.OtpBasedLogIn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import static com.OtpBasedLogIn.OtpBasedLogIn.service.EmailVerficationService.sendOtpMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class EmailSendingService {

    @Autowired
    private JavaMailSender javaMailSender;

    public Map<String,String> sendOtp(String email){
        //here we get the generated otp, now we have to save this for further verification
             String otp = generateOtp();
        //save to the static imported hashmap
        sendOtpMapping.put(email,otp);

        //send the otp t the email of the user
        sendOtpToEmail(email,otp);

        Map<String,String> response=new HashMap<>();
        response.put("status","User2 got Registered!!");
        response.put("message","Otp send successfully!");
        return response;
    }

    private void sendOtpToEmail(String userEmail, String otp) {
        String subject = "Your OTP for verification";
        String message = "Your OTP is: " + otp;

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        javaMailSender.send(mailMessage);
    }
    private String generateOtp() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

    public Map<String,String> sendOtpToLogIn(String email) {

        String otp = generateOtp();
        //save to the static imported hashmap
        sendOtpMapping.put(email,otp);

        //send the otp t the email of the user
        sendOtpToEmail(email,otp);

        Map<String,String> response=new HashMap<>();
        response.put("status","Check your EmailId for OTP");
        response.put("message","Otp send successfully!");
        return response;
       }
}
