// Mengirim data Serial dari Esp-01 -> Arduino Uno Menggunakan == Serial.begin
// Mengirim data Serial dari Arduino Uno -> Esp-01 Menggunakan == espSerial
// Receive data Menggunakan == espSerial
// Serial Coms Hanya bisa Char -> Solusi gunakan Code -> Example A Untuk 6

// sendString(message.c_str()); // Mengirim string menggunakan fungsi sendString()


#include <ESP8266Firebase.h>
#include <ESP8266WiFi.h>
#include <SoftwareSerial.h>

#define _SSID "vivo"        // Your WiFi SSID
#define _PASSWORD "bangwifibang"    // Your WiFi Password
#define PROJECT_ID "smartfeeding-proyek-akhir"   // Your Firebase Project ID. Can be found in project settings.

SoftwareSerial espSerial(3, 4); // RX, TX
Firebase firebase(PROJECT_ID);    // SLOW BUT HASTLE-FREE METHOD FOR LONG TERM USAGE. DOES NOT REQUIRE PERIODIC UPDATE OF FINGERPRINT
String receivedString = ""; // Variabel untuk menyimpan string yang diterima
boolean stringComplete = false; // Flag untuk menandakan string sudah lengkap


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
  //  Serial.print("1"); // Mengirim data melalui koneksi serial
  //  delay(2000); // Jeda 1 detik
  //  Serial.print("0"); // Mengirim data melalui koneksi serial
  //  delay(1000); // Jeda 1 detik

  String data1 = "1";
  String data2 = "0";
  sendString(data1.c_str()); // Mengirim data melalui koneksi serial
  delay(2000);
  sendString(data2.c_str()); // Mengirim data melalui koneksi serial
  delay(1000); // Jeda 1 detik


  if (espSerial.available()) {
    //    char data = espSerial.read(); // Membaca data yang diterima dari Arduino
    char receivedChar = espSerial.read(); // Membaca data yang diterima dari Esp8266

    processReceivedChar(receivedChar); // Memproses karakter yang diterima

    // Mengendalikan LED berdasarkan data yang diterima
    if (receivedString.equals("1")) {
      digitalWrite(LED_BUILTIN, LOW);   // Turn the LED on (Note that LOW is the voltage level
    } else if (receivedString.equals("0")) {
      digitalWrite(LED_BUILTIN, HIGH);  // Turn the LED off by making the voltage HIGH
    }
  }

  // getting if 3 == Open, 2 == Close.
  int servoOpen = firebase.getInt("sensor/servo/open");
  String servoOpenString = String(servoOpen);
  sendString(servoOpenString.c_str());

  //  sendString(servoOpen);

}
void processReceivedChar(char receivedChar) {
  if (receivedChar == '|') {
    // Memproses string yang telah diterima ketika batasan '|' ditemukan
    // Lakukan operasi apa pun yang perlu Anda lakukan dengan string yang diterima di sini


    // Reset variabel untuk menerima string berikutnya
    receivedString = "";
  } else {
    receivedString += receivedChar; // Menambahkan karakter ke string yang diterima
  }
}

void sendString(const char* str) {
  for (int i = 0; i < strlen(str); i++) {
    Serial.print(str[i]); // Mengirim setiap karakter menggunakan Serial.print()
  }
  Serial.print('|'); // Menambahkan batasan '|' di akhir
}
