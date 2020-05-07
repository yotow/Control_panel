    #include <ESP8266WiFi.h>
#include <WiFiClient.h>
#include <ESP8266WebServer.h>
#include <ESP8266mDNS.h>

#ifndef STASSID
#define STASSID "forlamp"
#define STAPSK  "123456aa"
#endif

MDNSResponder mdns;

const char *ssid = STASSID;
const char *password = STAPSK;

ESP8266WebServer server(80);

const int gpio0_pin = 5;
String webPage = "";
int mode_number = 0;

void incModeNumber(){
  mode_number++;
  if(mode_number == 4){
    mode_number = 0;
  }
  Serial.print("mode is ");
  Serial.println(mode_number);
  
}

void modeUp(int time) {
  incModeNumber();
  digitalWrite(gpio0_pin, HIGH);   // зажигаем светодиод
  delay(time);            // ждем 
  digitalWrite(gpio0_pin, LOW); // выключаем светодиод 
  delay(100);   
  Serial.println("Button Pressed");
  
}

void lightOffButton(){
  switch(mode_number){
    case 1: modeUp(200);
            modeUp(200);
            modeUp(200);
            break;
    case 2: modeUp(200);
            modeUp(200);
            break;
    case 3: modeUp(200);
            break;
    case 0: break;
  }
}

void setup(void) {
  
  webPage += "<h1>ESP8266 Web Server</h1><p>Socket #1<a href=\"socket1On\"><button>ON</button></a>&nbsp;<a href=\"socket1Off\"><button>OFF</button></a></p>";

  pinMode(gpio0_pin, OUTPUT);
  digitalWrite(gpio0_pin, LOW);
  Serial.begin(115200);
  
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);
  
  Serial.println("");
  Serial.print("Connected to ");
  Serial.print(ssid);
  Serial.print(" ");
  // Wait for connection
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());

  if (MDNS.begin("esp8266", WiFi.localIP())) {
    Serial.println("MDNS responder started");
  }
  
  server.on("/", [](){
    server.send(200, "text/html", webPage);
    
  });
  server.on("/socket1On", [](){
    server.send(200, "text/html", webPage);
    modeUp(200);
    delay(200);
  });
  server.on("/socket1Off", [](){
    server.send(200, "text/html", webPage);
    lightOffButton();
    delay(200);
  });
  server.begin();
  Serial.println("HTTP server started");
  modeUp(500);
}

void loop(void) {
 server.handleClient();
 MDNS.update();
}
