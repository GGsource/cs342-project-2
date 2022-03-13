import javafx.scene.control.Button;
/*
ToggleButton - Like a normal button except
With the added feature of a boolean to tell
if it has been selected before or not.
This way we can check if a button has
already been chosen, and unchoose it if the
user changes their mind.
*/
public class ToggleButton extends Button {
    private boolean selectedState = false;
    
    public static String normalColor = "#b0d57b"; //Green for unselected buttons
    public static String highlightedColor = "#92e222"; //Green for selected buttons

    //ToggleButton Constructor
    ToggleButton(String buttonName) {
        this.setText(buttonName);
    }

    //Method sets selected status
    void setSelected(Boolean boolVal){
        selectedState = boolVal;
    }
    //Method returns selected status
    boolean isSelected() {
        return selectedState;
    }
}
