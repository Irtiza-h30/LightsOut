import javax.swing.*;
import java.awt.*;
// your other import here if needed

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out a matrix of <b>GridButton</b> (the actual game) and
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {

    // your variables here

    private  GameController controller;
    private GameModel model;
    private GridButton[] buttons;

    private JCheckBox check;
    private JButton reset;
    private JButton random;
    private JButton quit;
    private JLabel steps;
    private JFrame win;

    /**
     * Constructor used for initializing the Frame
     *
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel gameModel, GameController gameController) {

        // YOUR CODE HERE

        controller = gameController;
        model = gameModel;
        buttons= new GridButton[model.getHeight() * model.getWidth()];


        JPanel p = new JPanel(new GridLayout(model.getHeight(), model.getWidth()));

        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));


        int currentIndex = model.getHeight() * model.getWidth();
        for (int i = 0; i < currentIndex; i++) {
            int row = i / model.getWidth();
            int col = i % model.getWidth();
            buttons[i] = new GridButton(col,row);
            buttons[i].setContentAreaFilled(false);
            buttons[i].setBorder(null);
            buttons[i].addActionListener(controller);
            p.add(buttons[i]);
        }

        JPanel p3 = new JPanel(new FlowLayout());
        p.setPreferredSize(new Dimension(33*model.getWidth(),33*model.getHeight()));
        p3.add(p);

        JPanel p4 = new JPanel();
        p4.add(p2,"Center");

        reset = new JButton("Reset");
        reset.addActionListener(controller);
        p2.add(reset);

        random = new JButton("Random");
        random.addActionListener(controller);
        p2.add(random);

        check = new JCheckBox("Solution");
        check.setVisible(true);
        check.setBackground(Color.white);
        check.addItemListener(controller);
        p2.add(check);

        quit = new JButton("Quit");
        quit.addActionListener(controller);
        p2.add(quit);

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        main.setBorder(BorderFactory.createEmptyBorder(10,20,0,0));

        steps = new JLabel("Number of steps: "+model.getNumberOfSteps(),SwingConstants.CENTER);

        p.setBackground(Color.white);
        p2.setBackground(Color.white);
        p3.setBackground(Color.white);
        p4.setBackground(Color.white);
        main.setBackground(Color.white);
        main.add(p4, "East");
        main.add(p3, "Center");
        main.add(steps,"South");
        this.setTitle("LightsOut -- the ITI 1121 Version");
        this.getContentPane().add(main,"Center");
        this.getContentPane().setBackground(Color.white);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);



    }

    /**
     * updates the status of the board's GridButton instances based
     * on the current game model, then redraws the view
     */

    public void update(){
        int currentIndex = model.getHeight() * model.getWidth();
        for (int i = 0; i < currentIndex; i++) {
            int row = i / model.getWidth();
            int col = i % model.getWidth();
            if(solutionShown()){
                buttons[i].setState(model.isON(row,col),model.solutionSelects(row,col));
            }else{
                buttons[i].setState(model.isON(row,col),false);
            }
        }
        steps.setText("Number of steps: "+model.getNumberOfSteps());
        if(model.isFinished()){
            winUpdate();

        }
    }


    private void winUpdate(){
        win = new JFrame("Won");

        JPanel winMain = new JPanel(new FlowLayout());
        JPanel buttonPanel = new JPanel (new FlowLayout());

        JLabel firstWinLabel = new JLabel("Congratulations, you won in "+model.getNumberOfSteps()+" steps");
        JLabel secondWinLabel = new JLabel("Would you like to play again?");
        JButton playAgain = new JButton("Play again");
        JButton winQuit = new JButton("Quit");
        winQuit.addActionListener(controller);
        playAgain.setBackground(Color.blue);
        playAgain.addActionListener(controller);

        buttonPanel.add(winQuit);
        buttonPanel.add(playAgain);


        win.setSize(300,130);
        winMain.add(firstWinLabel);
        winMain.add(secondWinLabel);
        winMain.add(buttonPanel);


        win.add(winMain);


        win.setDefaultCloseOperation(EXIT_ON_CLOSE);
        win.setVisible(true);
    }

    /**
     * returns true if the ``solution'' checkbox
     * is checked
     *
     * @return the status of the ``solution'' checkbox
     */

    public boolean solutionShown(){

        return check.isSelected();

    }

}