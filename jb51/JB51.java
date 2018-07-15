import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class JB51 {
	
	private static Robot robot; 

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, AWTException {
	       
		WebClient webclient = new WebClient(); 
		 robot = new Robot(); 

	    // ����������һ�²�����css��javaScript,���������ܼ򵥣��ǲ���  
	    webclient.getOptions().setCssEnabled(false);  
	    webclient.getOptions().setJavaScriptEnabled(false);  

	    // ���ĵ�һ���£�ȥ�õ������ҳ��ֻ��Ҫ����getPage�����������  
	    
	    String address = InetAddress.getLocalHost().getHostAddress().toString();
	    String preAddress = "";
	    System.out.println(address);
	    
	    
	    for(int i=0;i<140000;i++) {
	    	
	    	HtmlPage htmlpage = webclient.getPage("https://www.jb51.net/article/1.htm");  
		   
		    new MyThread(htmlpage,i).start();

		   
		    //ÿ50������ipһ�Σ�
		    if(i%50==0) {		    
		    	
		    	 
		    	 robot.keyPress(KeyEvent.VK_F6);
		    	 address = InetAddress.getLocalHost().getHostAddress().toString();
		    	 while(address.contains("192.168.2")||preAddress.contains(address)) {//�жϵ�ַ�Ƿ�����ɹ���������ɹ������ַ���Ǳ��ص�ַ��������ǰ�ĵ�ַ�͸���ip
		    		 
		    		 robot.delay(3000);
			    	 robot.keyPress(KeyEvent.VK_F6);
			    	 address = InetAddress.getLocalHost().getHostAddress().toString();
			    	 
		    	 }
		    	 
		    	 preAddress = address;
		    	
		    }
	    }
	    
	    
	    
	    
	   
   }
}