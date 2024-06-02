package Constants;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Constants {
    public final static ImageIcon startButton[] = {new ImageIcon("Button Image/Start Button/1.png"),
                                                   new ImageIcon("Button Image/Start Button/2.png"),
                                                   new ImageIcon("Button Image/Start Button/3.png")};
    public final static ImageIcon treeHouseButton[] = {new ImageIcon("Button Image/Tree House Button/1.png"),
                                                       new ImageIcon("Button Image/Tree House Button/2.png"),
                                                       new ImageIcon("Button Image/Tree House Button/3.png")};
    public final static ImageIcon candyKingdomButton[] = {new ImageIcon("Button Image/Candy Kingdom Button/1.png"),
                                                          new ImageIcon("Button Image/Candy Kingdom Button/2.png"),
                                                          new ImageIcon("Button Image/Candy Kingdom Button/3.png")};
    public final static ImageIcon iceKingdomButton[] = {new ImageIcon("Button Image/Ice Kingdom Button/1.png"),
                                                        new ImageIcon("Button Image/Ice Kingdom Button/2.png"),
                                                        new ImageIcon("Button Image/Ice Kingdom Button/3.png")};
    public final static ImageIcon fireKingdomButton[] = {new ImageIcon("Button Image/Fire Kingdom Button/1.png"),
                                                         new ImageIcon("Button Image/Fire Kingdom Button/2.png"),
                                                         new ImageIcon("Button Image/Fire Kingdom Button/3.png")};
    
    
    public final static Image startingPageBackground = new ImageIcon("Background Image/Starting Page Background.jpg").getImage();
    public final static Image TreeHouseBackground = new ImageIcon("Background Image/Tree House Background.jpg").getImage();
    public final static Image FireKingdomBackground = new ImageIcon("Background Image/Fire Kingdom Background.png").getImage();
    public final static Image IceKingdomBackground = new ImageIcon("Background Image/Ice Kingdom Background.png").getImage();
    public final static Image CandyKingdomBackground = new ImageIcon("Background Image/Candy Kingdom Background.png").getImage();
    
    public final static String treeHouse = "Tree House";
    public final static String iceKingdom = "Ice Kingdom";
    public final static String fireKingdom = "Fire Kingdom";
    public final static String candyKingdom = "Candy Kingdom";
}
