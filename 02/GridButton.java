import java.io.File;
import java.awt.*;
import javax.swing.*;

public class GridButton extends JButton {


    ImageIcon[] lights = new ImageIcon[4];
    private int column,row;

    /**
     * Constructor used for initializing a GridButton at a specific
     * Board location.
     *
     * @param column
     *            the column of this Cell
     * @param row
     *            the row of this Cell
     */

    public GridButton(int column, int row) {
        this.column=column;
        this.row=row;
        for(int i=0;i<4;i++){
            lights[i]=new ImageIcon("Icons/Light-"+i+".png");
        }
        setIcon(lights[1]);
    }

    /**
     * sets the icon of the button to reflect the
     * state specified by the parameters
     * @param isOn true if that location is ON
     * @param isClicked true if that location is
     * tapped in the model's current solution
     */
    public void setState(boolean isOn, boolean isClicked) {

        if(isClicked&&isOn){
            setIcon(lights[2]);
        }else if(isClicked){
            setIcon(lights[3]);
        }else if(isOn){
            setIcon(lights[0]);
        }else{
            setIcon(lights[1]);
        }
    }

    /**
     * Getter method for the attribute row.
     *
     * @return the value of the attribute row
     */

    public int getRow() {
        return row;
    }

    /**
     * Getter method for the attribute column.
     *
     * @return the value of the attribute column
     */

    public int getColumn() {
        return column;
    }

}