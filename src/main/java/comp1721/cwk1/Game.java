/*******************************************
This Program Demonstrates a Game class
********************************************
 */

//Class and Package used in the program
package comp1721.cwk1;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Paths;
import java.time.LocalDate;  
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.io.FileWriter;

// Game Class declaration
public class Game 
{
  private int gameNumber;
  private String target;
  private List<String> objWord = new ArrayList<String>();

  /****************************************************************************************
  This class stores the value in list then the values are extracted by the use of gameNumber
  GameNumber is determined by subtracting the given date by present date.
  If there is no file then it throws IOException
  *****************************************************************************************
   */
  public Game(String filename)throws IOException
  {
    //creating word list to store the words
    List<String> wordList = new ArrayList<String>();
    //reads the file
    Scanner input = new Scanner(Paths.get(filename));
    while (input.hasNextLine())
    {
      String line = input.nextLine();
      //adds the data to wordList
      wordList.add(line);
    }
    //close the file
    input.close();
    // d1 gives the date where 1st wordle game was played
    LocalDate d1 = LocalDate.parse("2020-06-19", DateTimeFormatter.ISO_LOCAL_DATE);
    //d2 gives the present date
    LocalDate d2 = LocalDate.now();
    //diff gives the duration between two dates
    Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
    //converting the dates into days
    long diffDays = diff.toDays();
    //casting into the integer value
    gameNumber = (int) diffDays;
    target = wordList.get(gameNumber);
  }

  /*****************************************************************************************
  This class stores the value in list then the values are extracted by the use of gameNumber
  gameNumber is initialized by integer parameter of the class.
  If there is no file then it throws IOException
  ******************************************************************************************
  */

  public Game(int num,String filename) throws IOException
  {
    //initializing gameNumber
    gameNumber = num;
    //creating word list to store the words
    List<String> wordList = new ArrayList<String>();
    //reads the file
    Scanner input = new Scanner(Paths.get(filename));
    while (input.hasNextLine())
    {
      String line = input.nextLine();
      //adds the data to wordList
      wordList.add(line);
    }
    //close the file
    input.close();
    target = wordList.get(gameNumber);
  }

  /************************************************************
  play() class plays the Wodle game by using all of the classes.
  The classes used in this clas is defined in Guess.java
  *************************************************************
   */
  public void play()
  {
    //gets the first guessnumber
    int gues = 1;
    //number is the object of guess class
    Guess number = new Guess (gues);
    //count is counting the number of times the loop continues
    int count = 0;
    System.out.printf("WORDLE " + gameNumber + "\n");
    for(gues = 1; gues<7; gues++)
    {
      System.out.printf("Enter guess" + "(" +gues +"/6): ");
      //the number object uses . operator to take input
      number.readFromPlayer();
      //to get the chosenWord
      String sget = number.getChosenWord();
      //to capitalize the chosenWord
      String sgetcaps = sget.toUpperCase();
      number = new Guess(gues,sget);
      //compare the input word and target
      String comparewortarget = number.compareWith(target);
      //add the data obtained from comparewith to objWord list
      objWord.add(comparewortarget);
      //prints out the result obtained after comparing
      System.out.println(comparewortarget);
      count = count + 1;
      //if the two string matches then break out of string
      if (sgetcaps.matches(target))
      {
        break;
      }
    }
    //if two string matches in one go
    if (count == 1)
    {
      System.out.println("Superb - Got it in one!");
    }
    //if two string matches in 2-5 try.
    else if (count >=2  && count <= 5)
    {
      System.out.println("Well done!");
    }
    //if the string doesnot match in all 6 try or matches in 6th try.
    else if (count == 6)
    {
      System.out.println("That was a close call!");
    }
    
  }
  /*****************************************************************
  save class writes the output obtained from the play clas in a file
  *******************************************************************
   */
  public void save(String filename) throws IOException
  {
    FileWriter file = new FileWriter(filename);
    //objWord is the list of string
    for (int i =0; i<objWord.size(); i++)
    {
      // writes to file
      file.write(objWord.get(i) + "\n");
    }
    //close the file
    file.close();
  }
}