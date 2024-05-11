package input;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class Input {
    private static ArrayList<KeyCode> keyPressed = new ArrayList<>();

    public static boolean getKeyPressed(KeyCode keyCode){
        return keyPressed.contains(keyCode);
    }

    public static void setKeyPressed(KeyCode keycode,boolean pressed) {
        if(pressed){
            if(!keyPressed.contains(keycode)){
                keyPressed.add(keycode);
            }
        }else{
            keyPressed.remove(keycode);
        }
    }
    public static void setMouseClick(KeyCode keycode,boolean clicked) {
        if(clicked){
            if(!keyPressed.contains(keycode)){
                keyPressed.add(keycode);
            }
        }else{
            keyPressed.remove(keycode);
        }
        System.out.println(keycode);
    }

    public static ArrayList<KeyCode> getKeyPressedList () {
        return keyPressed;
    }
}
