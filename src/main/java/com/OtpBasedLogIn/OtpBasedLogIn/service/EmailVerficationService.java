package com.OtpBasedLogIn.OtpBasedLogIn.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailVerficationService {

    public static Map<String,String> sendOtpMapping=new HashMap<>();
    //Here We came after sending OTP to Registered User now we gonna Verify the OTP
        // Assuming sendOtpMapping is a static Map in the same class or accessible
        // Make sure it is declared like this: private static Map<String, String> sendOtpMapping = new HashMap<>();

        public static Map<String, String> verifyOtp(String email, String userEnteredOtp) {
            Map<String, String> response = new HashMap<>();

            if (sendOtpMapping.containsKey(email)) {
                String generatedOtp = sendOtpMapping.get(email);

                if (generatedOtp.equals(userEnteredOtp)) {
                    // OTP is valid, you can proceed with further actions
                    response.put("status", "Otp Verified");
                    response.put("message", "Otp verification successful!");
                    // Optionally, you can remove the entry from the map as the OTP is used
                    sendOtpMapping.remove(email);
                } else {
                    response.put("status", "Otp Verification Failed");
                    response.put("message", "Invalid OTP entered!");
                }
            } else {
                response.put("status", "Otp Verification Failed");
                response.put("message", "No OTP found for the provided email!");
            }

            return response;
        }


    public Map<String,String> verifyLogInOtp(String email, String otp) {

        Map<String, String> response = new HashMap<>();

        if (sendOtpMapping.containsKey(email)) {
            String generatedOtp = sendOtpMapping.get(email);

            if (generatedOtp.equals(otp)) {
                // OTP is valid, you can proceed with further actions
                response.put("status", "Otp Verified");
                response.put("message", "Otp verification successful!");
                // Optionally, you can remove the entry from the map as the OTP is used
                sendOtpMapping.remove(email);
            } else {
                response.put("status", "Otp Verification Failed");
                response.put("message", "Invalid OTP entered!");
            }
        } else {
            response.put("status", "Otp Verification Failed");
            response.put("message", "No OTP found for the provided email!");
        }
        return response;
    }
}



