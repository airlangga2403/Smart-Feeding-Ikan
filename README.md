# Smart-Feeding-Fish
Final Project: Smart Fish Feeding

Developed by:
- Airlangga
- Ardhani Ahlan

# Device 
1. Esp8266(01)
2. Arduino Uno
3. BreadBoard
4. HC-SR04
5. Micro Servo SG90

# Library
1. Servo
2. SoftwareSerial
3. Firebase
4. TimeAlarms

# TinkerCad WiringSchema

# App Schema


# Wiring ESP-01 && Arduino Uno ( Upload Code -> Arduino Uno )
Wiring
- RX -> TX
- TX -> RX
- EN -> VCC (5V)
- VCC -> VCC
- GND -> GND

# Wiring ESP-01 && Arduino Uno ( Upload Code -> Esp-01 )
Wiring
- RX -> RX
- TX -> TX
- EN -> VCC (5V)
- GP100 -> GND
- VCC -> VCC
- GND -> GND
- RST ( Arduino ) -> GND

# Note
- Mengirim data Serial dari Esp-01 -> Arduino Uno Menggunakan  Serial.begin
- Mengirim data Serial dari Arduino Uno -> Esp-01 Menggunakan  espSerial
- Receive data Menggunakan  espSerial

English : 
- Sending Serial data from Esp-01 to Arduino Uno using Serial.begin
- Sending Serial data from Arduino Uno to Esp-01 using espSerial
- Receiving data using espSerial

# If Error Occurs When Uploading ESP-01, Use FTDI and Connect to ESP-01
Wiring
- RX -> TX
- TX -> RX
- EN -> VCC
- GP100 -> GND
- VCC -> VCC
- GND -> GND

# Document