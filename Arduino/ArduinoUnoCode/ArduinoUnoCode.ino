

// Mengirim data Serial dari Esp-01 -> Arduino Uno Menggunakan == espSerial.begin
// Mengirim data Serial dari Arduino Uno -> Esp-01 Menggunakan == espSerial
// Receive data Menggunakan == espSerial

// gunakan detach avoid getar

// Esp Serial Test Communication
#include <SoftwareSerial.h>
#include <Servo.h>


SoftwareSerial espSerial(4, 5);  // RX, TX
int ledPin = 9;
Servo servo;  // membuat objek servo
// ULTRASONIC
const int pingPin = 12;
const int echoPin = 13;

long duration, cm = 0;

void setup() {
  Serial.begin(9600);     // Inisialisasi koneksi serial Arduino
  espSerial.begin(9600);  // Inisialisasi koneksi serial ESP-01

  pinMode(LED_BUILTIN, OUTPUT);  // Inisialisasi pin LED sebagai output
  pinMode(ledPin, OUTPUT);
}

void loop() {

  // put your main code here, to run repeatedly:

  espSerial.println("3");


  while (espSerial.available()) {
    String receivedChar = espSerial.readStringUntil('\n');
    receivedChar.trim();  // Menghilangkan NewLine
    delay(20);            // Reading String from serial give delay


    if (receivedChar.equals("open")) {
      servo.attach(8);
      servo.write(0);
      delay(400);
      servo.write(90);
      delay(400);
      servo.detach();
    }

    // Sending Data Sensor To ESP-01
    sensorJarak();
    String jarakString = String(cm) + "cm";
    espSerial.println(jarakString);
    delay(20);

    Serial.println(receivedChar);
    Serial.println(jarakString);
    espSerial.flush();  // Membersihkan buffer serial sebelum melanjutkan ke langkah berikutnya
  }
}

void sensorJarak() {

  pinMode(pingPin,
          OUTPUT);
  digitalWrite(pingPin,
               LOW);
  delayMicroseconds(2);
  digitalWrite(pingPin,
               HIGH);
  delayMicroseconds(10);
  digitalWrite(pingPin,
               LOW);
  pinMode(echoPin, INPUT);
  duration = pulseIn(echoPin, HIGH);
  cm = microsecondsToCentimeters(duration);
}

long microsecondsToCentimeters(long microseconds) {
  return microseconds / 29 / 2;
}