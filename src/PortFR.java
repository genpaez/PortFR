import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.StringReader;
import java.util.Properties;
import java.util.stream.Stream;

import com.jcraft.jsch.*;


public class PortFR {   //// PorForwarding y ejecución vía PipedInput. Pendiente recuperar Output
	                    //// hay que agregar user info
	
	private static String username = "";
	private static String passwordA = "", passwordB = "";
	private static String hostA = "10.30.4.165", hostB = "10.250.2.152";
	private static JSch jSch = new JSch();
	private static int forwardedPort;
	private static UserInterface UI;
	private static Session sessionA;
	private static Session sessionB;
	private static BufferedReader br;
	private static Channel channel;


	 
	
	 public static void sesionA(){
		 
		 UI = new UserInterface();
		 
		 try {
			sessionA = jSch.getSession(username, hostA, 22);  
			Properties config = new Properties(); 
	        config.put("StrictHostKeyChecking", "no");
	        sessionA.setConfig(config);
	        sessionA.setPassword(passwordA);
	        forwardedPort = 2222;
        	sessionA.setPortForwardingL(forwardedPort, hostB, 22);	
	        sessionA.connect();
	        sessionA.openChannel("direct-tcpip"); //**********************************************
	        
	        
	        if(sessionA.isConnected()) {
	        	System.out.println("Connected host A!");
	        		
	        }
			
		} catch (JSchException e) {
			e.printStackTrace();
		}
	 }
	 
	 public static void sesionB() throws IOException, InterruptedException {
		

			try {
				sessionB = jSch.getSession(username, "localhost", forwardedPort);

			Properties config = new Properties(); 
	        config.put("StrictHostKeyChecking", "no");
	        sessionB.setConfig(config);
	        sessionB.setPassword(passwordB);
			sessionB.connect();
				
		      if(sessionB.isConnected()) {
		         System.out.println("Connected host B!"); 
		         
			   /*   channel = sessionB.openChannel("shell");
			      PipedInputStream en = new PipedInputStream();
			      pin = new PipedOutputStream((PipedInputStream) en);
			      br = new BufferedReader(new InputStreamReader(en = (PipedInputStream) channel.getInputStream()));
			      System.out.println("Channel connected!");
			      pin.write("show port\r\n".getBytes());*/
			//      enviarComando(sessionB);
			    //******************************************************************************************************      
			      


			//******************************************************************************************************
		            
			  }
		    } catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	 }
	 
	 

	
	static void enviarComando() throws IOException, JSchException, InterruptedException{
		// TODO Auto-generated method stub
		
		  Channel channel = sessionB.openChannel("shell");
	//	  PipedInputStream en = (PipedInputStream) channel.getInputStream();
	//	  PipedOutputStream pin = new PipedOutputStream((PipedInputStream) channel.getInputStream());
	//	  BufferedReader br = new BufferedReader(new InputStreamReader(channel.getInputStream()));
		  
		  
		  InputStream in = channel.getInputStream();
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  OutputStream out = channel.getOutputStream();
		  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
		
		  channel.connect();
		  
		  if(channel.isConnected()) { 

		  String myCommand = ("show service id 500151940 interface 10.20.30.1 \n \n");
		  bw.write(myCommand);
		  Thread.sleep(100);
		  String myCommand2 = ("show router 500151940 bgp summary neighbor 10.20.30.2 \n \n");
		  bw.write(myCommand2);
		  Thread.sleep(100);
		  String myCommand3 = ("show port description 1/2/1  \n \n");
		  bw.write(myCommand3);
		  Thread.sleep(100);
		  String myCommand4 = ("ping 10.20.30.2 router 500151940 rapid count 5 \n \n");
		  bw.write(myCommand4);
		  Thread.sleep(100);
		  String myCommand5 = ("show port 1/2/1 detail \n \n");
		  bw.write(myCommand5);
		  String myCommand6 = ("x \n \n");
		  bw.write(myCommand6);
		  bw.flush();
		/*  String myCommand2 = ("ping 10.20.30.2 router 500151940 \n \n");
		  bw.write(myCommand2);
		  bw.flush();*/
		  }
		  
	      String received = null;
	      StringBuilder sb = new StringBuilder(); 
	      
	      if(br.readLine()!=null){
	      System.out.println("tiene algo:"+br.read());
	      		}
	      else if(br.readLine()==null){
	    	  System.out.println("tiene nada");
	    	    }
		  
	      //channel.disconnect();
		  //close();   /// read on close ?
	      
	      
	  //    while((received=br.readLine())!=null) 
		  for(int x=0;x<160;x++)
	      {
	    	  received=br.readLine();
	    //	  System.out.println("recibido:" +received);
	          sb.append(received+"\n");
	         
	   //       if((received=br.readLine())==null) {System.out.println("es null"); break; }
	          
	          if(received.contains("logout")) {break;}
	          
	          if (channel.isClosed()) {
	              System.out.println("exit-status: " + channel.getExitStatus());
	              break;
	          }
	          try{Thread.sleep(10);}catch(Exception ee){}
	          
	        //  UI.setRespuesta(sb.toString());
	      }
	      
	      UI.setRespuesta(sb.toString());
		
	}
	
	

	private static void close() {
	      channel.disconnect(); 
	      sessionA.disconnect();
	      sessionB.disconnect();
	      System.out.println("Desconectado!");
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		sesionA();
		sesionB();	

	}
	
		

	 

		public static void verBGP() {
			
		/*	try {
				myCommand = "show router 500151940 bgp summary neighbor 10.20.30.2 \n";
				pin.write(myCommand.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				try {
					Thread.sleep(500);
					myCommand = "x"; // Avoid buffer tail of command response
					pin.write(myCommand.getBytes());	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
		
		public static void verInterfaz() {

	/*		try {
				
				myCommand = "show service id 500151940 interface 10.20.30.1 \n";
				pin.write(myCommand.getBytes());

			} catch (IOException e) {
				e.printStackTrace();
			}    */

		   // readChannelOutput(channel);
		}

}

