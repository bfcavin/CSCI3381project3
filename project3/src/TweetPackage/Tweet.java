package TweetPackage;

public class Tweet implements Comparable<Tweet>{
	private int polarity;
	private long id;
	private String tweeter;
	private String tweetBody;
	
	//Default Constructor
	public Tweet() {
		polarity = -1;
		id = -1;
		tweeter = "NO DATA";
		tweetBody = "NO DATA";
	}
	//Parameterized Constructor
	public Tweet(int p, long i, String u, String t) {
		this();
		polarity = p;	
		id = i;
		tweeter = u;
		tweetBody = t;
	}
	// getters and setters for tweet data
	public int getPolarity() {
		return polarity;
	}
	public void setPolarity(int p) {
		polarity = p;
	}
	public long getId() {
		return id;
	}
	public void setId(long i) {
		id = i;
	}	
	public int compareTo(long i) {
		if(id > i) {
			return -1;
		}
		else if(id < i) {
			return 1;
		}
		else {
			return 0;
		}
	}
	public String getTweeter() {
		return tweeter.toString();
	}
	public void setTweeter(String u) {
		tweeter = u;
	}
	public String getTweetBody() {
		return tweetBody.toString();
	}
	public void setTweetBody(String t) {
		tweetBody = t;
	}
	public Tweet getTweet() {
		return this;
	}
	// string representation of tweet
	public String toString () {
		return "Polarity: " + polarity +  "\nID: " + id + "\nUsername: " + tweeter.toString() + "\nTweet: " + tweetBody.toString() + "\n";
		//return polarity +"," + id + "," + tweeter.toString() + "," + tweetBody.toString();
	}	
	public String toStringwrite () {
		return polarity +  "," + id + "," + tweeter.toString() + "," + tweetBody.toString();
		//return polarity +"," + id + "," + tweeter.toString() + "," + tweetBody.toString();
	}	
	public int compareTo(Tweet t) {
		if(polarity == t.polarity && id == t.id && tweeter.compareTo(t.tweeter) == 1 
				&& tweetBody.compareTo(t.tweetBody) ==1) {
			return 1;
		}
		else {
			return 0;
		}
	}
	public void setAll(int p, long i, String u, String b) {
		setPolarity(p);
		setId(i);
		setTweeter(u);
		setTweetBody(b);
	}
}

