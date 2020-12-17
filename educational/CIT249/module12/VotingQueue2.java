/**************************************************
 * Author: Jared Duncan
 * School: [redacted]
 * Instructor: [redacted]
 * Class: CIT249 - Java II
 * Project: Module 12 Programming Assignment (Part B)
 * Description: This program processes a CSV file into
 * a queue and then generates a nice looking report off
 * of the queued data.
 **************************************************/

/**************************************************
 * Import our imports.
 **************************************************/

import jsjf.CircularArrayQueue;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.io.*;
import java.util.Scanner;

public class VotingQueue2
{

	/**************************************************
	 * Configure the min and max time people spend voting.
	 **************************************************/
	final static private int MIN_VOTE_TIME = 60;
	final static private int MAX_VOTE_TIME = 900;

	public static void main (String[] args) {
		/**************************************************
		Create our queues that hold votes and times.
		 **************************************************/
		CircularArrayQueue<Integer> queue = new CircularArrayQueue<>();
		CircularArrayQueue<Integer> times = new CircularArrayQueue<>();

		/**************************************************
		 * Create our scanner.
		 **************************************************/
		Scanner scanner = new Scanner(System.in);

		boolean loop = true;
		while(loop) {
			/**************************************************
			 * Prompt the user.
			 **************************************************/
			System.out.println("What would you like to do?\n" +
					"1) Add a carload of voters to the queue\n" +
					"2) Process voters from car and document in report\n" +
					"3) Display statistics on screen\n" +
					"4) End program\n" +
					"Please select a choice from above:");
			String choice = scanner.nextLine();

			if ("1".equals(choice)) {
				/**************************************************
				 * Add voters to queues.
				 **************************************************/
				System.out.println("Please enter the amount of voters in the car:");
				int num = Integer.parseInt(scanner.nextLine());
				if (num > 1000) {
					System.out.println("Wow! That number is too high. Try again.");
					continue;
				}
				queue.enqueue(num);
				for (int i = 0; i < num; i++) {
					times.enqueue(getRandomTime());
				}
				System.out.println("Done.");
			} else if ("2".equals(choice)) {
				/**************************************************
				 * Generate a preliminary report.
				 **************************************************/
				CircularArrayQueue<Integer> copy1 = copyQueue(queue);
				CircularArrayQueue<Integer> copy2 = copyQueue(times);
				String report = getVoterReport(copy1, copy2, false);
				saveToFile("report.txt", report);
				System.out.println("Report saved to 'report.txt'.\nDone.");
			} else if ("3".equals(choice)) {
				/**************************************************
				 * Show preliminary statistics.
				 **************************************************/
				CircularArrayQueue<Integer> copy1 = copyQueue(queue);
				CircularArrayQueue<Integer> copy2 = copyQueue(times);
				String[] stats = getVoterReport(copy1, copy2, true).split(",");
				String TOTAL_VOTE_COUNT = stats[0];
				String TOTAL_VOTE_TIME = stats[1];
				String TOTAL_VOTE_AVERAGE = stats[2];
				System.out.println("Number of voters: " + TOTAL_VOTE_COUNT + "." +
						"\nTotal length of voting (seconds): " + TOTAL_VOTE_TIME + "." +
						"\nAverage length of vote (seconds): " + TOTAL_VOTE_AVERAGE + ".\nDone.");
			} else if ("4".equals(choice)) {
				/**************************************************
				 * Generate final reports and statistics..
				 **************************************************/
				String report = getVoterReport(queue, times, false);
				saveToFile("report.txt", report);
				System.out.println(report + "\nGoodbye!");
				System.exit(0);
			} else {
				System.out.println("You entered something wrong, please try again.");
			}
			System.out.println(' ');
		}
	}

	/**************************************************
	 * This method takes a queue and returns a copy of it
	 * that isn't referenced to the original queue variable.
	 **************************************************/
	static public CircularArrayQueue<Integer> copyQueue(CircularArrayQueue<Integer> queue) {
		/**************************************************
		 * Store the original size of the queue.
		 **************************************************/
		int size = queue.size();

		/**************************************************
		 * Create a second queue.
		 **************************************************/
		CircularArrayQueue<Integer> copiedQueue = new CircularArrayQueue<>();

		/**************************************************
		 * One by one, read and clone each element of the
		 * original queue back into itself and the copy queue.
		 **************************************************/
		for (int i = 0; i < size; i++) {
			int value = queue.dequeue();
			queue.enqueue(value);
			copiedQueue.enqueue(value);
		}

		/**************************************************
		 * Return the copied queue.
		 **************************************************/
		return copiedQueue;
	}

	/**************************************************
	 * This function takes a string and writes it to a specified file.
	 **************************************************/
	static public void saveToFile(String fileName, String data) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(data);
			writer.close();
		} catch (IOException ignored) {}
	}

	/**************************************************
	 * This function takes a string and adds a character
	 * n-times to pad the string to a certain length. You
	 * can also specify which side of the string to put the
	 * padding on as well as choose the padding character.
	 **************************************************/
	static public String padString(String string, int length, char character, boolean append) {
		String padding = "";
		for (int i = 0; i < length; i++) {
			padding += character;
		}
		if (append) {
			string += padding;
			string = string.substring(0,length);
		} else {
			string = padding + string;
			string = string.substring(string.length()-length);
		}
		return string;
	}

	/**************************************************
	 * This function simply returns a string containing
	 * a date and time.
	 **************************************************/
	static public String getTimeStamp() {
		Date date = new Date();
		long time = date.getTime();
		Timestamp stamp = new Timestamp(time);
		return ""+stamp;
	}

	/**************************************************
	 * This function simply returns a random number
	 * within the bounds required by the assignment.
	 **************************************************/
	static public int getRandomTime() {
		return new Random().nextInt((MAX_VOTE_TIME - MIN_VOTE_TIME) + 1) + MIN_VOTE_TIME;
	}

	/**************************************************
	 * This function takes the above functions queue
	 * and churns out a report based on the data
	 * contained in the queue. A string is returned
	 * containing the entire report.
	 **************************************************/
	static public String getVoterReport(CircularArrayQueue<Integer> voterQueue, CircularArrayQueue<Integer> timesQueue, boolean statsOnly) {
		/**************************************************
		 * Create the preheading line which shows the time
		 * the report was generated and add it to the report.
		 **************************************************/
		String startTime = getTimeStamp();
		String result = "";
		String preheading = "VOTER REPORT\nSTARTED GENERATING AT " + startTime + ".\n";
		result += preheading;

		/**************************************************
		 * Create the head of the table and add it to the report.
		 **************************************************/
		String heading_col1 = "ID NUMBER     ";
		String heading_col2 = "TIME (SECONDS)";
		String heading = "| " + padString(heading_col1,heading_col1.length(),' ',true) + " | " +
				padString(heading_col2,heading_col2.length(),' ',true) + " |\n";
		result += heading;

		/**************************************************
		 * Start counting individual votes and how long they take.
		 **************************************************/
		int TOTAL_VOTE_COUNT = 0;
		float TOTAL_VOTE_TIME = 0;

		/**************************************************
		 * Note the initial size of the queue for the loop.
		 **************************************************/
		int size = voterQueue.size();

		/**************************************************
		 * Loop thru the queue.
		 **************************************************/
		for (int r = 0; r < size; r++) {
			int votes = voterQueue.dequeue();
			/**************************************************
			 * Loop for as many votes we just got.
			 **************************************************/
			for (int i = 0; i < votes; i++) {
				/**************************************************
				 * Track vote count and generate a random time (and
				 * track that too..).
				 **************************************************/
				TOTAL_VOTE_COUNT += 1;
				int randomTime = timesQueue.dequeue();
				TOTAL_VOTE_TIME += randomTime;

				/**************************************************
				 * Add our line to the report.
				 **************************************************/
				String line_col1 = "#" + TOTAL_VOTE_COUNT;
				String line_col2 = "" + randomTime;
				String line = "| " + padString(line_col1,heading_col1.length(),' ',true) + " | " +
						padString(line_col2,heading_col2.length(),' ',true) + " |\n";
				result += line;
			}
		}

		/**************************************************
		 * Calculate the average vote time.
		 **************************************************/
		float TOTAL_VOTE_AVERAGE = TOTAL_VOTE_TIME / TOTAL_VOTE_COUNT;

		/**************************************************
		 * Add the statistics to the report.
		 **************************************************/
		String stats = "Number of voters: " + TOTAL_VOTE_COUNT + "." +
				"\nTotal length of voting (seconds): " + TOTAL_VOTE_TIME + "." +
				"\nAverage length of vote (seconds): " + TOTAL_VOTE_AVERAGE + "." +
				"\nReport finished at: " + getTimeStamp() + ".";

		result += stats;

		if (statsOnly) {
			result = TOTAL_VOTE_COUNT + "," + TOTAL_VOTE_TIME + "," + TOTAL_VOTE_AVERAGE;
		}

		/**************************************************
		 * Return our completed report to calling statement.
		 **************************************************/
		return result;
	}
}
