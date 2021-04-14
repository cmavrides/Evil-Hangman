
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Alexandros Patsalides / 1028348
 * @author Christodoulos Mavrides / 
 * 
 * @version 3.0
 * 
 * This method represents the object Hangman - a very well known game;
 * It is not a fair game because the computer hides the guessing process in order to play not by
 * the rules but in a way that makes it super hard for the user to win.
 *
 */
public class Hangman {
	  private ArrayList<String> dictionary;
	  private ArrayList<Character> guesses = new ArrayList<>();
	  private int letterAmount = 0;
	  private int triesAmount = 0;
	  private String output;
	  private String answer;
	  
	  /**
	   * The constructor of the class Hangman that initializes the ArrayList Dictionary,
	   * the ArrayList guesses, the letterAmount and the triesAmount;
	   * Initializes the dictionary using words of given length in the desired dictionary;
	   * 
	 * @param amountOfLetters The amount of letters in the desired word; This will be used to filter the words in the given dictionary.
	 * @param amountOfTries The amount of allowed guesses for the user.
	 * @param Dictionary An arrayList representing all the valid words.
	 */
	public Hangman(int amountOfLetters, int amountOfTries, ArrayList<String> Dictionary) {
		  dictionary = new ArrayList<>();
		  this.letterAmount = amountOfLetters;
		  this.triesAmount = amountOfTries;
		
		  String s = "";
		  for(int i=0;i<amountOfLetters;i++)
		 	s+='_';
		 this.output = s;
		  dictionary.addAll(Dictionary.stream().filter(word -> word.length() == this.letterAmount).collect(Collectors.toList()));	  
	  }
	  /**
	   * Checks if the game has ended; This is true if
	   * there are no more guesses left or if the dictionary empties or if the player won in the previous round.
	 * @return
	 */
	public boolean exitGame() {
		  return this.triesAmount == 0  || !output.contains("_") || dictionary.isEmpty();
	  }
	  
	  /**
	 * @return Return the amount of tries (guesses) left.
	 */
	public int getTriesAmount() {
		  return this.triesAmount;
	  }
	  
	  /**
	 * @return Returns the amount of available words in the dictionary
	 */
	public int getAmountOfRemainingWords() { 
		  return this.dictionary.size();
	  }
	  
	  /**
	   * Check if the player won the game.
	 * @return Returns true if the player won; Otherwise it returns false.
	 */
	public boolean Winner() {
		  
	        if (!exitGame()) {
	            System.out.println("The game is not over yet");
	            return false;
	        }
	       
	      return !output.contains("_");
	        
	    }
	  
	  /**
	 * This method prints the current state of the chosen word; In other words it prints the spaces remaining to find the current word.
	 */
	public void printCurrentState() {
		  String s = "";
		  for(int i=0;i<this.output.length()-1;i++)
			  s+=output.charAt(i)+",";
		  s+=this.output.charAt(this.output.length()-1);
		  System.out.println(s);
	  }
	  
	  /**
	 * @return Return the amount of words left in the Dictionary.
	 */
	public int dictionarySize() {
		  return dictionary.size();
	  }
	  
	  
	  /**
	 * @return Returns the guesses of the user using the toString method of the ArrayList
	 */
	public String getGuesses() {
		  return this.guesses.toString();
	  }
	  
	  /**
	   * This method checks the guesses of the user for a duplicated input.
	 * @param guess The current guess of the user
	 * @return Returns true if the guessed character has already been guessed; Otherwise it returns false.
	 */
	public boolean checkDoublicate(char guess) {
		  for(Character a : this.guesses)
	        	if(a == guess)
	        		return true;
		  return false;
	  }
	  
	  /**
	   * This method is invoked only when the game is over; Returns the answer if the game has ended;
	   * Otherwise it returns null.
	 * @return The word "selected" by the program; If the game is not over return null.
	 */
	public String getAnswer() {
		  if(exitGame())
			  return answer;
		  return null;
		  
	  }	 
	  
	  private void addKey(String key, String word, HashMap<String, ArrayList<String>> wordChoiceMap) {
	        if (wordChoiceMap.get(key) == null) {
	            wordChoiceMap.put(key, new ArrayList<>());
	        }
	        wordChoiceMap.get(key).add(word);
	    }
	  public boolean playGame(char guess) {
		  String temp = output;
	        boolean guessRight = false;
	        HashMap<String, ArrayList<String>> wordChoices = new HashMap<>();
	        
	        if (exitGame()) {
	            System.out.println("Game Over");
	            return Winner();
	        }
	        for (String word : dictionary) {
	            char[] key = new char[letterAmount];
	            for (int index = 0; index < letterAmount; index++) {
	                if (word.charAt(index) == guess) {
	                    key[index] = guess;
	                } 
	                else {
	                    key[index] = output.charAt(index);
	                }
	            }
	            String keyString = new String(key);
	            addKey(keyString, word, wordChoices);

	        }
	        
	        if (triesAmount == 1) {
	            if (wordChoices.keySet().contains(output)) {
	                dictionary = new ArrayList<>(wordChoices.get(output));
	                triesAmount--;
	                answer = wordChoices.get(output).get(0);
	                guesses.add(guess);
	                return false;
	            }
	        }
	        for (String key: wordChoices.keySet()) {
	        	if (!wordChoices.keySet().contains(temp)) {
	        		temp=key;
	        	}
	       
	        	if(getUniqueChars(wordChoices.get(key))>getUniqueChars(wordChoices.get(temp))) {
	        		temp=key;
	        	}
	        }
	        if (wordChoices.keySet().contains(temp)) {
	            dictionary = new ArrayList<>(wordChoices.get(temp));
	            guessRight = !temp.equals(output);
	            if (!guessRight) triesAmount--;
	            output = temp;
	            answer = wordChoices.get(output).get(0);
	            guesses.add(guess);
	            return guessRight;
	        } 
	        else {
	            dictionary = new ArrayList<>();
	            guesses.add(guess);
	            return false;
	        }
	  }
	  
	  
	  public void printSums() {
		  System.out.println("guesses: "+ getTriesAmount());
	        System.out.println("words: " + dictionarySize());
	        System.out.println("guessed:" + getGuesses());
	    	printCurrentState(); 
	    	System.out.println();
	  }
	  
	  private int getUniqueChars(ArrayList<String> words) {
	        int counter = 0;
	        if (words == null) return 0;
	        for (String word:words){
	            char[] tempArray = word.toCharArray();
	            HashSet<Character> uniqueChars = new HashSet<>();
	            for (char c : tempArray) {
	                if (!guesses.contains(c)){
	                    uniqueChars.add(c);
	                }
	            }
	            counter += uniqueChars.size();
	        }
	        return counter;
	    }
}