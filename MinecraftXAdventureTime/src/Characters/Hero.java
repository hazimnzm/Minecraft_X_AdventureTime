package Characters;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Hero {
    private double hp = 5000;
    private double hpCapacity = 5000;
    public int jumpHeight = 60;
    public double jumpSpeed = 6;
    public double attackSpeed = 8;
    public double speed = 3;
    public String name;
    public BufferedImage walkSprite;
    public BufferedImage[] attackSprite;
    public BufferedImage[][] animation;
    int attackLength;
    int moveLength;
    
    public Hero(){
        
    }
    
    public Hero(int hpCapacity, String name, int attackLength, int moveLength){
        this.hpCapacity = this.hp = hpCapacity;
        this.name = name;
        this.attackLength = attackLength;
        this.moveLength = moveLength;
    }
    
    public void damaged(int damage){
        hp -= damage<hp ? damage : hp;
    }
    
    public void regen(int regen){
        hp = hp+regen<=hpCapacity ? hp + regen : hpCapacity; 
    }
    
    public boolean isAlive(){
        return hp>0;
    }
    
    public double getHp(){
        return hp;
    }
    
    public double getHpCapacity(){
        return hpCapacity;
    }
    
    private void importSprite() throws IOException{
        InputStream walk = getClass().getResourceAsStream("/Animation Sprite/" + name + " Animation Sprite.png");
        InputStream[] attack = new InputStream[4];
        attackSprite = new BufferedImage[4];
        for(int i=0 ; i<attackLength ; i++){
            attack[i] = getClass().getResourceAsStream("/Animation Sprite/" + name + " Attack" +(i+1) + " Sprite.png");
        }
        try {
            walkSprite = ImageIO.read(walk);
            for(int i=0 ; i<4 ; i++)
                attackSprite[i] = ImageIO.read(attack[i]);
        } catch (IOException ex) {
            throw new IOException("Image not found");
        }
    }
    
    public void loadAnimation() throws IOException{
        importSprite();
        animation = new BufferedImage[10][6];
        
        for(int i=0 ; i<moveLength ; i++){
            for(int j=0 ;j<2 ; j++){
                animation[j][i] = walkSprite.getSubimage(i*32, j*32, 32, 32);
            }
        }
        for(int k=0 ; k<attackLength ; k++){
            for(int i=0 ; i<2 ; i++){
                for(int j=0 ; j<attackLength ; j++){
                    animation[(2*(k+1))+i][j] = attackSprite[k].getSubimage(j*94, i*94, 92,94);
                }
            }
        }
    }
}
