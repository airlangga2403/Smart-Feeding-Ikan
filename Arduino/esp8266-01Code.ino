// Mengirim data Serial dari Esp-01 -> Arduino Uno Menggunakan == Serial.begin
// Mengirim data Serial dari Arduino Uno -> Esp-01 Menggunakan == espSerial
// Receive data Menggunakan == espSerial

#include <SoftwareSerial.h>

SoftwareSerial espSerial(3, 4); // RX, TX

void setup() {
  Serial.begin(9600); // Inisialisasi koneksi serial Arduino
  espSerial.begin(9600); // Inisialisasi koneksi serial ESP-01

  pinMode(LED_BUILTIN, OUTPUT); // Inisialisasi pin LED sebagai output
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
}