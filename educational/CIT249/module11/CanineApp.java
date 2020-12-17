/**************************************************
 * Author: Jared Duncan
 * Date: 4/16/2020
 * School: [redacted]
 * Class: CIT249 Java II
 * Teacher: [redacted]
 * Description: This program uses the supplied arraystack class to create
 * a sort of positional storage and recalling system with the ability to manipulate
 * the stored data to remove any redundant information as well as organize it into
 * a simplified format. This program satisfies the module 11 programming assignment.
 **************************************************/

/**************************************************
 * Import used libraries.
 **************************************************/

import jsjf.ArrayStack;
import java.util.ArrayList;
import java.util.Scanner;

public class CanineApp
{
   public static void main (String[] args) {
      /**************************************************
       * Initialize our stack variable.
       **************************************************/
      ArrayStack<String> stack = new ArrayStack<String>();

      /**************************************************
       * Initialize our scanner variable.
       **************************************************/
      Scanner scanner = new Scanner(System.in);

      /**************************************************
       * Prompt the user for input
       **************************************************/
      String prompt = "Please select a movement choice (1 = North, 2 = South, 3 = East, 4 = West, -1 = Stop):";
      System.out.println(prompt);

      /**************************************************
       * Begin our input loop
       **************************************************/
      while (true) {
         /**************************************************
          * Get the user input and store it.
          **************************************************/
         String input = userInputParser(scanner.nextLine());

         /**************************************************
          * If user input was to stop, break out of the loop and inform the user what is happening.
          **************************************************/
         if (input.equals("stop")) {
            System.out.println("You entered the sentinel value, stopping input loop.");
            break;
         }
         /**************************************************
          * If the user entered something unrecognized, inform the user and ignore the input.
          **************************************************/
         else if (input.equals("invalid")) {
            System.out.println("You entered an invalid choice.\n" + prompt);
         }
         /**************************************************
          * If the input wasn't invalid and wasn't stop, it must be a cardinal direction. Store it.
          **************************************************/
         else {
            stack.push(input);
         }
      }
      /**************************************************
       * Display the original path and backwards path, both simplified and not simplified.
       **************************************************/
      System.out.println("Your original path is: " + getPath(stack,false));
      System.out.println("Your original path simplified is: " + getSimplifiedPath(stack, false));
      System.out.println("Your path back is: " + getPath(stack, true));
      System.out.println("Your path back simplified is: " + getSimplifiedPath(stack, true));
   }

   /**************************************************
    * This function is called in the input loop and converts whatever
    * the user types into either a cardinal direction, "stop", or "invalid" string.
    **************************************************/
   static public String userInputParser(String input) {
      input = input.strip().toLowerCase();
      if (input.equals("1")) {
         return "north";
      } else if (input.equals("2")) {
         return "south";
      } else if (input.equals("3")) {
         return "east";
      } else if (input.equals("4")) {
         return "west";
      } else if (input.equals("-1")) {
         return "stop";
      } else {
         /**************************************************
          * This will happen if the user doesn't enter one
          * of the above valid choices.
          **************************************************/
         return "invalid";
      }
   }

   /**************************************************
    * This method takes a cardinal direction (lower case)
    * and returns the opposite cardinal direction.
    **************************************************/
   static public String invertDirection(String direction) {
      if (direction.equals("north")) {
         return "south";
      } else if (direction.equals("south")) {
         return "north";
      } else if (direction.equals("east")) {
         return "west";
      } else if (direction.equals("west")) {
         return "east";
      }
      return "invalid";
   }

   /**************************************************
    * This method takes a stack and returns a copy of it
    * that isn't referenced to the original stack variable.
    **************************************************/
   static public ArrayStack<String> copyStack(ArrayStack<String> stack) {
      /**************************************************
       * Store the original size of the stack.
       **************************************************/
      int size = stack.size();

      /**************************************************
       * Create a second stack.
       **************************************************/
      ArrayStack<String> copiedStack = new ArrayStack<>();

      /**************************************************
       * Create an ArrayList for storing the data from the original stack.
       **************************************************/
      ArrayList<String> data = new ArrayList<>();

      /**************************************************
       * Put all the data from the stack into the array list.
       * We do this because we can't by default see what resides
       * in the stack based on a specific index, so we choose
       * to take it apart and reassemble it.
       **************************************************/
      for (int i = 0; i < size; i++) {
         data.add(stack.pop());
      }

      /**************************************************
       * Add the data to the original stack and the new
       * stack in the same order it was on the original
       * stack, thereby creating a copy of the original
       * stack.
       **************************************************/
      for (int i = 0; i < size; i++) {
         String datum = data.remove(data.size() - 1);
         stack.push(datum);
         copiedStack.push(datum);
      }

      /**************************************************
       * Return the copied stack.
       **************************************************/
      return copiedStack;
   }

   /**************************************************
    * This method takes a stack and returns the stack
    * with all the data placed inside it backwards.
    * (Whatever is on top is on bottom and vice versa)
    * We use this method because it allows us to output
    * the data in the same order it was inputted.
    **************************************************/
   static public ArrayStack<String> invertStack(ArrayStack<String> stack) {
      /**************************************************
       * Store the original size of the stack.
       **************************************************/
      int size = stack.size();

      /**************************************************
       * Make an arraylist for storing the data elsewhere
       * to empty the stack.
       **************************************************/
      ArrayList<String> data = new ArrayList<>();

      /**************************************************
       * Move all the data from the stack to the arraylist.
       **************************************************/
      for (int i = 0; i < size; i++) {
         data.add(stack.pop());
      }
      /**************************************************
       * Add the data back into the stack, but so that
       * it is reversed from the order it was originally in.
       **************************************************/
      for (int i = 0; i < size; i++) {
         stack.push(data.remove(0));
      }

      /**************************************************
       * Return the reversed/backwards/inverted stack.
       **************************************************/
      return stack;
   }

   /**************************************************
    * This method takes a stack and returns a string containing
    * information about the path data the stack contained.
    * It also has a parameter to reverse the results which will
    * invert any cardinal direction.
    **************************************************/
   static public String getPath(ArrayStack<String> stack, boolean reverse) {
      /**************************************************
       * Copy and invert the stack.
       **************************************************/
      stack = invertStack(copyStack(stack));

      /**************************************************
       * Initialize our string that will contain the path data information.
       **************************************************/
      String path = "";
      /**************************************************
       * Go thru the stack and add the directions to the string as necessary.
       **************************************************/
      while (stack.size() >= 1) {
         /**************************************************
          * Get the direction stored in the stack.
          **************************************************/
         String direction = stack.pop();

         /**************************************************
          * If we're supposed to reverse the direction, do it.
          **************************************************/
         if (reverse) {
            direction = invertDirection(direction);
         }

         /**************************************************
          * Add the direction to the path string.
          **************************************************/
         path += direction;

         /**************************************************
          * Add dashes in between directions only.
          **************************************************/
         if (stack.size() != 0) {
            path += " - ";
         }
      }

      /**************************************************
       * Return our completed path data string.
       **************************************************/
      return path;
   }

   /**************************************************
    * This method takes a stack and returns a string containing
    * information about the path data the stack contained, except
    * it removes any redundant/extra steps from the data.
    * This method also has a reverse parameter that functions the same
    * as the method above this one.
    **************************************************/
   static public String getSimplifiedPath(ArrayStack<String> stack, boolean reverse) {
      /**************************************************
       * Copy and invert the stack.
       **************************************************/
      stack = invertStack(copyStack(stack));

      /**************************************************
       * Initialize our string variable which will contain path data information.
       **************************************************/
      String path = "";

      /**************************************************
       * Initialize our x and y axis counters that will help
       * us summarize the direction taken in the path data.
       **************************************************/
      int x = 0;
      int y = 0;

      /**************************************************
       * Go thru the stack and add to the x and y counters
       * to match the path taken accordingly.
       **************************************************/
      while (stack.size() >= 1) {
         /**************************************************
          * Get the direction stored in the stack.
          **************************************************/
         String direction = stack.pop();
         /**************************************************
          * If we're supposed to reverse the direction, reverse it.
          **************************************************/
         if (reverse) {
            direction = invertDirection(direction);
         }
         /**************************************************
          * Add/subtract to the x/y counters according to the data.
          **************************************************/
         if (direction.equals("north")) {
            y += 1;
         } else if (direction.equals("south")) {
            y -= 1;
         } else if (direction.equals("east")) {
            x += 1;
         } else if (direction.equals("west")) {
            x -= 1;
         }
      }
      /**************************************************
       * Determine how far we traveled on the y axis as
       * well as what direction overall and store itall.
       **************************************************/
      int yAbsolute = y > 0 ? y : -y;
      String yDirection = y > 0 ? "north" : "south";

      /**************************************************
       * Add the "north" or "south"'s to the string.
       **************************************************/
      for (int i = 0; i < yAbsolute; i++) {
         path += yDirection;
         if (i < yAbsolute - 1) {
            path += " - ";
         }
      }

      /**************************************************
       * Determine how far we traveled on the x axis as
       * well as what direction overall and store it all.
       **************************************************/
      int xAbsolute = x > 0 ? x : -x;
      String xDirection = x > 0 ? "east" : "west";

      /**************************************************
       * Add the "east" or "west"'s to the string.
       * Also add a dash if there was any steps taken previously.
       **************************************************/
      for (int i = 0; i < xAbsolute; i++) {
         if (path != "" && i == 0) {
            path += " - ";
         }
         path += xDirection;
         if (i < xAbsolute - 1) {
            path += " - ";
         }
      }

      /**************************************************
       * Return our completed simplified path string.
       **************************************************/
      return path;
   }
}
