import javafx.scene.control.Button;

public class ToggleButton extends Button {
    private boolean pressedVal = false;

    //ToggleButton Constructor
    ToggleButton(String buttonName) {
        this.setText(buttonName);
    }

    void setPressed(Boolean boolVal){
        pressedVal = boolVal;
    }
    boolean isSelected() {
        return pressedVal;
    }
}
