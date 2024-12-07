package ebdsms.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rbmjltd.ebdsms.eBDSMS;


public class MainActivity extends AppCompatActivity {

    String API_KEY = "YOUR_API_KEY";
    String DEVICE_NUMBER = "DEVICE_NUMBER";

    String NUMBER = "TYPE_YOUR_SENDER_NUMBER";
    String MESSAGE = "TYPE_YOUR_MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eBDSMS.OTP otp = new eBDSMS.OTP();
        String otpString = otp.OTPString(6);
        System.out.println(otpString);


        eBDSMS ebdsms = new eBDSMS(API_KEY,NUMBER,MESSAGE+" "+otpString, DEVICE_NUMBER, null,null,getApplicationContext());
        ebdsms.sendSms();



    }
}