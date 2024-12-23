# eBDSMS SDK Integration for Android
This document provides instructions for integrating the eBDSMS Android SDK into your project. 

# Getting Started
> 
How to use ?

To get a Git project into your build:

Step 1. Add the JitPack repository to your build file 

``` build.gradle

dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
 ```
> Step 2. Add the dependency to your `build.gradle`:
``` gradle
dependencies {
	        implementation 'com.github.ebdsms:eBDSMS:2.0.5'
	}
```

# Constants for SMS Send String
Add the following code inside your `Activity`:
``` gradle
     String API_KEY = "YOUR_API_KEY";
     String DEVICE_NUMBER = "DEVICE_NUMBER";
     String NUMBER = "TYPE_YOUR_SENDER_NUMBER";
     String MESSAGE = "TYPE_YOUR_MESSAGE";
```
# Send OTP Process
Note: Use this code send otp:
``` gradle
// If you want to send OTP then use this 2 line code, or skip this 2 line code.
 eBDSMS.OTP otp = new eBDSMS.OTP();
 String otpString = otp.OTPString(6); // Enter the value of the number you want to send OTP like 4,6
 System.out.println(otpString);
```

# SMS Send Process
Note: Use this code when the sms send button is clicked:
If you are not sending OTP then please ignore the OTP code and remove #otpString and do not use +88 before the number as the country code. Start the number like this 017,019,015,018,016.
``` gradle
 eBDSMS ebdsms = new eBDSMS(API_KEY,NUMBER,MESSAGE+" "+otpString, DEVICE_NUMBER, "","",getApplicationContext());
 ebdsms.sendSms();
```

# eBDSMS SDK Integration for Flutter
This document provides instructions for integrating the eBDSMS Flutter project. 

# Getting Started
> 
How to use ?
# yaml
``` gradle
  dependencies:
  flutter:
  sdk: flutter
  http: ^0.14.0  # Make sure to use the latest version
```
# Implement the eBDSMS class in Flutter:
Create eBDSMS Class. 
``` gradle
  import 'dart:math';
import 'package:http/http.dart' as http;

class eBDSMS {
  String apiKey;
  String number;
  String message;
  String device;
  String extra;
  String others;

  eBDSMS({
    required this.apiKey,
    required this.number,
    required this.message,
    required this.device,
    required this.extra,
    required this.others,
  });

  Future<String> sendSms() async {
    try {
      String baseUrl = "https://client.ebdsms.com/services/send.php";
      Uri url = Uri.parse(
          "$baseUrl?key=$apiKey&number=$number&message=$message&devices=$device&type=sms&prioritize=0");

      final response = await http.get(url);
      if (response.statusCode == 200) {
        return response.body;
      } else {
        return 'Error: ${response.statusCode}';
      }
    } catch (e) {
      print(e);
      return 'Error: $e';
    }
  }
}

class OTP {
  final String letters = "0987654321";
  final String numbers = "1234567890";
  late List<String> randomChars;

  OTP() {
    randomChars = (letters + letters.toUpperCase() + numbers).split('');
  }

  String generateOTP(int length) {
    final random = Random();
    return List.generate(length, (_) => randomChars[random.nextInt(randomChars.length)]).join();
  }
}

void main() async {
  String apiKey = "YOUR_API_KEY";
  String deviceNumber = "DEVICE_NUMBER";
  String number = "SEND_NUMBER";
  String message = "MESSAGE";

  // Generate OTP
  OTP otp = OTP();
  String otpString = otp.generateOTP(6);
  print("Generated OTP: $otpString");

  // Create eBDSMS instance with the message + OTP
  eBDSMS sms = eBDSMS(
    apiKey: apiKey,
    number: number,
    message: "$message $otpString",
    device: deviceNumber,
    extra: '',
    others: '',
  );

  // Send SMS
  String response = await sms.sendSms();
  print("SMS Response: $response");
}

```
# How to use:
> 
dart
``` gradle
 void main() {
  EBDSMS sms = EBDSMS(
    apiKey: 'your_api_key',
    number: 'recipient_number',
    message: 'Hello, this is your message!',
    otp: '1234',
    device: 'device_id',
    extra: 'extra_info',
  );

  sms.sendSms();
}

```



# Contact With Us
If you face any problem using this library then feel free to contact me.
To contact me message me on Facebook or email me at:

`Email`: info@ebdsms.com

`Facebook`: <a href="https://www.facebook.com/M220719" rel="nofollow">Shohag Hossain</a> 

# Authors
<a href="https://www.ebdsms.com" rel="nofollow">eBDSMS.com</a>
