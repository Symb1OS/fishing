#include <Keyboard.h>
#include <Mouse.h>
#include <MouseTo.h>

const char W = '0';
const char S = '1';
const char A = '2';
const char D = '3';
const char SPACE = '4';
const char R = '9';

// BEAR
const int SLAVE_ICON_X = 1162;
const int SLAVE_ICON_Y = 519;
const int SLAVE_RECREATION_X = 1499;
const int SLAVE_RECREATION_Y = 676;
const int SLAVE_SELECT_BEAR_X = 1160;
const int SLAVE_SELECT_BEAR_Y = 189;
const int SLAVE_OK_X = 1247;
const int SLAVE_OK_Y = 346;
const int SLAVE_REPEAT_X= 1577;
const int SLAVE_REPEAT_Y= 679;

//MINI-GAME
const int MINIGAME_ICON_X = 754;
const int MINIGAME_ICON_Y = 660;
const int MINIGAME_CUBE_X = 1318;
const int MINIGAME_CUBE_Y = 795;

//DINNER 1
const int DINNER_SLOT_1_X = 1312;
const int DINNER_SLOT_1_Y = 1002;

//DINNER 2
const int DINNER_SLOT_2_X = 1361;
const int DINNER_SLOT_2_Y = 1004;

void setup() {
  Serial.begin(9600);
  Keyboard.begin();
  Mouse.begin();
}

void moveTo(int x, int y, boolean leftClick){
    MouseTo.setTarget(x, y);
    while (MouseTo.move() == false) {}
    delay(random(1000, 1500));
    if(leftClick){
      Mouse.press();
      delay(random(120,150));
      Mouse.release();
      delay(1500);
    } 
}

void pressKey(char key){
  Keyboard.press(key);
  delay(random(60,130));
  Keyboard.releaseAll();
}

void bear(){
  pressKey(0xB1);
  moveTo(SLAVE_ICON_X, SLAVE_ICON_Y, true);
  moveTo(SLAVE_RECREATION_X, SLAVE_RECREATION_Y, true);
  moveTo(SLAVE_SELECT_BEAR_X, SLAVE_SELECT_BEAR_Y, true);
  moveTo(SLAVE_OK_X, SLAVE_OK_Y, true);
  moveTo(SLAVE_REPEAT_X, SLAVE_REPEAT_Y, true);
  pressKey(0xB1);
}

void miniGame(){
  pressKey(0xB1);
  moveTo(MINIGAME_ICON_X, MINIGAME_ICON_Y, true);
  moveTo(MINIGAME_CUBE_X, MINIGAME_CUBE_Y, true);
  pressKey(0xB1);
}

void dinner1(){
  pressKey(0xB1);
  moveTo(DINNER_SLOT_1_X, DINNER_SLOT_1_Y, true);
  pressKey(0xB1);
}

void dinner2(){
  pressKey(0xB1);
  moveTo(DINNER_SLOT_2_X, DINNER_SLOT_2_Y, true);
  pressKey(0xB1);
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

  if(text.startsWith("bear")){
      bear();
  } else if(text.startsWith("minigame")){
      miniGame();
  } else if(text.startsWith("dinner1")){
      dinner1();
  } else if(text.startsWith("dinner2")){
      dinner2();
  } else{
      int length = text.length();
      if(text.length() != 0 ){
        for(int i = 0; i < length; i++){
          delay(random(130, 210));
          char symbol = text[i];
          char key = getKey(symbol);
          pressKey(key);
        }
      }
  }
  }
