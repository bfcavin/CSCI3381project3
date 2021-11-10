//Brian Cavin
//Project 2
package project2;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.MenuDragMouseListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.MenuDragMouseEvent;



public class MainPanel extends JPanel {
	private TweetCollection tc;
	private Tweet predictTweet;
	private Tweet newTweet;
	private JTextField userName;
	private JComboBox comboBox;
	private ArrayList idList;
	private JTextField textField;
	private boolean showtweet;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private int singlePolarity;
	private FileNameExtensionFilter filter;
	private JTextField accuracyPercent;
	private JTextField overallAccuracy;
	
	//-----------------------------------------------------------------
	// Sets up the panel, including the timer for the animation.
	//-----------------------------------------------------------------
	public MainPanel()
	{
		//build a tweet collection
		tc = new TweetCollection("./testProcessed.txt");
		showtweet = false;
		//initializes the panel
		setPreferredSize (new Dimension(800, 800));
		setLayout(null);
		
		final JFileChooser chooser = new JFileChooser();

		
		//Scrollpane for big tweet list
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 78, 385, 412);
		add(scrollPane);
		
		//Displays tweet list
		JTextArea allTweets = new JTextArea();
		allTweets.setToolTipText("A list of all of the tweets in the collection");
		allTweets.setLineWrap(true);
		allTweets.setEditable(false);
		allTweets.setText(tc.toString());
		scrollPane.setViewportView(allTweets);
		
		//Label for tweet list
		JLabel lblTweets = new JLabel("Tweets:");
		lblTweets.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTweets.setBounds(10, 32, 124, 31);
		add(lblTweets);
		
		//Place to enter a username
		userName = new JTextField();
		userName.setBounds(530, 63, 221, 19);
		add(userName);
		userName.setColumns(10);
		
		//Label for username search bar
		JLabel lblUserId = new JLabel("Enter a Username:");
		lblUserId.setBounds(422, 61, 130, 22);
		add(lblUserId);
		
		//Dropdown list of tweet ids
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(422, 141, 329, 21);
		add(comboBox);
		
		//Text area that displays a single tweet based on which ID is selected
		JTextArea singleTweet = new JTextArea();
		singleTweet.setLineWrap(true);
		singleTweet.setEditable(false);
		singleTweet.setBounds(422, 194, 329, 90);
		add(singleTweet);
		

		JRadioButton rdbtnnegative = new JRadioButton("0 (Negative)");
		buttonGroup.add(rdbtnnegative);
		rdbtnnegative.setBounds(422, 290, 103, 21);
		add(rdbtnnegative);
		
		JRadioButton rdbtnneutral = new JRadioButton("2 (Neutral)");
		buttonGroup.add(rdbtnneutral);
		rdbtnneutral.setBounds(530, 290, 103, 21);
		add(rdbtnneutral);
		
		JRadioButton rdbtnpositive = new JRadioButton("4 (Positive)");
		buttonGroup.add(rdbtnpositive);
		rdbtnpositive.setBounds(637, 290, 103, 21);
		add(rdbtnpositive);
		
		JTextArea tweetPredictorUserName = new JTextArea();
		tweetPredictorUserName.setEditable(false);
		tweetPredictorUserName.setBounds(95, 571, 300, 22);
		add(tweetPredictorUserName);
		
		JRadioButton predictPolarityNeg = new JRadioButton("0 (Negative)");
		predictPolarityNeg.setBounds(95, 662, 103, 21);
		add(predictPolarityNeg);
		
		JRadioButton predictPolarityNeut = new JRadioButton("2 (Neutral)");
		predictPolarityNeut.setBounds(210, 662, 103, 21);
		add(predictPolarityNeut);
		
		JRadioButton predictPolarityPos = new JRadioButton("4 (Positive)");
		predictPolarityPos.setBounds(338, 662, 103, 21);
		add(predictPolarityPos);
		
		JRadioButton polarityActualNeg = new JRadioButton("0 (Negative)");
		polarityActualNeg.setBounds(95, 705, 103, 21);
		add(polarityActualNeg);
		
		JRadioButton polarityActualNeut = new JRadioButton("2 (Neutral)");
		polarityActualNeut.setBounds(210, 705, 103, 21);
		add(polarityActualNeut);
		
		JRadioButton polarityActualPos = new JRadioButton("4 (Positive)");
		polarityActualPos.setBounds(338, 705, 103, 21);
		add(polarityActualPos);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 577, 75, 13);
		add(lblUsername);
		
		JTextArea tweetPredictorID = new JTextArea();
		tweetPredictorID.setEditable(false);
		tweetPredictorID.setBounds(95, 539, 300, 22);
		add(tweetPredictorID);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 545, 45, 13);
		add(lblId);
		
		JLabel lblTweet = new JLabel("Tweet:");
		lblTweet.setBounds(10, 609, 45, 13);
		add(lblTweet);
		
		JLabel lblAccuracy = new JLabel("Accuracy:");
		lblAccuracy.setBounds(436, 686, 82, 13);
		add(lblAccuracy);
		
		JLabel lblPredictedPolarity = new JLabel("Predicted Polarity");
		lblPredictedPolarity.setBounds(10, 666, 79, 13);
		add(lblPredictedPolarity);
		
		JLabel lblActualPolarity = new JLabel("Actual Polarity");
		lblActualPolarity.setBounds(10, 709, 79, 13);
		add(lblActualPolarity);
		
		accuracyPercent = new JTextField();
		accuracyPercent.setEditable(false);
		accuracyPercent.setToolTipText("Accuracy of the single tweet prediction");
		accuracyPercent.setBounds(505, 683, 96, 19);
		add(accuracyPercent);
		accuracyPercent.setColumns(10);
		
		JLabel lblTweetEditor = new JLabel("Tweet Editor");
		lblTweetEditor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTweetEditor.setBounds(405, 317, 188, 31);
		add(lblTweetEditor);
		
		JRadioButton polNegEditor = new JRadioButton("0 (Negative)");
		polNegEditor.setBounds(498, 350, 103, 21);
		add(polNegEditor);
		
		JRadioButton polNeutEditor = new JRadioButton("2 (Neutral)");
		polNeutEditor.setBounds(604, 350, 103, 21);
		add(polNeutEditor);
		
		JRadioButton polPosEditor = new JRadioButton("4 (Positive)");
		polPosEditor.setBounds(709, 350, 103, 21);
		add(polPosEditor);
		
		JLabel lblId_1_1 = new JLabel("Polarity:");
		lblId_1_1.setBounds(425, 354, 45, 13);
		add(lblId_1_1);
		
		
		
		//Button that retrieves tweets
		JButton btnGetTweetsBy = new JButton("Get Tweets By Username");

		btnGetTweetsBy.setBounds(422, 89, 329, 19);
		add(btnGetTweetsBy);
		btnGetTweetsBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idList = new ArrayList();
				idList = tc.getIdsByUser(userName.getText());
				comboBox.setModel(new DefaultComboBoxModel(idList.toArray()));
				
				if(idList.isEmpty()) {
					boolean error = true;
					JOptionPane.showMessageDialog(getTopLevelAncestor(), "User not found.");
				}
			}
		});
		
		//Label for comboBox
		JLabel lblSelctTweetId = new JLabel("Selct Tweet ID:");
		lblSelctTweetId.setBounds(420, 118, 196, 13);
		add(lblSelctTweetId);
		
		//Text area to enter a tweet to predict the polarity
		JTextArea tweetPredictor = new JTextArea();
		tweetPredictor.setEditable(false);
		tweetPredictor.setToolTipText("Tweet body");
		tweetPredictor.setBounds(95, 603, 695, 22);
		add(tweetPredictor);
		
		//Combo box action listener, black magic
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				singleTweet.setText(tc.getTweet((long) comboBox.getSelectedItem()).toString());
				//tweetPredictor.setText(tc.getTweet((long) comboBox.getSelectedItem()).getTweetBody());
				singlePolarity = tc.getTweet((long) comboBox.getSelectedItem()).getPolarity();
				if(singlePolarity == 0) {
					rdbtnnegative.setSelected(true);
				}
				else if (singlePolarity == 4) {
					rdbtnpositive.setSelected(true);
				}
				else {
					rdbtnneutral.setSelected(true);
				}
			}
			
		});
		
		
		
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				tweetPredictor.setText(tc.getTweet((long) comboBox.getSelectedItem()).getTweetBody());
				tweetPredictorID.setText(Long.toString(tc.getTweet((long) comboBox.getSelectedItem()).getId()));
				//singlePolarity = tc.getTweet((long) comboBox.getSelectedItem()).getPolarity();
			}
		});
		addPopup(singleTweet, popupMenu);
		
		//Populates tweet body into tweet predictor if pop up menu is used
		JMenuItem mntmPredict = new JMenuItem("Predict");
		mntmPredict.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				tweetPredictor.setText(tc.getTweet((long) comboBox.getSelectedItem()).getTweetBody());
				tweetPredictorID.setText(Long.toString(tc.getTweet((long) comboBox.getSelectedItem()).getId()));
				tweetPredictorUserName.setText(tc.getTweet((long) comboBox.getSelectedItem()).getTweeter());
				
				if(singlePolarity == 0) {
					polarityActualNeg.setSelected(true);
				}
				else if (singlePolarity == 4) {
					polarityActualPos.setSelected(true);
				}
				else {
					polarityActualNeut.setSelected(true);
				}
				
			}
		});
		popupMenu.add(mntmPredict);
		
		JMenuItem mntmEdit = new JMenuItem("Edit...");
		popupMenu.add(mntmEdit);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 800, 22);
		add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHelp = new JMenuItem("Help...");
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(getTopLevelAncestor(), "I barely know what I am doing, if you're looking for help here we're in trouble.");
			}
		});
		mnHelp.add(mntmHelp);
		
		JButton btnPredictTweet = new JButton("Predict Tweet");
		btnPredictTweet.setToolTipText("Predicts the polarity of the current tweet and compares it to the actual polarity");
		btnPredictTweet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TweetCollection pt = new TweetCollection();
				pt.addTweet(tc.getTweet((long) comboBox.getSelectedItem()).getTweet());
				
				//int accuracy = pt.tweetPredictor();
				int actualpol =tc.getTweet((long) comboBox.getSelectedItem()).getPolarity();
				int predictpol = pt.tweetPredictor(tc.getTweet((long) comboBox.getSelectedItem()));
				
				int accuracy = 0;
				
				if (actualpol == predictpol) {
					accuracy = 100;
				}
				else if (actualpol - predictpol == 0 || predictpol - actualpol ==0) {
					accuracy = 50;
				}
				else {
					accuracy = 0;
				}
				
				accuracyPercent.setText(accuracy + "%");
				
				
				
				
				if(tc.getTweet((long) comboBox.getSelectedItem()).getPolarity() == 0) {
					predictPolarityNeg.setSelected(true);
				}
				else if (tc.getTweet((long) comboBox.getSelectedItem()).getPolarity() == 4) {
					predictPolarityPos.setSelected(true);
				}
				else {
					predictPolarityNeut.setSelected(true);
				}
				
				
				
			}
		});
		btnPredictTweet.setBounds(95, 635, 695, 21);
		add(btnPredictTweet);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(488, 432, -8, 50);
		add(separator);
		
		JButton btnPredictAllTweets = new JButton("Predict All Tweets");
		btnPredictAllTweets.setToolTipText("Predicts and compares the polarity of every tweet in the collection and displays the accuracy of the prediction");
		btnPredictAllTweets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int accuracy = tc.tweetPredictor();
				overallAccuracy.setText(accuracy + "%");
			}
		});
		btnPredictAllTweets.setBounds(22, 751, 251, 21);
		add(btnPredictAllTweets);
		
		JLabel lblAccuracy_1 = new JLabel("Accuracy:");
		lblAccuracy_1.setBounds(302, 755, 72, 13);
		add(lblAccuracy_1);
		
		overallAccuracy = new JTextField();
		overallAccuracy.setEditable(false);
		overallAccuracy.setToolTipText("Accuacy of the tweet collection prediction");
		overallAccuracy.setBounds(384, 752, 96, 19);
		add(overallAccuracy);
		overallAccuracy.setColumns(10);
		
		JLabel lblTweet_1 = new JLabel("Tweet:");
		lblTweet_1.setBounds(432, 172, 96, 13);
		add(lblTweet_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(405, 32, 2, 561);
		add(separator_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(405, 591, 385, 2);
		add(separator_1_1);
		
		JLabel lblSingleTweet = new JLabel("Single Tweet:");
		lblSingleTweet.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSingleTweet.setBounds(422, 32, 150, 31);
		add(lblSingleTweet);
		
		JLabel lblTweetPredictor = new JLabel("Tweet Predictor");
		lblTweetPredictor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTweetPredictor.setBounds(10, 500, 188, 31);
		add(lblTweetPredictor);
		
		JTextArea editTweetArea = new JTextArea();
		editTweetArea.setLineWrap(true);
		editTweetArea.setBounds(422, 455, 329, 90);
		add(editTweetArea);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setBounds(405, 317, 385, 2);
		add(separator_1_1_1);
		
		JLabel lblId_1 = new JLabel("ID:");
		lblId_1.setBounds(425, 377, 45, 13);
		add(lblId_1);
		
		JTextArea idEditor = new JTextArea();
		idEditor.setBounds(510, 371, 241, 22);
		add(idEditor);
		
		JLabel lblUsername_1 = new JLabel("Username:");
		lblUsername_1.setBounds(416, 409, 82, 13);
		add(lblUsername_1);
		
		JTextArea usernameEditor = new JTextArea();
		usernameEditor.setBounds(508, 403, 241, 22);
		add(usernameEditor);
		
		JLabel lblTweet_1_1 = new JLabel("Tweet:");
		lblTweet_1_1.setBounds(422, 432, 96, 13);
		add(lblTweet_1_1);
		
		JButton btnEditaddTweet = new JButton("Edit/Add Tweet");
		btnEditaddTweet.setToolTipText("If the tweet ID is unchanged, this will edit the tweet, if the ID is changed, it will add a new tweet");
		btnEditaddTweet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				int newPolarity = tc.getTweet((long) comboBox.getSelectedItem()).getPolarity();
				long newId = Long.parseLong(idEditor.getText());
				String newUser = usernameEditor.getText();
				String newTweetbody = editTweetArea.getText();
				
				if(polNegEditor.isSelected()) {
					newPolarity = 0;
				}
				else if(polPosEditor.isSelected()) {
					newPolarity = 4;
				}
				else {
					newPolarity = 2;
				}
				
				if(Long.parseLong(idEditor.getText()) == tc.getTweet((long) comboBox.getSelectedItem()).getId()) {
					
					tc.getTweet((long) comboBox.getSelectedItem()).setPolarity(newPolarity);
					tc.getTweet((long) comboBox.getSelectedItem()).setTweeter(newUser);
					tc.getTweet((long) comboBox.getSelectedItem()).setTweetBody(newTweetbody);
					allTweets.setText(tc.toString());
				}
				else {
					
					Tweet newTweet = new Tweet(newPolarity, newId, newUser, newTweetbody);
					tc.addTweet(newTweet);
					allTweets.setText(tc.toString());
					
				}
			}
		});
		btnEditaddTweet.setBounds(442, 555, 130, 21);
		add(btnEditaddTweet);
		
		JButton btnDeleteTweet = new JButton("Delete Tweet");
		btnDeleteTweet.setToolTipText("This will remove the tweet, however all of the fields will remain filled in the event you decide you would like to add the tweet back to the collection");
		btnDeleteTweet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tc.removeTweet(Long.parseLong(idEditor.getText()));
				allTweets.setText(tc.toString());
			}
		});
		btnDeleteTweet.setBounds(604, 555, 130, 21);
		add(btnDeleteTweet);
		
	
		
		
		
		
		
		//Allows user to select a file to open
		mntmOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//JFileChooser chooser = new JFileChooser();
			   /* filter = new FileNameExtensionFilter(
			        ".txt");
			    chooser.setFileFilter(filter);*/
			    int returnVal = chooser.showOpenDialog(MainPanel.this); 
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			      /* System.out.println("You chose to open this file: " +
			        chooser.getSelectedFile().getName());*/
			    	
			    	File file = chooser.getSelectedFile();
			    	tc = new TweetCollection(file.getName());
			    	allTweets.setText(tc.toString());
			    	
			    }
				
			}
		});
		
	
		mntmSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				tc.writeFile();
				
			}
		});
		
		
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//tc.writeFile();
				System.exit(0);
				
			}
		});
		
		
		mntmEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				editTweetArea.setText(tc.getTweet((long) comboBox.getSelectedItem()).getTweetBody());
				usernameEditor.setText(tc.getTweet((long) comboBox.getSelectedItem()).getTweeter());
				idEditor.setText(Long.toString(tc.getTweet((long) comboBox.getSelectedItem()).getId()));
				
				singlePolarity = tc.getTweet((long) comboBox.getSelectedItem()).getPolarity();
				
				/*if(singlePolarity == 0) {
					rdbtnnegative.setSelected(true);
				}
				else if (singlePolarity == 4) {
					rdbtnpositive.setSelected(true);
				}
				else {
					rdbtnneutral.setSelected(true);
				}*/
			
				
			}
		});
		
	
		
		
		
		
		
		
		
		
		
		
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}