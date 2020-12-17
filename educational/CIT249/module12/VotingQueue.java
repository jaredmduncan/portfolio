/**************************************************
 * Author: Jared Duncan
 * School: [redacted]
 * Instructor: [redacted]
 * Class: CIT249 - Java II
 * Project: Module 12 Programming Assignment (Part A)
 * Description: This program processes a CSV file into
 * a queue and then generates a nice looking report off
 * of the queued data.
 **************************************************/

/**************************************************
 * Import our imports.
 **************************************************/

import jsjf.CircularArrayQueue;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.io.*;

public class VotingQueue
{

	/**************************************************
	 * Configure the min and max time people spend voting.
	 **************************************************/
	final static private int MIN_VOTE_TIME = 60;
	final static private int MAX_VOTE_TIME = 900;

	public static void main (String[] args) {
		/**************************************************
		 * This code steps through the data as required by
		 * the assignment and generates a text report about the
		 * data.
		 **************************************************/
		String report = getVoterReport(getVoterQueue("Voters.csv"));

		/**************************************************
		 * Print the data report to the console.
		 **************************************************/
		System.out.print(report);

		/**************************************************
		 * Save the data report to a file called 'report.txt'.
		 **************************************************/
		saveToFile("report.txt", report);
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
	 * This function reads the CSV file data and puts it
	 * into a queue as required/described in the assignment
	 * documentation.
	 **************************************************/
	static public CircularArrayQueue<Integer> getVoterQueue(String fileName) {
		/**************************************************
		 * Create our queue.
		 **************************************************/
		CircularArrayQueue<Integer> queue = new CircularArrayQueue<Integer>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));

			/**************************************************
			 * Only loop 1000 times in-case file magically is
			 * way too big.
			 **************************************************/
			final int MAX_COUNT = 1000;

			int count = 0;
			while (count < MAX_COUNT) {
				try {
					String rawLineData = reader.readLine();
					if (rawLineData != null) {
						String[] line = rawLineData.split(",");

						/**************************************************
						 * Add the carpool's number of votes to the queue
						 **************************************************/
						queue.enqueue(Integer.parseInt(line[1]));

					} else {
						break;
					}
					count++;
				} catch (IOException ignored) {}
			}
		} catch (FileNotFoundException ignored) {}

		/**************************************************
		 * Return the queue.
		 **************************************************/
		return queue;
	}

	/**************************************************
	 * This function takes the above functions queue
	 * and churns out a report based on the data
	 * contained in the queue. A string is returned
	 * containing the entire report.
	 **************************************************/
	static public String getVoterReport(CircularArrayQueue<Integer> voterQueue) {
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
				int randomTime = getRandomTime();
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

		/**************************************************
		 * Return our completed report to calling statement.
		 **************************************************/
		return result;
	}
}
