/*
 * Welcome to the untimate Path of Exile Bravery randomizer.
 * If you actually make a build you see here, you're fucking stupid.
 * Enjoy the memes.
 *
 * I shouldn't need to tell you that if you alter this code, it could
 * do bad things. I'm not sure. It's a pretty lightweight program, so
 * I really don't know how you could fuck it up, but I disavow all
 * responsibility anyways. 
 */
package pathofexilebravery;
import java.util.*;
import java.io.*;

/**
 *
 * @author Jeffrey Spinner
 */
public class Build {
    
    private ArrayList<String> array = new ArrayList<>();
    
    public Build() throws FileNotFoundException {
    }
    
    public void read(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        
        while (sc.hasNext()) {
            array.add(sc.next());
        }
    }
    
    /**
     * This method reads in Ascendencies from a .txt file
     * and randomly selects one for use. When Scion is
     * selected, two random ascendencies are also
     * selected. These ascdencencies are checked to make
     * sure that they're from different classes.
     *
     * @return A randomly selected character class.
     * @throws java.io.FileNotFoundException
     */
    public String charClass() throws FileNotFoundException {
        
        ArrayList<ArrayList> ascendencies = new ArrayList<>();
        ArrayList<String> marauder = new ArrayList<>();
        marauder.add("Berserker");
        marauder.add("Chieftain");
        marauder.add("Juggernaut");
        ascendencies.add(marauder);
        ArrayList<String> templar = new ArrayList<>();
        templar.add("Inquisitor");
        templar.add("Guardian");
        templar.add("Heirophant");
        ascendencies.add(templar);
        ArrayList<String> witch = new ArrayList<>();
        witch.add("Occultist");
        witch.add("Elementalist");
        witch.add("Necromancer");
        ascendencies.add(witch);
        ArrayList<String> shadow = new ArrayList<>();
        shadow.add("Trickster");
        shadow.add("Assassin");
        shadow.add("Saboteur");
        ascendencies.add(shadow);
        ArrayList<String> ranger = new ArrayList<>();
        ranger.add("Deadeye");
        ranger.add("Raider");
        ranger.add("Pathfinder");
        ascendencies.add(ranger);
        ArrayList<String> duelist = new ArrayList<>();
        duelist.add("Slayer");
        duelist.add("Champion");
        duelist.add("Gladiator");
        ascendencies.add(duelist);
        
        Random rand = new Random();
        int charClass = 6;   //rand.nextInt(7);
        
        if (charClass == 6) {
            int index = rand.nextInt(6);
            int ascend = rand.nextInt(3);
            StringBuilder string = new StringBuilder();
            string.append(ascendencies.get(index).get(ascend)).append(" + ");
        }
        
        
        
        String result;
        /*
        if (choice == 18) {
            StringBuilder string = new StringBuilder();
            int index = rand.nextInt(18);
            string.append(array.get(index)).append(" + ");
            
            if (index <= 2) {
                ascendency.remove(0);
                ascendency.remove(0);
                ascendency.remove(0);
            } else if (index <= 5) {
                ascendency.remove(3);
                ascendency.remove(3);
                ascendency.remove(3);
            } else if (index <= 8) {
                ascendency.remove(6);
                ascendency.remove(6);
                ascendency.remove(6);
            } else if (index <= 11) {
                ascendency.remove(9);
                ascendency.remove(9);
                ascendency.remove(9);
            } else if (index <= 14) {
                ascendency.remove(12);
                ascendency.remove(12);
                ascendency.remove(12);
            } else if (index <= 17) {
                ascendency.remove(15);
                ascendency.remove(15);
                ascendency.remove(15);
            }
                  
            index = rand.nextInt(15);
            string.append(ascendency.get(index)).append(" Ascendant");
            result = string.toString();
            
        } else {
            result = ascendency.get(choice);
        }
        */
        return null;
    }
    
    /**
     * This method decides if the build has a keystone passive
     * and then rolls again at a smaller probability.
     * Keystones are read in from a .txt file.
     * The probability of it having n keystones is 1/2^n.
     *
     * @return A randomly selected assortment of keystones.
     * @throws java.io.FileNotFoundException
     */
    public String keystone() throws FileNotFoundException {
        
        Random rand = new Random();
        int roll = rand.nextInt(33554432);
        ArrayList<String> string = new ArrayList<>();
        double operand = 2;
        File file = new File("Keystones.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> keystones = new ArrayList<>();      
        
        while (sc.hasNextLine()) {
            keystones.add(sc.nextLine());
        }
        
        int c = keystones.size();
        double d = Math.pow(2, c);
        
        while (roll >= d-(1/(operand)*d)) {
            operand = operand*2;
            
            int selection = rand.nextInt(c);
            string.add(keystones.get(selection));
            keystones.remove(selection);
            c--;
        }
        return string.toString();
    }
    
    /**
     * This method reads a list of skills from a .txt
     * file and randomly selects one. The .txt file 
     * can be resized and edited at will, as long as
     * the user remembers to keep each entry on a 
     * separate line.
     * 
     * @return A randomly decided main damage skill
     * @throws FileNotFoundException 
     */
    public String skill() throws FileNotFoundException {
        
        Random rand = new Random();
        ArrayList<String> skills = new ArrayList<>();
        File file = new File("Skills.txt");
        Scanner sc = new Scanner(file);
        
        while (sc.hasNextLine()) {
            skills.add(sc.nextLine());
        }
        
        String result = skills.get(rand.nextInt(skills.size()));
        return result;
    } 
    
}
