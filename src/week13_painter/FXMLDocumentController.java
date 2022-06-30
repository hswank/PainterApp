package week13_painter;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class FXMLDocumentController {

    private enum PenSize {
        SMALL(2),
        MEDIUM(4),
        LARGE(6);
        
        private final int radius;
        PenSize(int radius) {this.radius = radius;}
        public int getRadius() {return radius;}
    }
    
    
    @FXML
    private Slider alphaSlider;

    @FXML
    private TextField alphaTextField;

    @FXML
    private Slider blueSlider;

    @FXML
    private TextField blueTextField;

    @FXML
    private Rectangle colorRectangle;

    @FXML
    private Slider greenSlider;

    @FXML
    private TextField greenTextField;

    @FXML
    private Slider redSlider;

    @FXML
    private TextField redTextField;
    
    
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private double alpha = 1.0;
    
    @FXML
    private RadioButton blackRadioButton;

    @FXML
    private RadioButton blueRadioButton;

    @FXML
    private Button clearButton;

    @FXML
    private ToggleGroup colorToggleGroup;

    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton greenRadioButton;

    @FXML
    private RadioButton largeRadioButton;

    @FXML
    private RadioButton mediumRadioButton;

    @FXML
    private RadioButton redRadioButton;

    @FXML
    private RadioButton customRadioButton;
    
    @FXML
    private ToggleGroup sizeToggleGroup;

    @FXML
    private RadioButton smallRadioButton;

    @FXML
    private Button undoButton;
    
    
    private PenSize radius = PenSize.MEDIUM;
    private Paint brushColor = Color.BLACK;
    
    public void initialize() {
        blackRadioButton.setUserData(Color.BLACK);
        redRadioButton.setUserData(Color.RED);
        greenRadioButton.setUserData(Color.GREEN);
        blueRadioButton.setUserData(Color.BLUE);
        customRadioButton.setUserData(Color.rgb(red, green, blue, alpha));
        smallRadioButton.setUserData(PenSize.SMALL);
        mediumRadioButton.setUserData(PenSize.MEDIUM);
        largeRadioButton.setUserData(PenSize.LARGE);
        
        redTextField.textProperty().bind(redSlider.valueProperty().asString("%.0f"));
        greenTextField.textProperty().bind(greenSlider.valueProperty().asString("%.0f"));
        blueTextField.textProperty().bind(blueSlider.valueProperty().asString("%.0f"));
        alphaTextField.textProperty().bind(alphaSlider.valueProperty().asString("%.2f"));
        
        redSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                red = newValue.intValue();
                colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                customRadioButton.setUserData(Color.rgb(red, green, blue, alpha));

            }
        }
        );
        
        greenSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                green = newValue.intValue();
                colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                customRadioButton.setUserData(Color.rgb(red, green, blue, alpha));

            }
        }
        );
        
        blueSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                blue = newValue.intValue();
                colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                customRadioButton.setUserData(Color.rgb(red, green, blue, alpha));
            }
        }
        );
        
        alphaSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                alpha = newValue.doubleValue();
                colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                customRadioButton.setUserData(Color.rgb(red, green, blue, alpha));
            }
        }
        );
    }

    @FXML
    private void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    private void colorRadioButtonSelected(ActionEvent e) {
        brushColor = (Color) colorToggleGroup.getSelectedToggle().getUserData();
    }

    @FXML
    private void drawingAreaMouseDragged(MouseEvent e) {
        Circle newCircle = new Circle(e.getX(), e.getY(), radius.getRadius(), brushColor);
        drawingAreaPane.getChildren().add(newCircle);
    }

    @FXML
    private void sizeRadioButtonSelected(ActionEvent e) {
        radius = (PenSize) sizeToggleGroup.getSelectedToggle().getUserData();
    }

    @FXML
    private void undoButtonPressed(ActionEvent e) {
        int count = drawingAreaPane.getChildren().size();
        
        if (count > 0) {
            drawingAreaPane.getChildren().remove(count - 1);
        }
    }
    
    

}
