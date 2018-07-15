import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class JB51 {

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
	       
		WebClient webclient = new WebClient();  

	    // ����������һ�²�����css��javaScript,���������ܼ򵥣��ǲ���  
	    webclient.getOptions().setCssEnabled(false);  
	    webclient.getOptions().setJavaScriptEnabled(false);  

	    // ���ĵ�һ���£�ȥ�õ������ҳ��ֻ��Ҫ����getPage�����������  
	    HtmlPage htmlpage = webclient.getPage("https://www.jb51.net/article/1.htm");  

	    // �������ֵõ�һ�������鿴���������ҳ��Դ������Է��ֱ������ֽС�f��  
	   
	    /*List<HtmlElement> liList=(List<HtmlElement>) htmlpage.getByXPath("//ul[@class='list-block aboutus']/li");
	   
	    for(int i=0;i<liList.size();i++){
	    String result = liList.get(i).asText().toString();
	    System.out.println(result);  
	    }
	    */
	    List<HtmlElement> keywordList=(List<HtmlElement>) htmlpage.getByXPath("//meta[@name='keywords']");
	    String keywords = keywordList.get(0).getAttribute("content");
	    List<HtmlElement> descList=(List<HtmlElement>) htmlpage.getByXPath("//meta[@name='description']");
	    String description = descList.get(0).getAttribute("content");
	    
	    List<HtmlElement> titleList = (List<HtmlElement>) htmlpage.getByXPath("//div[@class='title']/h1");//$(".title h1").text();
	    String title = titleList.get(0).asText();
	    
	    HtmlElement he = (HtmlElement) htmlpage.getElementById("content");
	    List<HtmlElement> jb51ewmList = (List<HtmlElement>) he.getByXPath("//div[@class='jb51ewm']");//$(".title h1").text();
	    if(jb51ewmList!=null&&jb51ewmList.size()>0) {
	    	
	    	jb51ewmList.get(0).remove();
	    }
       
       
	    List<HtmlElement> ps = he.getElementsByTagName("p");
	    if(ps.size()>0) {
	    	
	    	for(int i=0;i<ps.size();i++) {
	    		
	    		if(ps.get(i).asText().contains("ԭ������")) {
	    			
	    			ps.get(i).remove();
	    		}
	    	}
	    }
	    
	    String content = he.asXml();
		
	    
	   String html = "<html><head><meta charset='utf-8'><meta name='viewport' content='width=device-width, initial-scale=1' /><meta http-equiv='Cache-Control' content='no-siteapp' /><meta http-equiv='Cache-Control' content='no-transform' /><title>"+title+"</title><meta name='keywords' content='"+keywords+"'/><meta name='description' content='"+description+"'/><link href='css/my.css' rel='stylesheet' type='text/css' media='screen' /><script src='https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js'></script><script src='js/my.js'></script></head><body><h2>"+title+"</h2><br>" + content +"</body></html>";       
		
	   
   }
}