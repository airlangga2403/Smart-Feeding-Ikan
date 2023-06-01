// Mengirim data Serial dari Esp-01 -> Arduino Uno Menggunakan == espSerial.begin
// Mengirim data Serial dari Arduino Uno -> Esp-01 Menggunakan == espSerial.begin
// Receive data Menggunakan == espSerial
// Serial Coms Hanya bisa Char -> Solusi gunakan Code -> Example A Untuk 6

// sendString(message.c_str()); // Mengirim string menggunakan fungsi sendString()

// Estimasi Esp Mengirimkan data serial berupa 4 Detik -> Dikarenakan ESP MENGGUNKAN 5V dan menyebabkan ERROR
#include <SoftwareSerial.h>
#include <ESP8266Firebase.h>
#include <ESP8266WiFi.h>
#include <SoftwareSerial.h>

#define _SSID "vivo"        // Your WiFi SSID
#define _PASSWORD "bangwifibang"    // Your WiFi Password
#define PROJECT_ID "smartfeeding-proyek-akhir"   // Your Firebase Project ID. Can be found in project settings.


SoftwareSerial espSerial(3, 1); // RX, TX
Firebase firebase(PROJECT_ID);    // SLOW BUT HASTLE-FREE METHOD FOR LONG TERM USAGE. DOES NOT REQUIRE PERIODIC UPDATE OF FINGERPRINT

void setup() {


  Serial.begin(9600); // Inisialisasi koneksi serial Arduino
  espSerial.begin(9600); // Inisialisasi koneksi serial ESP-01

  pinMode(LED_BUILTIN, OUTPUT); // Inisialisasi pin LED sebagai output
  digitalWrite(LED_BUILTIN, LOW);

  WiFi.mode(WIFI_STA);
  WiFi.disconnect();
  delay(1000);

  WiFi.begin(_SSID, _PASSWORD);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
  }
  digitalWrite(LED_BUILTIN, HIGH);

}

void loop() {

  // put your main code here, to run repeatedly:
  // Jika Serial berhenti sending, matikan while

  while (espSerial.available()) {
    String receivedChar = espSerial.readStringUntil('\n');
    receivedChar.trim(); // Menghilangkan NewLine
    delay(20); // Reading String from serial give delay


    // Updload Ke Firebase Jika ada Data Dari Serial dengan end : "cm"
    if (receivedChar.endsWith("cm")) {
      firebase.setString("sensor/jarak", receivedChar);
    }

    // Getting servo open value and sending to arduino uno
    String servoOpen = firebase.getString("sensor/servo/open");
    servoOpen.trim(); // Menghilangkan NewLine
    if (servoOpen.equals("open")) {
      espSerial.println(servoOpen);
      delay(20);
    }

    espSerial.flush();

  }



}
