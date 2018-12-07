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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Jeffrey Spinner
 * Don't dox me pls
 */
public class Buildv1 {
    
    public Buildv1() throws FileNotFoundException {
    }
    
    public ArrayList<String> read(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        ArrayList<String> array = new ArrayList<>();
        
        while (sc.hasNextLine()) {
            
            array.add(sc.nextLine());
        }
        
        return array;
        
    }
    
    /**
     * This method randomly selects an ascendency. 
     * When Scion is selected, two random ascendencies 
     * are also selected. These ascdencencies are
     * checked to make sure that they're from
     * different classes.
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
        int charClass = rand.nextInt(7);
        StringBuilder string = new StringBuilder();
        String result;
        
        if (charClass == 6) {
            int index = rand.nextInt(6);
            int ascend = rand.nextInt(3);
            ArrayList<String> choice = ascendencies.get(index);
            string.append(choice.get(ascend)).append(" + ");
            ascendencies.remove(index);
            index = rand.nextInt(5);
            ascend =  rand.nextInt(3);
            choice = ascendencies.get(index);  
            string.append(choice.get(ascend)).append(" Ascendant");
            result = string.toString();
        } else {
            int ascend = rand.nextInt(3);
            ArrayList<String> choice = ascendencies.get(charClass);  
            result = choice.get(ascend);
        }
        
        return result;
    }
    
    /**
     * This method decides if the build has a keystone passive
     * and then rolls again at a smaller probability.
     * Keystones are read in from a .txt file.
     * The probability of it having n keystones is 1/2^n.
     * 
     * I reused it for unique items. Sue me.
     *
     * @param filename
     * @return A randomly selected assortment of keystones.
     * @throws java.io.FileNotFoundException
     */
    public String keystone(String filename) throws FileNotFoundException {
        
        Random rand = new Random();
        ArrayList<String> string = new ArrayList<>();
        double operand = 2;
        ArrayList<String> array = read(filename);
        
        int c = array.size();               //  for now, 25
        double d = Math.pow(2, c);          //  2 to the power of the number of keystones
        int e = (int) d;
        int roll = rand.nextInt(e);         //  roll max = 2^n, n = number of keystones

        while (roll >= e-(1/(operand)*e)) { //  while rolled number is greater than 1/2, 3/4, 7/8 etc. of maximum value
            operand = operand*2;            //  make the odds of additional keystone smaller and add one
            int selection = rand.nextInt(c);
            string.add(array.get(selection));
            array.remove(selection);
            c--;
        }
        return string.toString();
    }
    /**
     * This method does the same as the above method, but returns a certain number of keystones.
     * I also reused this for uniques. Sue me twice.
     * 
     * @param size
     * @param filename
     * @return A specified amount of randomly generated Keystones
     * @throws FileNotFoundException 
     */
    public String keystone(int size, String filename) throws FileNotFoundException {
        
        Random rand = new Random();
        ArrayList<String> array = read(filename);
        ArrayList<String> string = new ArrayList<>();
        
        while (size > 0) {
            int roll = rand.nextInt(array.size());
            if (size > array.size() || size < 0) {
                return ("Please select a valid number of keystones.");
            }
            string.add(array.get(roll));
            array.remove(roll);
            size--;
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
        ArrayList<String> array = read("Skills.txt");
        String result = array.get(rand.nextInt(array.size()));
        return result;
    } 

}
