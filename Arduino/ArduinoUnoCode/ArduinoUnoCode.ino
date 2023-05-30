// Mengirim data Serial dari Esp-01 -> Arduino Uno Menggunakan == Serial.begin
// Mengirim data Serial dari Arduino Uno -> Esp-01 Menggunakan == espSerial
// Receive data Menggunakan == espSerial

#include <SoftwareSerial.h>
#include <Servo.h>

SoftwareSerial espSerial(3, 4); // RX, TX
int ledPin = 6;
Servo servo;  // membuat objek servo

void setup() {

  Serial.begin(9600); // Inisialisasi koneksi serial
  espSerial.begin(9600); // Inisialisasi koneksi serial ESP-01

  // initialize digital pin LED_BUILTIN as an output.
  pinMode(ledPin, OUTPUT);
  // initialize Servo .
  servo.attach(8);
}

void loop() {
  espSerial.print("1"); // Mengirim data melalui koneksi serial
  delay(100); // Jeda 1 detik
  //  espSerial.print("0"); // Mengirim data melalui koneksi serial
  //  delay(100); // Jeda 1 detik

  servoBuka();
  delay(400);
  servoTutup();
  delay(400);


  if (espSerial.available()) {
    char data = espSerial.read(); // Membaca data yang diterima dari Esp8266


    // Mengendalikan LED berdasarkan data yang diterima
    if (data == '1') {
      digitalWrite(ledPin, HIGH);   // Turn the LED on (Note that LOW is the voltage level
    } else if (data == '0') {
      digitalWrite(ledPin, LOW);  // Turn the LED off by making the voltage HIGH
    }
  }
}

void servoBuka() {
  servo.write(0);
}
void servoTutup() {
  servo.write(90);
}
