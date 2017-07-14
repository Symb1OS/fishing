#include <Keyboard.h>
#include <Mouse.h>

const char W = '0';
const char S = '1';
const char A = '2';
const char D = '3';
const char SPACE = '4';
const char R = '9';

void setup() {
  Serial.begin(9600);
  
  Keyboard.begin();
  Mouse.begin();
    
}

char getKey(char key){
      if(W == key) return 'w';
      if(S == key) return 's';
      if(A == key) return 'a';
      if(D == key) return 'd';
      if(R == key) return 'r';
      if(SPACE == key) return (char) 0x20;     
}

void loop() {
  
  String text = Serial.readString();

  int length = text.length();
  if(text.length() != 0 ){
  	for(int i = 0; i < length; i++){
      	delay(random(130, 210));
       	char symbol = text[i];
        char key = getKey(symbol);
      	Keyboard.press(key);
        delay(random(60,130));
        Keyboard.releaseAll();
  	 }
    
  }
    
  }
