import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainWindow {

	protected Shell shlPong;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
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
		shlPong.open();
		shlPong.forceFocus();
		shlPong.layout();
		while (!shlPong.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlPong = new Shell();
		shlPong.setSize(400, 300);
		shlPong.setText("Pong!");
		
		Button btnSingle = new Button(shlPong, SWT.NONE);
		btnSingle.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlPong.close();
				SinglePlayer sin = new SinglePlayer();
				sin.open();
			}
		});
		btnSingle.setBounds(0, 0, 190, 170);
		btnSingle.setText("Single Player");
		
		Button btnMulti = new Button(shlPong, SWT.NONE);
		btnMulti.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewScore nwsc = new NewScore();
				nwsc.open(1, 435);
			}
		});
		btnMulti.setText("Multiplayer");
		btnMulti.setBounds(196, 0, 188, 170);
		
		Button btnHigh = new Button(shlPong, SWT.NONE);
		btnHigh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Highscore high = new Highscore();
				shlPong.close();
				high.open();
			}
		});
		btnHigh.setBounds(0, 175, 384, 86);
		btnHigh.setText("High Scores");

	}
}
