package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.command.Crossover;
import controller.command.GainOrLoss;
import controller.command.ICommand;
import controller.command.MovingAverage;
import model.IModel;

public class ControllerImpl implements IController {
  private Appendable out;
  private Readable in;

  private final Map<String, Function<Scanner, ICommand> commands;

  public ControllerImpl(IModel model, Appendable out, Readable in)throws IllegalArgumentException {
    if ((model == null) || (in == null) || (out == null)) {
      throw new IllegalArgumentException("Sheet, readable or appendable is null");
    }
    this.out = out;
    this.in = in;

    this.commands = new HashMap<>();
    commands.put("gainorloss", sc -> {
      writeMessage("Which stock do you want to analyze? " + System.lineSeparator());
      String ticker= sc.next();
      writeMessage("Please enter a starting date: " + System.lineSeparator());
      Calendar date1 = getCalendar(sc.next());
      writeMessage("Please enter a ending date: " + System.lineSeparator());
      Calendar date2 = getCalendar(sc.next());
      if (date2.before(date1)) {
        throw new IllegalArgumentException("Ending date should not be before starting date.");
      }
      return new GainOrLoss(ticker, date1, date2);
    });
    commands.put("movingaverage", sc -> {
      writeMessage("Which stock do you want to analyze? " + System.lineSeparator());
      String ticker = sc.next();
      writeMessage("Please enter how many days to base the average "
              + "on (x in x-day moving average): " + System.lineSeparator());
      int window = sc.nextInt();
      writeMessage("Please enter the day you want to see the moving average for: " + System.lineSeparator());
      //date as int
      Calendar date1 = getCalendar(sc.next());
      return new MovingAverage(ticker, window, date1);
    });
    commands.put("crossover", sc -> {
      writeMessage("Which stock do you want to analyze? " + System.lineSeparator());
      String ticker = sc.next();
      writeMessage("Please enter how many days to base the averages "
              + "on (x in x-day moving average): " + System.lineSeparator());
      int window = sc.nextInt();
      writeMessage("Please enter the starting date: " + System.lineSeparator());
      Calendar date1 = getCalendar(sc.next());
      writeMessage("Please enter the ending date: " + System.lineSeparator());
      Calendar date2 = getCalendar(sc.next());
      return new Crossover(ticker, window, date1, date2);
    }
  }

  @Override
  public void go(IModel model) {
    Scanner sc = new Scanner(in);
    boolean quit = false;

    this.welcomeMessage();

    while (!quit&&sc.hasNext()) {
//      writeMessage("Please enter an instruction: ");
      String userInstruction = sc.next();
      if (userInstruction.equals("Q") || userInstruction.equals("q")) {
        quit = true;
      }
      else {
        processCommand(userInstruction, sc,model);
      }
    }

    this.endMessage();
   }

   protected void processCommand(String userInstruction, Scanner sc, IModel model)throws IllegalStateException {
     while (sc.hasNext()) {
       ICommand c;
       String in = sc.next();
       if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
         return;
       }
       Function<Scanner, ICommand> cmd = commands.getOrDefault(in, null);
       if (cmd == null) {
         throw new IllegalArgumentException("Invalid command");
       }
       else {
         c = cmd.apply(sc);
         c.run(model);

       }

         try {

           GainOrLoss gainOrLoss = new GainOrLoss(date1, date2, ticker);
           writeMessage("The gain/loss of "+ ticker + " is: "
                   + gainOrLoss.run(model) + System.lineSeparator());
         } catch (IllegalArgumentException e) {
           writeMessage("Error: " + e.getMessage() + System.lineSeparator());
         }
         break;
       case "2":
         try {
           writeMessage("Which stock do you want to analyze? " + System.lineSeparator());
           ticker = sc.next();
           writeMessage("Please enter how many days to base the average "
                   + "on (x in x-day moving average): " + System.lineSeparator());
           window = sc.nextInt();
           writeMessage("Please enter the day you want to see the moving average for: " + System.lineSeparator());
           //date as int
           date1 = getCalendar(sc.next());
           MovingAverage movingAverage = new MovingAverage(window, date1, ticker);
           writeMessage("The moving average of "+ ticker + " on "
                   + date1 + " is: " + movingAverage.run(model) + System.lineSeparator());
         } catch (IllegalArgumentException e) {
           writeMessage("Error: " + e.getMessage() + System.lineSeparator());
         }
         break;
       case "3":
         try {
           writeMessage("Which stock do you want to analyze? " + System.lineSeparator());
           ticker = sc.next();
           writeMessage("Please enter how many days to base the averages "
                   + "on (x in x-day moving average): " + System.lineSeparator());
           window = sc.nextInt();
           writeMessage("Please enter the starting date: " + System.lineSeparator());
           date1 = getCalendar(sc.next());
           writeMessage("Please enter the ending date: " + System.lineSeparator());
           date2 = getCalendar(sc.next());
           Crossover crossover = new Crossover(window, date1, date2, ticker);
           writeMessage("From "
                   + date1
                   + " to "
                   + date2
                   + ", the "
                   + window
                   + "-day crossovers for "
                   + ticker
                   + " were on the following dates: ");
           //temporarily an arraylist of integers for the dates
           ArrayList<Calendar> result = crossover.run(model);
           for (int i=0; i<result.size();i++) {
             if (i == result.size()-1) {
               writeMessage(result.get(i).getTime() + System.lineSeparator());
             }
             else {
               writeMessage(result.get(i).getTime() + ", ");
             }
           }
         } catch (IllegalArgumentException e) {
           writeMessage("Error: " + e.getMessage() + System.lineSeparator());
         }
         break;
       case "M":
       case "m":
         printMenu();
         break;
       default:
         writeMessage("Undefined instruction: "+userInstruction+System.lineSeparator());
     }
   }

  protected void writeMessage(String message) throws IllegalStateException {
    try {
      out.append(message);

    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  protected void welcomeMessage() throws IllegalStateException{
    writeMessage("Welcome to Stock Simulation!"+System.lineSeparator());
    printMenu();
  }

  protected void printMenu() {
    writeMessage("Please enter the character in the parentheses. Supported user instructions are: " + System.lineSeparator());
    writeMessage("(1) Find out stock gain or lose in a time period."
            + System.lineSeparator());
    writeMessage("(2) Find out x-day moving average on a given day."
            + System.lineSeparator());
    writeMessage("(3) Find out which days are x-day crossovers in a time period."
            + System.lineSeparator());
    writeMessage("(M)enu (show the menu again)" + System.lineSeparator());
    writeMessage("(Q)uit (closes the program)" + System.lineSeparator());
  }

  protected void endMessage() {
    writeMessage("Thank you for using this program!");
  }

  protected Calendar getCalendar(String input)throws IllegalArgumentException {
    String[] result = input.split("-");
    if(result.length>3) {
      throw new IllegalArgumentException("Error: Date input has too many arguments.");
    }
    Calendar cal = Calendar.getInstance();
    cal.set(Integer.parseInt(result[2]), Integer.parseInt(result[0]), Integer.parseInt(result[1]));
    return cal;
  }
}
