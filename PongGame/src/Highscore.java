import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Label;

public class Highscore {

	protected Shell shlPongHighscores;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Highscore window = new Highscore();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlPongHighscores.open();
		shlPongHighscores.forceFocus();
		shlPongHighscores.layout();
		while (!shlPongHighscores.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		List<Score> scores = new ArrayList<Score>();
		FileReader in = null;
		try {
			in = new FileReader("scores.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	    try {
		    BufferedReader br = new BufferedReader(in);
	    	String line;
	    	while ((line = br.readLine()) != null) {
	    	    String[] sc = line.split(":");
	    	    if (!sc[0].equals("rank"))
	    	    {
	    	    	int ranknum = Integer.parseInt(sc[0]);
	    	    	String name = sc[1];
	    	    	int score = Integer.parseInt(sc[2]);
	    	    	Score s = new Score(ranknum, name, score);
	    	    	scores.add(s);
	    	    }
	    	    
	    	}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		shlPongHighscores = new Shell();
		shlPongHighscores.setSize(450, 350);
		shlPongHighscores.setText("Pong! Highscores");
		
		Button btnBack = new Button(shlPongHighscores, SWT.NONE);
		btnBack.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MainWindow main = new MainWindow();
				shlPongHighscores.close();
				main.open();
			}
		});
		btnBack.setBounds(10, 10, 75, 25);
		btnBack.setText("Back");
		
		Button btnRadioReset = new Button(shlPongHighscores, SWT.RADIO);
		btnRadioReset.setBounds(10, 260, 90, 16);
		btnRadioReset.setText("Reset");
		
		Button btnReset = new Button(shlPongHighscores, SWT.NONE);
		btnReset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnRadioReset.getSelection())
				{
					PrintWriter writer = null;
					try {
						writer = new PrintWriter("scores.txt");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					writer.print("");
					writer.close();
					scores.clear();
					shlPongHighscores.close();
					open();
				}
			}
		});
		btnReset.setBounds(10, 276, 90, 25);
		btnReset.setText("Confirm Reset");
		
		Label lblName = new Label(shlPongHighscores, SWT.NONE);
		lblName.setBounds(216, 20, 55, 15);
		lblName.setText("Name");
		
		Label lblScore = new Label(shlPongHighscores, SWT.NONE);
		lblScore.setBounds(357, 20, 55, 15);
		lblScore.setText("Score");
		
		Label lblRank = new Label(shlPongHighscores, SWT.NONE);
		lblRank.setBounds(138, 20, 55, 15);
		lblRank.setText("Rank");
		
		Label label = new Label(shlPongHighscores, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(191, 20, 2, 236);
		
		Label label_1 = new Label(shlPongHighscores, SWT.SEPARATOR);
		label_1.setBounds(338, 20, 2, 236);
		
		Label label_2 = new Label(shlPongHighscores, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setBounds(106, 41, 306, 2);
		
		Label label_3 = new Label(shlPongHighscores, SWT.NONE);
		label_3.setBounds(168, 52, 17, 15);
		label_3.setText("1");
		
		Label label_4 = new Label(shlPongHighscores, SWT.NONE);
		label_4.setText("2");
		label_4.setBounds(168, 73, 17, 15);
		
		Label label_5 = new Label(shlPongHighscores, SWT.NONE);
		label_5.setText("3");
		label_5.setBounds(168, 94, 17, 15);
		
		Label label_6 = new Label(shlPongHighscores, SWT.NONE);
		label_6.setText("4");
		label_6.setBounds(168, 115, 17, 15);
		
		Label label_7 = new Label(shlPongHighscores, SWT.NONE);
		label_7.setText("5");
		label_7.setBounds(168, 136, 17, 15);
		
		Label label_8 = new Label(shlPongHighscores, SWT.NONE);
		label_8.setText("6");
		label_8.setBounds(168, 157, 17, 15);
		
		Label label_9 = new Label(shlPongHighscores, SWT.NONE);
		label_9.setText("7");
		label_9.setBounds(168, 178, 17, 15);
		
		Label label_10 = new Label(shlPongHighscores, SWT.NONE);
		label_10.setText("8");
		label_10.setBounds(168, 199, 17, 15);
		
		Label label_11 = new Label(shlPongHighscores, SWT.NONE);
		label_11.setText("9");
		label_11.setBounds(168, 220, 17, 15);
		
		Label label_12 = new Label(shlPongHighscores, SWT.NONE);
		label_12.setText("10");
		label_12.setBounds(168, 241, 17, 15);
		
		Label lblRank1 = new Label(shlPongHighscores, SWT.NONE);
		for (Score score : scores)
		{
			if (score.getRank() == 1)
			{
				lblRank1.setText(score.getName());
			}
		}
		lblRank1.setBounds(199, 52, 133, 15);
		
		Label lblRank2 = new Label(shlPongHighscores, SWT.NONE);
		for (Score score : scores)
		{
			if (score.getRank() == 2)
			{
				lblRank2.setText(score.getName());
			}
		}
		lblRank2.setBounds(199, 73, 133, 15);
		
		Label lblRank3 = new Label(shlPongHighscores, SWT.NONE);
		for (Score score : scores)
		{
			if (score.getRank() == 3)
			{
				lblRank3.setText(score.getName());
			}
		}
		lblRank3.setBounds(199, 94, 133, 15);
		
		Label lblRank4 = new Label(shlPongHighscores, SWT.NONE);
		for (Score score : scores)
		{
			if (score.getRank() == 4)
			{
				lblRank4.setText(score.getName());
			}
		}
		lblRank4.setBounds(199, 115, 133, 15);
		
		Label lblRank5 = new Label(shlPongHighscores, SWT.NONE);
		for (Score score : scores)
		{
			if (score.getRank() == 5)
			{
				lblRank5.setText(score.getName());
			}
		}
		lblRank5.setBounds(199, 136, 133, 15);
		
		Label lblRank6 = new Label(shlPongHighscores, SWT.NONE);
		for (Score score : scores)
		{
			if (score.getRank() == 6)
			{
				lblRank6.setText(score.getName());
			}
		}
		lblRank6.setBounds(199, 157, 133, 15);
		
		Label lblRank7 = new Label(shlPongHighscores, SWT.NONE);
		for (Score score : scores)
		{
			if (score.getRank() == 7)
			{
				lblRank7.setText(score.getName());
			}
		}
		lblRank7.setBounds(199, 178, 133, 15);
		
		Label lblRank8 = new Label(shlPongHighscores, SWT.NONE);
		for (Score score : scores)
		{
			if (score.getRank() == 8)
			{
				lblRank8.setText(score.getName());
			}
		}
		lblRank8.setBounds(199, 199, 133, 15);
		
		Label lblRank9 = new Label(shlPongHighscores, SWT.NONE);
		for (Score score : scores)
		{
			if (score.getRank() == 9)
			{
				lblRank9.setText(score.getName());
			}
		}
		lblRank9.setBounds(199, 220, 133, 15);
		
		Label lblRank10 = new Label(shlPongHighscores, SWT.NONE);
		for (Score score : scores)
		{
			if (score.getRank() == 10)
			{
				lblRank10.setText(score.getName());
			}
		}
		lblRank10.setBounds(199, 241, 133, 15);
		
		Label lblScore1 = new Label(shlPongHighscores, SWT.NONE);
		for (Score score : scores)
		{
			if (score.getRank() == 1)
			{
				lblScore1.setText("" + score.getScore());
			}
		}
		lblScore1.setBounds(346, 49, 66, 15);
		
		Label lblScore2 = new Label(shlPongHighscores, SWT.NONE);
		for (Score score : scores)
		{
			if (score.getRank() == 2)
			{
				lblScore2.setText("" + score.getScore());
			}
		}
		lblScore2.setBounds(346, 70, 66, 15);
		
		Label lblScore3 = new Label(shlPongHighscores, SWT.NONE);
		for (Score score : scores)
		{
			if (score.getRank() == 3)
			{
				lblScore3.setText("" + score.getScore());
			}
		}
		lblScore3.setBounds(346, 91, 66, 15);
		
		Label lblScore4 = new Label(shlPongHighscores, SWT.NONE);
		for (Score score : scores)
		{
			if (score.getRank() == 4)
			{
				lblScore4.setText("" + score.getScore());
			}
		}
		lblScore4.setBounds(346, 112, 66, 15);
		
		Label lblScore5 = new Label(shlPongHighscores, SWT.NONE);
		for (Score score : scores)
		{
			if (score.getRank() == 5)
			{
				lblScore5.setText("" + score.getScore());
			}
		}
		lblScore5.setBounds(346, 133, 66, 15);
		
		Label lblScore6 = new Label(shlPongHighscores, SWT.NONE);
		for (Score score : scores)
		{
			if (score.getRank() == 6)
			{
				lblScore6.setText("" + score.getScore());
			}
		}
		lblScore6.setBounds(346, 154, 66, 15);
		
		Label lblScore7 = new Label(shlPongHighscores, SWT.NONE);
		for (Score score : scores)
		{
			if (score.getRank() == 7)
			{
				lblScore7.setText("" + score.getScore());
			}
		}
		lblScore7.setBounds(346, 175, 66, 15);
		
		Label lblScore8 = new Label(shlPongHighscores, SWT.NONE);
		for (Score score : scores)
		{
			if (score.getRank() == 8)
			{
				lblScore8.setText("" + score.getScore());
			}
		}
		lblScore8.setBounds(346, 196, 66, 15);
		
		Label lblScore9 = new Label(shlPongHighscores, SWT.NONE);
		for (Score score : scores)
		{
			if (score.getRank() == 9)
			{
				lblScore9.setText("" + score.getScore());
			}
		}
		lblScore9.setBounds(346, 217, 66, 15);
		
		Label lblScore10 = new Label(shlPongHighscores, SWT.NONE);
		for (Score score : scores)
		{
			if (score.getRank() == 10)
			{
				lblScore10.setText("" + score.getScore());
			}
		}
		lblScore10.setBounds(346, 238, 66, 15);

	}
}
