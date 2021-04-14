import java.util.*;
import java.util.stream.Collectors;

public class Hangman {
	  private ArrayList<String> dictionary;
	  private ArrayList<Character> guesses = new ArrayList<>();
	  private int letterAmount = 0;
	  private int triesAmount = 0;
	  private String output;
	  private String answer;
	  
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
	  public boolean exitGame() {
		  return this.triesAmount == 0  || !output.contains("_") || dictionary.isEmpty();
	  }
	  
	  public int getTriesAmount() {
		  return this.triesAmount;
	  }
	  
	  public int getAmountOfRemainingWords() {
		  return this.dictionary.size();
	  }
	  
	  public void printCurrentState() {
		  String s = "";
		  for(int i=0;i<this.output.length()-1;i++)
			  s+=output.charAt(i)+",";
		  s+=this.output.charAt(this.output.length()-1);
		  System.out.println(s);
	  }

  public int dictionarySize() {
		  return dictionary.size();
	  }
	  
	  public boolean Winner() {
	        if (!exitGame()) {
	            System.out.println("Game not over yet");
	            return false;
	        } 
	        else {
	            return !output.contains("_");
	        }
	    }
	  
	  
  public boolean checkDoublicate(char guess) {
		  for(Character a : this.guesses)
	        	if(a == guess)
	        		return true;
		  return false;
	  }

public String getAnswer() {
		  if(exitGame())
			  return answer;
			  else
				  return null;
		  
	  }
public String getGuesses() {
		  return this.guesses.toString();
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
	        //se periptosi pou mini ena guess gia ton pekti
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
	  
	  