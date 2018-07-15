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
	static HtmlPage htmlpage;

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, AWTException, InterruptedException {
	      
		//http://ping.chinaz.com/www.jb51.net/article/2.htm  ����������51jb�����ӳ���������Ϊѡ������
		WebClient webclient = new WebClient(); 
		 robot = new Robot(); 

	    // ����������һ�²�����css��javaScript,���������ܼ򵥣��ǲ���  
	    webclient.getOptions().setCssEnabled(false);  
	    webclient.getOptions().setJavaScriptEnabled(false);  
	    webclient.getOptions().setDownloadImages(false);

	    // ���ĵ�һ���£�ȥ�õ������ҳ��ֻ��Ҫ����getPage�����������  
	    
	    String address = InetAddress.getLocalHost().getHostAddress().toString();
	    String preAddress = "";
	    System.out.println(address);
	    
	    
	    long pre = System.currentTimeMillis();
	    int delayNum = 0;
	    
	    int errornum = 0;
	    for(int i=1091;i<140000;i++) {
	    	
	    try {
	    	HtmlPage page = webclient.getPage("https://www.jb51.net/article/"+i+".htm");  
		   
		    new MyThread(page,i).start();
		    
		    long now =System.currentTimeMillis();
		    
		    
		    address = InetAddress.getLocalHost().getHostAddress().toString();
		    System.out.println(i+"-"+address+"-"+now+"");
		    if(now-pre>1000) {
		    	
		    	 System.out.println("delay:"+now+"");
		    	 delayNum++;
		    }
		   
		   
		    if((now-pre)>20000) {
		    	
		    	System.out.println("long delay");
		    }
		    
		    //ÿ50������ipһ�Σ�
		    if(i%100==0||delayNum>10||address.contains("192.168.2")||(now-pre)>20000) {		    
		    	
		    	 
		    	 robot.keyPress(KeyEvent.VK_F6);
		    	 Thread.sleep(8000);
		    	 while(address.contains("192.168.2")) {//�жϵ�ַ�Ƿ�����ɹ���������ɹ������ַ���Ǳ��ص�ַ��������ǰ�ĵ�ַ�͸���ip
		    		 
		    		 
		    		 //robot.delay(3000);
			    	 robot.keyPress(KeyEvent.VK_F6);
			    	 address = InetAddress.getLocalHost().getHostAddress().toString();
			    	 Thread.sleep(8000);
		    	 }
		    	 
	
		    	 
		    	 delayNum = 0;
		    	
		    }
		    
		    pre = now;
	    }
	    catch(Exception e) {
	    	
	    	errornum++;
	    	
	    	if(errornum>4) {
	    		
	    		robot.keyPress(KeyEvent.VK_F6);
		    	 Thread.sleep(4000);
		    	 while(address.contains("192.168.2")) {//�жϵ�ַ�Ƿ�����ɹ���������ɹ������ַ���Ǳ��ص�ַ��������ǰ�ĵ�ַ�͸���ip
		    		 
		    		 
		    		 //robot.delay(3000);
			    	 robot.keyPress(KeyEvent.VK_F6);
			    	 address = InetAddress.getLocalHost().getHostAddress().toString();
			    	 Thread.sleep(4000);
		    	 }
		    	 
		 	    	 
		      errornum = 0;
	    	}
	    	else
	    	continue;
	    }
		    
	  }
	    
	    
	    
	    
	   
   }
}