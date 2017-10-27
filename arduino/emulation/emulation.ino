#include <Keyboard.h>
#include <Mouse.h>
#include <MouseTo.h>

// BEAR
const int SLAVE_ICON_X = 1162;
const int SLAVE_ICON_Y = 519;
const int SLAVE_RECREATION_X = 1499;
const int SLAVE_RECREATION_Y = 676;
const int SLAVE_SELECT_BEAR_X = 1160;
const int SLAVE_SELECT_BEAR_Y = 189;
const int SLAVE_OK_X = 1247;
const int SLAVE_OK_Y = 346;
const int SLAVE_REPEAT_X = 1577;
const int SLAVE_REPEAT_Y = 679;

void setup() {
  Serial.begin(9600);
  Serial.setTimeout(10);
  Keyboard.begin();
  Mouse.begin();
  MouseTo.setCorrectionFactor(1);
}

void pressKey(char key) {
  Keyboard.press(key);
  delay(random(60, 130));
  Keyboard.releaseAll();
}

void bear() {
  pressKey(0xB1);
  moveTo(SLAVE_ICON_X, SLAVE_ICON_Y, true);
  moveTo(SLAVE_RECREATION_X, SLAVE_RECREATION_Y, true);
  moveTo(SLAVE_SELECT_BEAR_X, SLAVE_SELECT_BEAR_Y, true);
  moveTo(SLAVE_OK_X, SLAVE_OK_Y, true);
  moveTo(SLAVE_REPEAT_X, SLAVE_REPEAT_Y, true);
  pressKey(0xB1);
}

void moveTo(int x, int y, char button) {
  MouseTo.setTarget(x, y);
  while (MouseTo.move() == false) {}
  delay(random(1000, 1500));

  Serial.println('Mouse button: ' + button);
  Mouse.press(button);   
  delay(random(120, 150));
  Mouse.release(button);
  delay(1500);
}

void changeRod(String touch){

  pressKey('i'); 
  
  int sx = touch.indexOf('[') + 1;
  int sy = touch.indexOf(',');
    
  int x = touch.substring(sx, sy).toInt();
  int y = touch.substring(sy + 1, touch.length() - 1).toInt();
  
  Serial.println(x);
  Serial.println(y);

  moveTo(x, y, MOUSE_RIGHT);

  pressKey('i');
}

void useSlot(String slot){
	int start = slot.indexOf('[');

  Serial.println(start);
	char key = slot.charAt(start + 1);
  Serial.println(key);
	pressKey(key);
}

void takeLoot(String loot){

  pressKey(0x80); 
  
  int sx = loot.indexOf('[') + 1;
  int sy = loot.indexOf(',');
    
  int x = loot.substring(sx, sy).toInt();
  int y = loot.substring(sy + 1, loot.length() - 1).toInt();
  
  Serial.println(x);
  Serial.println(y);

  moveTo(x, y, MOUSE_RIGHT);

  pressKey(0x80);
}

char getKey(char key) {
  switch  (key) {
    case 'w': return 'w';
    case 's': return 's';
    case 'a': return 'a';
    case 'd': return 'd';
    case 'r': return 'r';
    default : return '@';
  }
}

void loop() {

  String input = Serial.readString();
  int length = input.length();
  if (length != 0) {
    Serial.println(input);
    if (input.startsWith("space")) {
      pressKey(0x20);
    } else if (input.startsWith("Rod")) {
      changeRod(input);
    } else if (input.startsWith("Loot")) {
      takeLoot(input);
    } else if (input.startsWith("Slot")) {
      useSlot(input);
    } else if (input.startsWith("bear")) {
      bear();
    } else {
      for (int i = 0; i < length; i++) {
        delay(random(130, 210));
        char symbol = getKey(input[i]);
        if (symbol != '@') pressKey(symbol);
        
      }

    }

  }
  
}
