package sumenzz.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class NetClientGet {
	
	public void postData(String Data,String urlpath) throws IOException {
		
		URL url = new URL(urlpath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
        conn.setDoOutput(true);
		conn.setRequestProperty("Accept", "text/plain");
		
		OutputStream outputStream = conn.getOutputStream();
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
	    bufferedWriter.write(Data);
	    bufferedWriter.flush();
	    
	    if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
			
		}
	    
	    BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

	    String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		conn.disconnect();
		
		
		
	}
	
	
	
	public static void main(String[] args) throws IOException, InterruptedException {

		NetClientGet testclient = 	new NetClientGet ();
		String[] myStrings = { "One", "Two", "Three" };
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		
		for (String string : myStrings) {
		
			Runnable task = new Runnable() {
		    public void run() {
		    		 try {
						testclient.postData(string, "http://localhost:8088/send");
					} catch (IOException e) {
						e.printStackTrace();
					}		
					
				
		    }
		    
		};
		executor.execute(task);
//		System.out.println("hi");
//		testclient.postData(string, "http://localhost:8088/send");
		
		}
		
	
		executor.shutdown();
		executor.awaitTermination(3600,TimeUnit.SECONDS) ;
		
		System.out.println("bye");
		
		
		
		


}
}