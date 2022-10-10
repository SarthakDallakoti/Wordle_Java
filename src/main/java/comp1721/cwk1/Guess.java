/*******************************************
This Program Demonstrates a Guess class
********************************************
*/

//Class and Package used in the program
package comp1721.cwk1;
import java.util.Scanner;

// Guess Class declaration
public class Guess 
{
  private static final Scanner INPUT = new Scanner(System.in);
  private int guessNumber; 
  private String chosenWord; 

  /****************************************************************************************
  public Guess is a constructor with only one parameter which initializes the guessNumber.
  If the guessNumber is out of range then it throws a GameException
  ******************************************************************************************
  */

  public Guess(int num)
  {
    // Initializing guessNumber
    guessNumber = num;
    if (guessNumber < 1 || guessNumber > 6)
    {
      //Throwing exception if it is invalid
      throw new GameException("Invalid!");
    }
  }

  /*************************************************************************************
  public Guess is another constructor of a Guess Class 
  This constructor initializes the guessNumber and another initializes the chosenWord.
  GameException is thrown if guessNumber or ChosenWord is invalid.
  *************************************************************************************
  */

  public Guess(int num, String word)
  {
    // Initializing guessNumber and chosenword
    guessNumber = num;
    chosenWord = word;
    if (guessNumber < 1 || guessNumber > 6)
    {
      //Throwing exception if it guessNumber not valid
      throw new GameException("Invalid!");
    }
    else if (word.length() == 5)
    {
      //Converting the string to uppercase
      chosenWord = word.toUpperCase();
      
      for (int i = 0; i<chosenWord.length(); i++)
      {
        //Checks it the word is letter or not
        if (Character.isLetter(chosenWord.charAt(i)))
        {
          continue;
        }
        else
        {
          //Throws exception if the word is not a letter
          throw new GameException("Invalid!");
        }
      }
    }
    else
    {
      throw new GameException("Invalid!");
    }
  }

  /******************************************************************************************
  public getGuessNumber returns the guessNumber which was initialized from Guess constructor
  *******************************************************************************************
  */

  public int getGuessNumber()
  {
    //returns guessNumber
    return guessNumber;
  }

  /******************************************************************************************
  public getChosenWord returns the ChosenWord which was initialized from Guess constructor
  *******************************************************************************************
  */

  public String getChosenWord()
  {
    //returns chosenWord
    return chosenWord;
  }

  /*******************************************************************************
  public readFromPlayer takes input from the user and initializes the chosenWord
  ********************************************************************************
  */

  public void readFromPlayer()
  {
    //takes input from INPUT which is defined above
    String str = INPUT.nextLine();
    //initializes chosenWord with the input value
    chosenWord = str;
  }

  /*****************************************************************************************
  public compareWith compares the chosenWord and target.
  If the chosenWord and target is same and each character is in the same position 
  then the character is returned in Green Background

  If the chosenWord and target is same but the charcter of the word is in different position 
  then the character is returned in Yellow Background

  If the chosenWord and target is in different position 
  then the character is returned in White Background
  ******************************************************************************************
  */

  public String compareWith(String target)
  {
    //Empty string to print the character out
    String output_string = "";
    //gets character at particular position
    char instance;
    //sets the upper case char to lower
    char tolower;
    //for loop for chosenWord.length()
    for (int i = 0; i< 5; i++)
    {
      //Flag declaration
      boolean flag = false;
      //for loop for target.length()
      for (int j=0; j<target.length(); j++)
      {
        //if char of chosenword and target word is same and is in same position
        if (chosenWord.charAt(i) == target.charAt(j) && i==j)
        {
          output_string = output_string + "\033[30;102m "+chosenWord.charAt(i)+" \033[0m"; //sets char color to green
          flag = true; //sets flag to true
          //creating object for string builder
          StringBuilder sb = new StringBuilder(target);
          //sets the position according to value of j
          int pos = j; 
          //gets character of target at jth position
          char ch = target.charAt(j);
          //sets the character into lower case
          tolower = Character.toLowerCase(ch);
          //changes the char at jth position
          sb.setCharAt(pos, tolower);
          //target will be the new string
          target = sb.toString();
          //breaks out of the loop
          break;
        }
        else if (chosenWord.charAt(i)==target.charAt(j) && i!=j)
        {
          output_string = output_string + "\033[30;103m "+chosenWord.charAt(i)+" \033[0m"; //sets char color to yellow
          flag = true; //sets flag to true
          //creating object for string builder
          StringBuilder sb = new StringBuilder(target);
          //sets the position according to value of j
          int pos = j;
          //gets character of target at jth position
          char ch = target.charAt(j);
          //sets the character into lower case
          tolower = Character.toLowerCase(ch);
          //changes the char at jth position
          sb.setCharAt(pos, tolower);
          //target will be the new string
          target = sb.toString();
          //breaks out of the loop
          break;
        }
      }
      //if flag is false then set the char color to white
      if (flag == false)
      {
        output_string = output_string + "\033[30;107m "+chosenWord.charAt(i)+" \033[0m";
      }
    }
    //returns the value of a after the loop is over
    return output_string;
  }

  /****************************************************************************
  public matches compares chosenWord and target. 
  If chosenWord and target is same then it returns true else it returns false
  *****************************************************************************
  */

  public boolean matches(String target)
  {
    //checks if the target word is same as chosenWord
    if (target.equals(chosenWord))
    {
      return true;
    }
    return false;
  }
}