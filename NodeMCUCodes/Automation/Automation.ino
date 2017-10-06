#include <SPI.h>
#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>
#define FIREBASE_HOST "auto-d6b09.firebaseio.com"
#define FIREBASE_AUTH "RClSp44jfCoqnFyludayuXzbBBwpozXFLuWMP8Ev" 
#define WIFI_SSID "JioPrimeBoi"
#define WIFI_PASSWORD "kkkkkkkk"

#define lRoomLight D0
#define lRoomFan D1
#define bRoomLight D2
#define bRoomFan D3


void setup() {
  Serial.begin(9600);  

  pinMode(lRoomLight, OUTPUT);
  pinMode(bRoomLight, OUTPUT);
  pinMode(lRoomFan, OUTPUT);
  pinMode(bRoomFan, OUTPUT);
  
  while (!Serial);    
  SPI.begin();      
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting.......");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected!: ");
  Serial.println(WiFi.localIP());
  
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
}
void loop() {

  if(Firebase.getString("/home/LivingRoom/light").equals("1")) {
    digitalWrite(lRoomLight,HIGH);
  }
    
  else if(Firebase.getString("/home/LivingRoom/light").equals("0")) {
    digitalWrite(lRoomLight,LOW);
  }
    
  if(Firebase.getString("/home/LivingRoom/fan").equals("1")) {
    digitalWrite(lRoomFan,HIGH);
  }
    
  else if(Firebase.getString("/home/LivingRoom/fan").equals("0")) {
    digitalWrite(lRoomFan,LOW);
  }

  if(Firebase.getString("/home/BedRoom/light").equals("1")) {
    digitalWrite(bRoomLight,HIGH);
  }
    
  else if(Firebase.getString("/home/BedRoom/light").equals("0")) {
    digitalWrite(bRoomLight,LOW);
  }
    
  if(Firebase.getString("/home/BedRoom/fan").equals("1")) {
    digitalWrite(bRoomFan,HIGH);
  }
    
  else if(Firebase.getString("/home/BedRoom/fan").equals("0")) {
    digitalWrite(bRoomFan,LOW);
  }
  if(Firebase.getString("/home/offAll").equals("1")) {
    digitalWrite(lRoomLight,LOW);
    digitalWrite(lRoomFan,LOW);
    digitalWrite(bRoomLight,LOW);
    digitalWrite(bRoomFan,LOW);

    Firebase.setString("/home/BedRoom/light","0");
    Firebase.setString("/home/BedRoom/fan","0");
    Firebase.setString("/home/LivingRoom/light","0");
    Firebase.setString("/home/LivingRoom/fan","0");

    Firebase.setString("/home/offAll","0");
  }
    
}

