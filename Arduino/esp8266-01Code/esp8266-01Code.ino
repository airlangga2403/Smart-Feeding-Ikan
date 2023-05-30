// Mengirim data Serial dari Esp-01 -> Arduino Uno Menggunakan == Serial.begin
// Mengirim data Serial dari Arduino Uno -> Esp-01 Menggunakan == espSerial
// Receive data Menggunakan == espSerial
// Serial Coms Hanya bisa Char -> Solusi gunakan Code -> Example A Untuk 6


#include <ESP8266Firebase.h>
#include <ESP8266WiFi.h>
#include <SoftwareSerial.h>

#define _SSID "vivo"        // Your WiFi SSID
#define _PASSWORD "bangwifibang"    // Your WiFi Password
#define PROJECT_ID "smartfeeding-proyek-akhir"   // Your Firebase Project ID. Can be found in project settings.

SoftwareSerial espSerial(3, 4); // RX, TX
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

  // Mengirim data yang diterima ke Serial Monitor
  Serial.print("1"); // Mengirim data melalui koneksi serial
  delay(2000); // Jeda 1 detik
  Serial.print("0"); // Mengirim data melalui koneksi serial
  delay(1000); // Jeda 1 detik

  if (espSerial.available()) {
    char data = espSerial.read(); // Membaca data yang diterima dari Arduino

    // Mengendalikan LED berdasarkan data yang diterima
    if (data == '1') {
      digitalWrite(LED_BUILTIN, LOW);   // Turn the LED on (Note that LOW is the voltage level
    } else if (data == '0') {
      digitalWrite(LED_BUILTIN, HIGH);  // Turn the LED off by making the voltage HIGH
    }
  }

  // getting if 3 == Open, 2 == Close.
  int servoOpen = firebase.getInt("sensor/servo/open");
  Serial.print(servoOpen);



}
