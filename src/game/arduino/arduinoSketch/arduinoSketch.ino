
#include <Wiichuck.h>
#include <Wire.h>

Wiichuck chuck;

void setup(){
  chuck.init(0,0);
  chuck.calibrate();

  Serial.begin(9600);
}

void loop(){
  if(chuck.poll()){

    Serial.write(chuck.buttonC());
    Serial.write(chuck.buttonZ());
    Serial.write(chuck.joyX());
    Serial.write(chuck.joyY());
    
  }
  delay(100);
}
