//Brian Cavin
//Project 2
package TweetPackage;

import java.util.ArrayList;
import java.util.Collection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


public class TweetCollection {

	private String fileName;
	private HashMap<Long, Tweet> myData;
	private ArrayList<Long> myIds;
		
	//Default constructor
	public TweetCollection() {
		fileName = null;
		myData = new HashMap<Long, Tweet>();
		myIds = new ArrayList<Long>();
	}
	//Parameterized Constructor
	public TweetCollection(String fn) {
		this();
		fileName = fn;
		readFile();
	}
	//Adds a tweet
	public void addTweet(Tweet t) {
		myData.put(t.getId(), t.getTweet());
		myIds.add(t.getId());
	}
	//File reader
	private void readFile () {
		BufferedReader lineReader = null;
		try {
			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String line = null;
			while ((line = lineReader.readLine())!=null) {
				String[] tokens = line.split(",");
				int polarity = Integer.parseInt(tokens[0]);
				long id = Long.parseLong(tokens[1]);
				String tweeter = tokens[2];
				String tweetBody = tokens[3];
				addTweet(new Tweet(polarity, id, tweeter, tweetBody));
			}
		} catch (Exception e) {
			System.err.println("there was a problem with the file reader, try different read type.");
			try {
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
				String line = null;
				while ((line = lineReader.readLine())!=null) {
					String[] tokens = line.split(",");
					int polarity = Integer.parseInt(tokens[0]);
					long id = Long.parseLong(tokens[1]);
					String tweeter = tokens[2];
					String tweetBody = tokens[3];
					addTweet(new Tweet(polarity, id, tweeter, tweetBody));
					}
			} catch (Exception e2) {
				System.err.println("there was a problem with the file reader, try again.  either no such file or format error");
			} finally {
				if (lineReader != null)
					try {
						lineReader.close();
					} catch (IOException e2) {
						System.err.println("could not close BufferedReader");
					}
			}			
		} finally {
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e) {
					System.err.println("could not close BufferedReader");
				}
		}
	}
	//Tweet getter
	public Tweet getTweet(long id) {
		if(!myData.containsKey(id)) {
			System.out.println("ERROR: Tweet ID not found. Please enter a different tweet ID.");
			return null;
		}
		else {
			return myData.get(id);
		}
	}
	//Deletes tweet
	public void removeTweet(long target) {
		if(!myData.containsKey(target)) {
			System.out.println("ERROR: Tweet ID not found. Please enter a different tweet ID.");
		}
		myData.remove(target);
		myIds.remove(target);
	}
	//Getter for tweet ID
	public long getId(Tweet t) { 
		return t.getId();
	}
	//Returns an ArrayList of tweet IDs from username
	public ArrayList<Long> getIdsByUser(String tweeter) {
		ArrayList<Long> someTweeter = new ArrayList<Long>();
		for(Long long1 : myData.keySet()) {
			if(myData.get(long1).getTweeter().equals(tweeter)) {
				someTweeter.add(myData.get(long1).getId());
			}
		}
		if(someTweeter.isEmpty()) {
			System.out.println("User not found.");
		}
		return someTweeter;
	}
	//Prints a tweet based on tweet ID
	public String toString(long id) {
		return myData.get(id).toString();
	}
	//Returns a list of Ids
	public ArrayList<Long> getIdList(){
		return myIds;
	}
	// overloaded method: this calls doWrite with file used to read data
	// use this for saving data between runs
	public void writeFile () {
		doWrite(fileName);
		}
	// overloaded method: this calls doWrite with different file name
	// use this for testing write
	public void writeFile(String altFileName) {
		doWrite(altFileName);
	}
	//Write tweets to a file
	private void doWrite(String fn) {
		
		try{
			FileWriter fw = new FileWriter(fn);
			BufferedWriter myOutfile = new BufferedWriter(fw);
			for(Long long1 : myData.keySet()) {
				myOutfile.write (myData.get(long1).toStringwrite()+"\n");	
			}
			myOutfile.flush();
			myOutfile.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Didn't save to " + fn);
		}
	}
	//Predicts the polarity of a single tweet
	public int tweetPredictor(Tweet t) {
		int polarity = 2;
		//Negative
		if(t.getTweetBody().contains("hate") || t.getTweetBody().contains("HATE") || t.getTweetBody().contains("Hate")
				|| t.getTweetBody().contains("dead") || t.getTweetBody().contains("FML") || t.getTweetBody().contains("dumb")
				|| t.getTweetBody().contains("dumbass") || t.getTweetBody().contains("sad") || t.getTweetBody().contains("waste")
				|| t.getTweetBody().contains("ugly") || t.getTweetBody().contains("die") || t.getTweetBody().contains("scare") 
				|| t.getTweetBody().contains("blah") || t.getTweetBody().contains("freaking") || t.getTweetBody().contains("annoying") 
				|| t.getTweetBody().contains("terrifying") || t.getTweetBody().contains("...") || t.getTweetBody().contains("fired")
				|| t.getTweetBody().contains("AT&T") || t.getTweetBody().contains("AT&amp;T") || t.getTweetBody().contains("evil")
				|| t.getTweetBody().contains("fight") || t.getTweetBody().contains("come on") || t.getTweetBody().contains("Fuck this")
				|| t.getTweetBody().contains("fuck this") || t.getTweetBody().contains("*ck") || t.getTweetBody().contains("miserable")
				|| t.getTweetBody().contains("why") || t.getTweetBody().contains("hurts") || t.getTweetBody().contains(":(")
				|| t.getTweetBody().contains(": (") || t.getTweetBody().contains("):") || t.getTweetBody().contains(") :")
				|| t.getTweetBody().contains(">:(") || t.getTweetBody().contains(":*(") || t.getTweetBody().contains("blech")
				|| t.getTweetBody().contains("ugh") || t.getTweetBody().contains("sore") || t.getTweetBody().contains("hurt")
				|| t.getTweetBody().contains("don't @") || t.getTweetBody().contains("trippin") || t.getTweetBody().contains("fucked up")
				|| t.getTweetBody().contains("sucks") || t.getTweetBody().contains("asshole") || t.getTweetBody().contains("piece of shit")
				|| t.getTweetBody().contains("cannot belive") || t.getTweetBody().contains("awful") || t.getTweetBody().contains("not looking forward")
				|| t.getTweetBody().contains("wreck") || t.getTweetBody().contains("almost died") || t.getTweetBody().contains("Comcast")
				|| t.getTweetBody().contains("comcast") || t.getTweetBody().contains("customer service") || t.getTweetBody().contains("rude")) {
			polarity = 0;
		}
		//Positive 
		if(t.getTweetBody().contains("love") || t.getTweetBody().contains("Love") || t.getTweetBody().contains("LOVE") 
				|| t.getTweetBody().contains("luv") || t.getTweetBody().contains("happy") || t.getTweetBody().contains("Happy") 
				|| t.getTweetBody().contains("HAPPY") || t.getTweetBody().contains("glad") || t.getTweetBody().contains("YAY")
				|| t.getTweetBody().contains("Yay") || t.getTweetBody().contains("yay") || t.getTweetBody().contains("great")
				|| t.getTweetBody().contains("good") || t.getTweetBody().contains("thank you") || t.getTweetBody().contains("Thank you")
				|| t.getTweetBody().contains("THANK YOU") || t.getTweetBody().contains("well done") || t.getTweetBody().contains("wonderful")
				|| t.getTweetBody().contains("got a new") || t.getTweetBody().contains("got some new") || t.getTweetBody().contains("got new")
				|| t.getTweetBody().contains("best") || t.getTweetBody().contains("BEST") || t.getTweetBody().contains("Best")
				|| t.getTweetBody().contains("just got") || t.getTweetBody().contains("perfect") || t.getTweetBody().contains("lovin")
				|| t.getTweetBody().contains(":)") || t.getTweetBody().contains(";)") || t.getTweetBody().contains(": )") 
				|| t.getTweetBody().contains("; )") || t.getTweetBody().contains("(;") || t.getTweetBody().contains("(:")
				|| t.getTweetBody().contains("awesome") || t.getTweetBody().contains("loved") || t.getTweetBody().contains("movie")
				|| t.getTweetBody().contains("fun") || t.getTweetBody().contains("had a blast") || t.getTweetBody().contains("woo")
				|| t.getTweetBody().contains("WOO") || t.getTweetBody().contains("still got it") || t.getTweetBody().contains("can't wait")
				|| t.getTweetBody().contains("AWESOME") || t.getTweetBody().contains("lucky") || t.getTweetBody().contains("wish me luck")
				|| t.getTweetBody().contains("so nice") || t.getTweetBody().contains("highly recommend") || t.getTweetBody().contains("looking forward")) {
			polarity = 4;
		}
		
		if(t.getPolarity() == polarity) {
			
		}
			
		return polarity;
	}
	//Predicts a collection of tweets and returns the accuracy percentage and displays total/right/accuracy
	public int tweetPredictor() {
		int goodGuess = 0;
		int badGuess = 0;
		int mehGuess = 0;
		double tweetsTested = 0;
		int goodActual = 0;
		int badActual = 0;
		int mehActual = 0;
		int goodPercent = 0;
		int badPercent = 0;
		int mehPercent = 0;
		double result = 0;
		double totalPercent = 0;
		
		
		for(Long long1 : myData.keySet()) {
			
			if( myData.get(long1).getPolarity() == 0) {
				badActual++;
			}
			else if( myData.get(long1).getPolarity() == 4) {
				goodActual++;
			}
			else {
				mehActual++;
			}
			
			if(tweetPredictor(myData.get(long1)) == 0) {
				badGuess++;
			}
			else if(tweetPredictor(myData.get(long1)) == 4) {
				goodGuess++;
			}
			else {
				mehGuess++;
			}
			tweetsTested++;
		}
		
		badPercent = badActual - badGuess;
		goodPercent = goodActual - goodGuess;
		//mehPercent = mehActual - mehGuess;
		totalPercent = badPercent + goodPercent + mehPercent;
		result = (totalPercent/tweetsTested)*100;
		
		int resultInt = (int)result;
		if((result - resultInt) >= 0.5) {
			resultInt++;
		}
		
		System.out.println("Tweets tested: " + tweetsTested + ". Correct predictions: " + totalPercent + ". Percent correct: " + resultInt + "%.");
		
		return resultInt;
	}
	
	public String toString()
	{
		String ret = myData.size() + " tweets";
		Collection<Tweet> values = myData.values();
		
			/*ret += ":\n";
			for (Tweet t: values) {
				ret += t + "\n";
			}*/
		ret += ":\n";
			for(Long long1 : myIds) {
				ret += getTweet(long1) + "\n";
			}
		
		return ret.substring(0, ret.length() - 1); // trim last \n
	}
}
