package CharThree;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	private Socket s;
	private Frame f;
	private TextArea ta;
	private TextField tf;
	private PrintWriter out;

	public Client() {
		f = new Frame("레모나");
		ta = new TextArea();
		tf = new TextField();
		
		f.add(ta);
		f.add(tf, BorderLayout.SOUTH);
		
		addListener();
		f.setSize(500, 500);
		f.setVisible(true);
		
	}
	
	public void initServer() {
		try {
			s = new Socket("192.168.0.100", 7109);
			OutputStream os = s.getOutputStream();
			out = new PrintWriter(os, true);
			receiveMessage();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void receiveMessage() {
		try {
			InputStream is = s.getInputStream();
			InputStreamReader ir = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(ir);
			
			String data = br.readLine();
			
			while (data != null) {
				ta.append(data +"\n");
				data = br.readLine();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addListener() {
		f.addWindowListener(new WindowAdapter() {
	
		@Override
		public void windowClosing (WindowEvent e){
			System.exit(0);
		}
		});
		tf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String data = tf.getText();
				
				out.println(data);
				tf.setText("");
				// TODO Auto-generated method stub
				
			}
		});
	}

}
