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
6. bluetooth HC-05

# Library
1. Servo
2. SoftwareSerial
3. Firebase
4. "TimeLib.h" ( Jika Tidak Menggunakan Modul RTC )

# TinkerCad
Wiring Schema

3D Design

# App Schema


# Wiring ESP-01 && Arduino Uno && Bluetooth( Using Arduino Uno Board)
Wiring ( Upload Code -> Arduino Uno )
Using Hardware Serial
Esp == Arduino
- RX -> TX (Pin 1)
- TX -> RX (Pin 0)
- EN -> VCC (5V) -> 3.3 Step Down
- VCC -> VCC
- GND -> GND

Wiring ( Upload Code -> Esp-01 )
Esp == Arduino
- RX -> RX
- TX -> TX
- EN -> VCC (5V)
- GP100 -> GND
- VCC -> VCC
- GND -> GND
- RST ( Arduino ) -> GND

Wiring (Upload Code -> Arduino Uno)
Bluetooth == Arduino
- RX -> TX (Pin 5)
- TX -> RX (Pin 4)
- VCC -> VCC
- GND -> GND

# Note
- Mengirim data Serial dari Esp-01 -> Arduino Uno Menggunakan  espSerial ( Software Serial )
- Mengirim data Serial dari Arduino Uno -> Esp-01 Menggunakan  Serial ( Hardware Serial )
- Receive data Arduino Menggunakan  Serial ( Hardware Serial ) || Receive Data Esp Menggunakan  espSerial ( Software Serial )
- ESP set Software Serial (3,1) // RX, TX and Arduino Set Software Serial (4,5) ( Comms With Bluetooth ) // Arduino Comms With Esp Using Hardware Serial (0,1) // RX,TX
- This is because in ESP-01 Module Pin 3 and 1 are considered as RX and TX Pins. Pin 0 and 2 are GPIO Pins.

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