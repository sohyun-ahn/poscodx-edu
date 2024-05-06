package chat.gui;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;

import chat.ChatClient;

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;
	private PrintWriter printWriter;
	private Socket socket;

	public ChatWindow(String name, Socket socket) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
		this.socket = socket;
		try {
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
		} catch (UnsupportedEncodingException e) {
			ChatClient.log( "UnsupportedEncodingException: " + e);	
		} catch (IOException e) {
			ChatClient.log( "error: " + e);	
		} 
	}

	public void show() {
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.DARK_GRAY);
		buttonSend.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent actionEvent ) {
				sendMessage();
			}
		});

		// Textfield
		// java textfield 한글처리가 좀 불편함 (밑에 받침이 있는 거 옮길때)=> 화살표로 커서 움직이기
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter() {
			// keyListener 인터페이스를 굳이 다른 클래스 파일로 만들지말고
			// => 다른곳에서는 안쓰니깐 그냥 여기다가 anonymous 클래스로 바로 구현
			// interface
			@Override
			public void keyPressed(KeyEvent e) {
				// textfield에서 enter키를 누르면, 메시지 보내지기
				char keyCode = e.getKeyChar(); // virtual key로 상수화로 매핑을 시켜진 것
				if(keyCode == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		}); 

		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);   // 대화창이니깐 수정 금지
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			// 닫힐때 
			public void windowClosing(WindowEvent e) {
				finish();  
			}
		});
		
		frame.setVisible(true);
		frame.pack(); // 위의 것들이 윈도우에 올라감.
		
		// ChatClientThread 생성하기
		new ChatClientThread(socket).start();
	}
	
	private void sendMessage() {
		String message = textField.getText();

		if(message != null) {
			printWriter.println( "message:" + message );
		}

		textField.setText(""); // 보내고 나서 비우기
		textField.requestFocus(); // 다시 focus를 주기
	}
	
	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}

	private void finish() {
		// exit java application
		 System.exit(0);
	}

	// thread를 외부에 만들지말고 내부에 만들기
	// 내부에 만들어야하는 이유는 error같은 것들 textarea에 표시하려고
	private class ChatClientThread extends Thread{
		
		private Socket socket;
		private BufferedReader bufferedReader;
		
		public ChatClientThread(Socket socket){
			this.socket = socket;
		}
		
		@Override
		public void run() {
			try {
				while(true) {
					bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
					String message = bufferedReader.readLine();
					updateTextArea(message);	
				
					if(message == null) {
						updateTextArea("📢 서버가 종료되었습니다. 3초 후에 창이 닫힙니다.");
						ChatClient.log( "서버가 종료되었습니다." );
						break;
					}
				}
			} catch (SocketException e) {	
				 ChatClient.log( "Socket Exception: " + e);	
			} catch (UnsupportedEncodingException e) {
				ChatClient.log( "error: " + e);
			} catch (IOException e) {
				ChatClient.log( "error: " + e);
			} finally {
				try {
					if(socket != null && !socket.isClosed()) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally{
						finish();
					}
				}
			}
		}
	}
}
