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
    public final static ImageIcon encyclopediaButton[] = {new ImageIcon("Button Image/Encyclopedia Button/1.png"),
                                                         new ImageIcon("Button Image/Encyclopedia Button/2.png"),
                                                         new ImageIcon("Button Image/Encyclopedia Button/3.png")};
    public final static ImageIcon upgradeButton[] = {new ImageIcon("Button Image/Upgrade Button/1.png"),
                                                         new ImageIcon("Button Image/Upgrade Button/2.png"),
                                                         new ImageIcon("Button Image/Upgrade Button/3.png")};
    public final static ImageIcon downgradeButton[] = {new ImageIcon("Button Image/Downgrade Button/1.png"),
                                                         new ImageIcon("Button Image/Downgrade Button/2.png"),
                                                         new ImageIcon("Button Image/Downgrade Button/3.png")};
    public final static ImageIcon finnButton[] = {new ImageIcon("Button Image/finn Button/1.png"),
                                                         new ImageIcon("Button Image/finn Button/2.png")};
    public final static ImageIcon jakeButton[] = {new ImageIcon("Button Image/jake Button/1.png"),
                                                         new ImageIcon("Button Image/jake Button/2.png")};
    public final static ImageIcon autoFarmerButton[] = {new ImageIcon("Button Image/Auto Farmer Button/1.png"),
                                                         new ImageIcon("Button Image/Auto Farmer Button/2.png"),
                                                         new ImageIcon("Button Image/Auto Farmer Button/3.png"),
                                                         new ImageIcon("Button Image/Auto Farmer Button/4.png")};
    public final static ImageIcon oneButton[] = {new ImageIcon("Button Image/1Button/1.png"),
                                                 new ImageIcon("Button Image/1Button/2.png")};
    public final static ImageIcon twoButton[] = {new ImageIcon("Button Image/2Button/1.png"),
                                                 new ImageIcon("Button Image/2Button/2.png")};
    
    public final static ImageIcon crops[][] = {{new ImageIcon("Item Image/Crop/01.png"),new ImageIcon("Item Image/Crop/02.png"),new ImageIcon("Item Image/Crop/03.png"),new ImageIcon("Item Image/Crop/04.png"),new ImageIcon("Item Image/Crop/05.png")},
                                               {new ImageIcon("Item Image/Crop/11.png"),new ImageIcon("Item Image/Crop/12.png"),new ImageIcon("Item Image/Crop/13.png"),new ImageIcon("Item Image/Crop/14.png"),new ImageIcon("Item Image/Crop/15.png")}};
    
    public final static Image[] SheepImage = {new ImageIcon("CreatureImage/SheepRight.png").getImage(),
                                         new ImageIcon("CreatureImage/SheepLeft.png").getImage()};
    public final static Image[] IceGolemImage = {new ImageIcon("CreatureImage/IceGolemRight.png").getImage(),
                                         new ImageIcon("CreatureImage/IceGolemLeft.png").getImage()};
    public final static Image[] LavaGolemImage = {new ImageIcon("CreatureImage/LavaGolemRight.png").getImage(),
                                         new ImageIcon("CreatureImage/LavaGolemLeft.png").getImage()};
    public final static Image[] BubbleGumGolemImage = {new ImageIcon("CreatureImage/BubbleGumGolemRight.png").getImage(),
                                         new ImageIcon("CreatureImage/BubbleGumGolemLeft.png").getImage()};
    
    public final static Image startingPageBackground = new ImageIcon("Background Image/Starting Page Background.jpg").getImage();
    public final static Image TreeHouseBackground = new ImageIcon("Background Image/Tree House Background.jpg").getImage();
    public final static Image FireKingdomBackground = new ImageIcon("Background Image/Fire Kingdom Background.png").getImage();
    public final static Image IceKingdomBackground = new ImageIcon("Background Image/Ice Kingdom Background.png").getImage();
    public final static Image CandyKingdomBackground = new ImageIcon("Background Image/Candy Kingdom Background.png").getImage();
    public final static Image LoadingPageBackground = new ImageIcon("Background Image/Loading Page Background.png").getImage();
   
    public final static String treeHouse = "Tree House";
    public final static String iceKingdom = "Ice Kingdom";
    public final static String fireKingdom = "Fire Kingdom";
    public final static String candyKingdom = "Candy Kingdom";
}
