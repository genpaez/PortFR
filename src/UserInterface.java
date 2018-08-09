

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JTextField;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedOutputStream;

import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;

public class UserInterface extends JFrame implements ActionListener{
	private JTextArea respuestaText;
	private JButton btnYes;
	private JButton btnClavePE, enviarBtn, btnEjecutarPruebas;

	public UserInterface() {
		setResizable(false);
		getContentPane().setLayout(null);
		this.setSize(600, 652);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		enviarBtn = new JButton("");
		enviarBtn.setBounds(5, 5, 173, 23);
		getContentPane().add(enviarBtn);
		enviarBtn.addActionListener(this);
		
		btnYes = new JButton("");
		btnYes.setBounds(188, 5, 193, 23);
		getContentPane().add(btnYes);
		btnYes.addActionListener(this);
		
		btnClavePE = new JButton("");
		btnClavePE.setBounds(391, 5, 193, 23);
		getContentPane().add(btnClavePE);
		
		respuestaText = new JTextArea();
		respuestaText.setBackground(Color.BLACK);
		respuestaText.setForeground(Color.GREEN);
		respuestaText.setBounds(5, 102, 589, 521);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 70, 589, 553);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(respuestaText);
		
		btnEjecutarPruebas = new JButton("Ejecutar pruebas");
		btnEjecutarPruebas.setBounds(5, 36, 173, 23);
		btnEjecutarPruebas.addActionListener(this);
		getContentPane().add(btnEjecutarPruebas);
		
		
		btnClavePE.addActionListener(this);
		this.setVisible(true);
	}

	public void setRespuesta(String s) {
		System.out.println("done");
		respuestaText.setText((String) s);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource()==btnEjecutarPruebas) {
			try {
				PortFR.enviarComando();
			} catch (IOException | JSchException | InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}
