package com.OtpBasedLogIn.OtpBasedLogIn.controller;

import com.OtpBasedLogIn.OtpBasedLogIn.entity.User2;
import com.OtpBasedLogIn.OtpBasedLogIn.service.EmailSendingService;
import com.OtpBasedLogIn.OtpBasedLogIn.service.EmailVerficationService;
import com.OtpBasedLogIn.OtpBasedLogIn.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmailingOTP {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private EmailSendingService emailSendingService;
    @Autowired
    private EmailVerficationService emailVerficationService;

    @PostMapping("/sendingOtp")
    public Map<String, String> sendOtpEmail(@RequestBody User2 user2) {
        //1.first we save the User2 without sending OTP to the user emailId
        User2 savedUser2 = userServiceImpl.saveUser(user2);

        if (savedUser2 != null) {
            //2.now we gonna send the OTP to the registered User2 EmailId, first we make createOTp() and save Otp into Hashmap.
             emailSendingService.sendOtp(savedUser2.getEmail());
        }
        HashMap<String,String> response=new HashMap<>();
        response.put("status","Successful");
        response.put("message","OTP send to your EmailId ");

        return response;
    }

    //url-> http://localhost:8089/api/verify-Otp?email=abhi2031999@gmail.com&otp=(from email), set request->POST,
    @PostMapping("/verify-Otp")
    public Map<String,String> verifyOtpFromEmail(@RequestParam String email,@RequestParam String otp){

        HashMap<String,String> response=new HashMap<>();
       return emailVerficationService.verifyOtp(email, otp);

    }

    //url->http://localhost:8089/api/Log-In-With-OTP?email=abhi2031999@gmail.com, set request->POST,
    @PostMapping("/Log-In-With-OTP")
    public Map<String,String> logIn(@RequestParam String email){

        return emailSendingService.sendOtpToLogIn(email);
           }

//url->http://localhost:8089/api/verify-Login-OTP?email=abhi2031999@gmail.com&otp=(from email),set request->POST
           @PostMapping("/verify-Login-OTP")
    public Map<String,String> verifyLogIn(@RequestParam String email,@RequestParam String otp){
               return  emailVerficationService.verifyLogInOtp(email,otp);
                   }

}
