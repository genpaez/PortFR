

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;

public class UserInterface extends JFrame implements ActionListener{
	private JTextField enviarText;
	JTextArea respuestaText;
	private JButton btnYes, pingBtn, btnClavePE, enviarBtn;

	public UserInterface() {
		setResizable(false);
		getContentPane().setLayout(null);
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pingBtn = new JButton("Ping");
		pingBtn.setBounds(271, 39, 154, 23);
		getContentPane().add(pingBtn);
		pingBtn.addActionListener(this);
		
		enviarText = new JTextField();
		enviarText.setBounds(5, 39, 256, 23);
		enviarText.setToolTipText("ingrese comando");
		getContentPane().add(enviarText);
		enviarText.setColumns(10);
		
		enviarBtn = new JButton("Ver interfaz");
		enviarBtn.setBounds(5, 5, 146, 23);
		getContentPane().add(enviarBtn);
		enviarBtn.addActionListener(this);
		
		respuestaText = new JTextArea();
		respuestaText.setBounds(5, 87, 579, 473);
		getContentPane().add(respuestaText);
		
		btnYes = new JButton("Puerto PE");
		btnYes.setBounds(161, 5, 101, 23);
		getContentPane().add(btnYes);
		btnYes.addActionListener(this);
		
		btnClavePE = new JButton("Protocolo BGP");
		btnClavePE.setBounds(269, 5, 156, 23);
		getContentPane().add(btnClavePE);
		btnClavePE.addActionListener(this);
		this.setVisible(true);
	}

	public void setRespuesta(String s) {
		System.out.println("done");
		respuestaText.setText((String) s);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnYes) {PortFR.verPuerto();}
		
		if(e.getSource()==btnClavePE) {PortFR.verBGP();}
		
		if(e.getSource()==enviarBtn) {PortFR.verInterfaz();}
		
		if(e.getSource()==pingBtn) {PortFR.enviarPing();}
	}
}
