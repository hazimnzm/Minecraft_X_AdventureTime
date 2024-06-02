package Item;

import Item.Items;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import java.util.LinkedHashMap;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class AdventurerDiary extends Items{
    public LinkedHashMap<String, DiaryEntry> entries;
    private String previousHash;
    public boolean isExpanded = false;
    public int offset = 0;
    public AdventurerDiary(){
        entries = new LinkedHashMap<>();
        previousHash = null;
        icon = new ImageIcon("Item Icon/AdventurerDiary.png");
        name = "The Adventurer Diary";
    }
    
    
    
    private String generateHash(String data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b: hash){
            hexString.append(String.format("%02x",b));
        }
        return hexString.toString();
    }
    
    public void addEntry(String eventDescription) throws NoSuchAlgorithmException{
         LocalDateTime timestamp = LocalDateTime.now();
        String entryData = timestamp.toString() + " - " + eventDescription;
        if (previousHash != null) {
            entryData += " - " + previousHash;
        }
        String currentHash = generateHash(entryData);
        DiaryEntry entry = new DiaryEntry(eventDescription, currentHash, previousHash);
        entries.put(timestamp.toString(), entry);
        previousHash = currentHash;
    }
    public boolean verifyEntries() throws NoSuchAlgorithmException {
        String previousHash = null;
        for (Map.Entry<String, DiaryEntry> entry : entries.entrySet()) {
            String entryData = entry.getKey() + " - " + entry.getValue().getDescription();
            if (previousHash != null) {
                entryData += " - " + previousHash;
            }
            if (!generateHash(entryData).equals(entry.getValue().getHash())) {
                return false;
            }
            previousHash = entry.getValue().getHash();
        }
        return true;
    }

    public void displayDiary() {
        for (Map.Entry<String, DiaryEntry> entry : entries.entrySet()) {
            System.out.println("Timestamp: " + entry.getKey());
            System.out.println("Event: " + entry.getValue().getDescription());
            System.out.println();
        }
    }
    
    public void expandDiary(Graphics g){
        g.drawImage(new ImageIcon("Item Image/AdventurerDiaryBook.png").getImage(),1280/2-328/2,720/2-300,null);
        int index = 0;
        int position;
        int scrollBarHeight;
        int scrollBarPosition;
        for (Map.Entry<String, DiaryEntry> entry : entries.entrySet()) {
            g.setFont(new Font("Serif", Font.BOLD, 10));
            position = entries.size()<=11 ? index : index - entries.size() + 11;
            if(position+offset<=10 && position+offset>=0){
                g.setColor(Color.BLACK);
                g.drawString((index+1) + ". Timestamp: " + entry.getKey(), 1280/2-328/2+50, 720/2-300 + 55 + 28*(position + offset));
                g.setColor(Color.BLUE);
                g.drawString("   Event: " + entry.getValue().getDescription(), 1280/2-328/2+50, 720/2-300 + 65 +28*(position + offset));
            }
            index++;
        }
        scrollBarHeight = (int)((double)((11) / (double)entries.size()) * (double)283);
        scrollBarPosition = (int)((((720/2-300 + 50)+283) - scrollBarHeight) - (((double)1/(double)entries.size() * (double)283) * offset));
        if(entries.size()>11){
            g.setColor(Color.DARK_GRAY);
            g.fillRect((1280/2-328/2) + 280,720/2-302 + 50 ,10,287);
            g.setColor(Color.GRAY);
            g.fillRect((1280/2-328/2) + 283,scrollBarPosition,4,scrollBarHeight);
        }
    }
    
    static class DiaryEntry {
        private String description;
        private String hash;
        private String previousHash;

        public DiaryEntry(String description, String hash, String previousHash) {
            this.description = description;
            this.hash = hash;
            this.previousHash = previousHash;
        }

        public String getDescription() {
            return description;
        }

        public String getHash() {
            return hash;
        }

        public String getPreviousHash() {
            return previousHash;
        }
    }
}