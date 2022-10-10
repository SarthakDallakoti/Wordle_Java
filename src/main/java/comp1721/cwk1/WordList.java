/*******************************************
This Program Demonstrates a WordList class
********************************************
*/

//Class and Package used in the program
package comp1721.cwk1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.List;

// WordList Class declaration
public class WordList 
{
  //Creats List of String and the value is stored in words.
  private List<String> words = new ArrayList<String>(); 

  /*********************************************************************
  public WordList is a constructor with only one parameter 
  This constructor scans .txt file, reads it and adds the data into list
  This class also throws IOException if .txt file is missing
  ***********************************************************************
  */

  public WordList(String filename) throws IOException
  {
    //read the file
    Scanner input = new Scanner(Paths.get(filename)); 
    while (input.hasNextLine())
    {
      String line = input.nextLine();
      //add the value to word list
      words.add(line); 
    }
    //close the file
    input.close(); 
  }

  /**********************************************************
  public size is a function which returns the size of words 
  ***********************************************************
  */

  public int size()
  {
    // return the size of word list
    return words.size();
  }

  /*****************************************************************
  public getWord is a function which as one parameter (int n)
  This parameter determines which value to return from the list. 
  If the value of parameter is out of range then Exception is thrown
  *******************************************************************
  */

  public String getWord(int n)
  {
    if (n>=0 && n<=(words.size() - 1))
    {
      //return the word from word list
      return words.get(n); 
    }
    else
    {
      throw new GameException("Invalid");
    }
  }
}