import java.lang.reflect.Array;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class BankInput {

	protected Shell shell;
	private Text text;
	 static bank[] a=new bank[1000];
	int i=0;
	private Text text_1;
	private Button btnNewButton_1;
	private Button btnAgian;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		 
		try {
			bankinput window = new bankinput();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int sum =0;
		for(int i=0;i<2*bank.getuser();i++) {
			if(a[i].getdeparttime()<a[i].getintime()) {
				System.out.println("Processing an Deaprture event at time :"+a[i].getdeparttime());
				System.out.println("Processing an Arrival event at time :"+a[i].getintime());
			}
			else if(a[i].getdeparttime()>=a[i].getintime()) {
				System.out.println("Processing an Arrival event at time :"+a[i].getintime());
				System.out.println("Processing an Deaprture event at time :"+a[i].getdeparttime());
			}
			
			sum=sum+a[i].gettranstime();
		}
		System.out.println("The total no of people processed : "+bank.getuser());
		System.out.println("Average amount of time wasting : "+(sum/bank.getuser()));
	}


	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(37, 107, 92, 21);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(10, 46, 131, 25);
		btnNewButton.setText("Enter Arrival time:");
		
		Button btnEnter = new Button(shell, SWT.NONE);
		btnEnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				a[i]=new bank();
				String val=text.getText();
				String val1=text_1.getText();
					a[i].setintime(Integer.parseInt(val));
					bank.setuser();
					a[i].settranstime(Integer.parseInt(val1));
					a[i].calculate();
					i++;
				
			}
			
		});
		btnEnter.setBounds(132, 169, 75, 25);
		btnEnter.setText("Enter.");
		
		Button btnEnd = new Button(shell, SWT.NONE);
		btnEnd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				shell.close();
			}
		});
		btnEnd.setBounds(271, 236, 75, 25);
		btnEnd.setText("END.");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(217, 107, 103, 21);
		
		btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setBounds(197, 46, 134, 25);
		btnNewButton_1.setText("Enter Transaction Time:");
		
		btnAgian = new Button(shell, SWT.NONE);
		btnAgian.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				
				text_1.setText("");
				text.setText("");
			
			}
		});
		
		btnAgian.setBounds(10, 236, 75, 25);
		btnAgian.setText("Again?");

	}
}
