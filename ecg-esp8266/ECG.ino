#include <ESP8266WiFi.h>
#include <PubSubClient.h>

/***********   WiFi   *************/
const char* WLAN_SSID = "WIFI_NAME;
const char* WLAN_PASSWORD = "PASSWORD";

/***********   ThinkSpeak   *************/
const unsigned long channel_Id = 1605146;
const char* THINSPEAK_HOST = "api.thingspeak.com";
const char* WRITE_API_KEY = "API_KEY";
const char* VARIABLE_LABEL = "LABEL_NAME";

// mqtt3.thingspeak.com:1883
const char* MQTT_BROKER = "mqtt3.thingspeak.com";

// creadentials
const char* USERNAME_ID = "";
const char* PASSWORD = "";

// clientId
const char* client_Id = "";

//char topic* = "channels/" + channel_Id +  "/publish/" + writeAPIKey;
char topic[150];
char payload[100];

/***********   variables and pins   *************/
int ecg_sensor = A0;
int LO_minus = D6;
int LO_plus = D5;
char str_sensor[10];  // Space to store values to send
float measuredECG;

WiFiClient WiFi_Client;
PubSubClient MQTT_Client(MQTT_BROKER, 1883, WiFi_Client);

void WiFiSetup() {
  delay(10);
  Serial.println();
  Serial.print("Connecting to ");
  Serial.print(WLAN_SSID);
  WiFi.begin(WLAN_SSID, WLAN_PASSWORD);
  // WiFi status control
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println();
  Serial.println("Connected to WiFi: ");
  Serial.print(WLAN_SSID);
  Serial.println();
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
}

void setup() {
  Serial.begin(9600);
  WiFiSetup();
  Serial.println("");
  Serial.println("WiFi Connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
  pinMode(ecg_sensor, OUTPUT);
  pinMode(LO_plus, INPUT);
  pinMode(LO_minus, INPUT);
  sprintf(topic, "channels/%s/publish/fields/field1", channel_Id); // define topic

  MQTT_display_connection_status();
}

void MQTT_display_connection_status() {
  int status = MQTT_Client.state();
  switch(status) {
    case 0:
      Serial.println("MQTT CONNECTED");
      break;
    case 1:
      Serial.println("MQTT CONNECT BAD PROTOCOL");
      break;
    case 2:
      Serial.println("MQTT CONNECT BAD CLIENT ID");
      break;
    case 3:
      Serial.println("MQTT CONNECT UNAVAILABLE");
      break;
    case 4:
      Serial.println("MQTT CONNECT BAD CREDENTIALS");
      break;
    case 5:
      Serial.println("MQTT CONNECT UNAUTHORIZED");
      break;
    case -1:
      Serial.println("MQTT DISCONNECTED");
      break;
    case -2:
      Serial.println("MQTT CONNECT FAILED");
      break;
    case -3:
      Serial.println("MQTT CONNECTION LOST");
      break;
    case -4:
      Serial.println("MQTT CONNECTION TIMEOUT");
      break;
  }
}

void loop() {
  // Check MQTT Broker connection
  if (!MQTT_Client.connected()) {
    reconnect();
  }
  
}

void reconnect() {
  MQTT_Client.setServer(MQTT_BROKER, 1883); 
  MQTT_Client.setClient(WiFi_Client);
  
  // Trying connect with broker.
  while (!MQTT_Client.connected()) {
    Serial.println("Trying to connect with Broker MQTT.");
    // Wait to try to reconnect again...
    MQTT_display_connection_status();
    delay(2000);
  }
  Serial.println("Conected to MQTT.");
}
