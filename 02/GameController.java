import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.*;

// YOUR OTHER IMPORTS HERE IF NEEDED

/**
 * The class <b>GameController</b> is the controller of the game. It is a listener
 * of the view, and has a method <b>play</b> which computes the next
 * step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener, ItemListener {

    private GameModel model;
    private GameView view;
    private int width,height;

    /**
     * Constructor used for initializing the controller. It creates the game's view
     * and the game's model instances
     *
     * @param width
     *            the width of the board on which the game will be played
     * @param height
     *            the height of the board on which the game will be played
     */
    public GameController(int width, int height) {

        this.width=width;
        this.height=height;
        model = new GameModel(width,height);
        view = new GameView(model,this);
    }


    /**
     * Callback used when the user clicks a button (reset,
     * random or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {
        if(e.getSource().getClass().getName().equals("GridButton")){
            GridButton source = (GridButton)e.getSource();
            model.click(source.getRow(),source.getColumn());
            if (view.solutionShown()){
                model.setSolution();
            }
        }else if(e.getSource().getClass().getName().equals("javax.swing.JButton")){
            JButton source = (JButton)e.getSource();
            if(source.getActionCommand().equals("Quit")){
                System.exit(0);
            }else if(source.getActionCommand().equals("Reset")){
                model.reset();
                model.setSolution();
            }else if(source.getActionCommand().equals("Random")){
                model.randomize();
                model.setSolution();
                model.resetCount();
            }else if(source.getActionCommand().equals("Play again")){
                model.reset();
                ((JFrame) SwingUtilities.getRoot(source)).dispose();
            }

        }
        view.update();
    }

    /**
     * Callback used when the user select/unselects
     * a checkbox
     *
     * @param e
     *            the ItemEvent
     */

    public void  itemStateChanged(ItemEvent e){
        model.setSolution();
        view.update();

    }


}