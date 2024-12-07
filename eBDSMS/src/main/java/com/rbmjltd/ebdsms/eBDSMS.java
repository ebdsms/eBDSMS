package com.rbmjltd.ebdsms;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class eBDSMS {
    private String apiKey;
    private String number;
    private String message;
    private String device;
    private String extra;
    private String others;
    private Context context;

    public eBDSMS(String apiKey, String number, String message,
                  String device, String extra, String others, Context context) {
        this.apiKey = apiKey;
        this.number = number;
        this.message = message;
        this.device = device;
        this.extra = extra;
        this.others = others;
        this.context = context;
    }

    public void sendSms() {
        new SendSmsTask().execute();
    }

    private class SendSmsTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            String result = "";
            try {
                String baseUrl = "https://client.ebdsms.com/services/send.php";
                String urlStr = baseUrl + "?key=" + apiKey +
                        "&number=" + number +
                        "&message=" + message +
                        "&devices=" + device +
                        "&type=sms&prioritize=0";

                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    result += inputLine;
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.contains("success") || result.contains("ok") || result.contains("true")) { // Check based on API response
                Toast.makeText(context, "SMS sent successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Failed to send SMS. Please try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static class OTP {
        String LATTER = "0987654321";
        String NUMBER = "1234567890";

        char[] RANDOM = (LATTER + LATTER.toUpperCase() + NUMBER).toCharArray();

        public String OTPString(int length) {
            StringBuilder result = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                result.append(RANDOM[new Random().nextInt(RANDOM.length)]);
            }
            return result.toString();
        }
    }

}

