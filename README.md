# Smart-Feeding-Ikan
Proyek Akhir Smart Feeding Ikan

Develop : 
-> Airlangga
-> Ardhani Ahlan

# Device 
1. Esp8266(01)
2. Arduino Uno
3. BreadBoard
4. HC-SR04
5. Micro Servo SG90

# TinkerCad Schema Wiring

# Apps Schema


# Wiring ESP-01 && Arduino Uno ( Upload Code -> Arduino Uno )
== Wiring == 
- RX -> TX
- TX -> RX
- EN -> VCC (5V)
- VCC -> VCC
- GND -> GND

# Wiring ESP-01 && Arduino Uno ( Upload Code -> Esp-01 )
== Wiring == 
- RX -> RX
- TX -> TX
- EN -> VCC (5V)
- GP100 -> GND
- VCC -> VCC
- GND -> GND
- RST ( Arduino ) -> GND

# Note
-> Mengirim data Serial dari Esp-01 -> Arduino Uno Menggunakan == Serial.begin
-> Mengirim data Serial dari Arduino Uno -> Esp-01 Menggunakan == espSerial
-> Receive data Menggunakan == espSerial

# Jika Error Saat Upload ESP-01 Gunakan FTDI
== Wiring == 
- RX -> TX
- TX -> RX
- EN -> VCC
- GP100 -> GND
- VCC -> VCC
- GND -> GND