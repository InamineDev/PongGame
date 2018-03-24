import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class NewScore {

	protected Shell shlNewScore;
	private Text name;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
//		try {
//			NewScore window = new NewScore();
//			window.open(1,1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open(int rank, int score) {
		Display display = Display.getDefault();
		createContents(rank, score);
		shlNewScore.open();
		shlNewScore.forceFocus();
		shlNewScore.layout();
		while (!shlNewScore.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents(int rank, int score) {
		shlNewScore = new Shell();
		shlNewScore.setSize(250, 255);
		shlNewScore.setText("Pong! New Highscore");
		
		Label lblRankNum = new Label(shlNewScore, SWT.NONE);
		lblRankNum.setBounds(39, 55, 55, 15);
		lblRankNum.setText("Rank: #");
		
		Label lblRank = new Label(shlNewScore, SWT.NONE);
		lblRank.setText("" + rank);
		lblRank.setBounds(113, 55, 55, 15);
		
		name = new Text(shlNewScore, SWT.BORDER);
		name.setMessage("Enter your Name!");
		name.setBounds(103, 136, 101, 21);
		
		Label lblScoreNum = new Label(shlNewScore, SWT.NONE);
		lblScoreNum.setText("Score: #");
		lblScoreNum.setBounds(39, 87, 55, 15);
		
		Label lblScore = new Label(shlNewScore, SWT.NONE);
		lblScore.setText("" + score);
		lblScore.setBounds(113, 87, 55, 15);
		
		Label lblName = new Label(shlNewScore, SWT.NONE);
		lblName.setText("Name:");
		lblName.setBounds(39, 139, 55, 15);
		
		Button btnSubmit = new Button(shlNewScore, SWT.NONE);
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				PrintWriter writer = null;
				try {
					writer = new PrintWriter("scores.txt");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				writer.print(rank + ":" + name.getText() + ":" + score);
				writer.close();
				shlNewScore.close();
				Highscore high = new Highscore();
				high.open();
			}
		});
		btnSubmit.setBounds(76, 181, 75, 25);
		btnSubmit.setText("Submit");

	}
}
