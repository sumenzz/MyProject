import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.json.*;


public class JsonParser {

public void jsonArrayToSet(JSONArray jAry, Set<String> result, String targetKey, String subArrayKey, boolean includeNode){
    try {
        for (int i = 0; i < jAry.length(); i++) {
            JSONObject jObj = jAry.getJSONObject(i);
            boolean hasSubArray = false;
            JSONArray subArray = null;
            if(jObj.has(subArrayKey)){
                Object possibleSubArray = jObj.get(subArrayKey);
                if(possibleSubArray instanceof JSONArray){
                    hasSubArray = true;
                    subArray = (JSONArray) possibleSubArray;
                }
            }
            if(hasSubArray){
                if(includeNode){
                    result.add(jObj.getString(targetKey));
                }
                jsonArrayToSet(subArray, result, targetKey, subArrayKey, includeNode);
            } else {
                result.add(jObj.getString(targetKey));
            }
        }
    } catch (JSONException e){
        e.printStackTrace();
    }
    
    
}
public String getFileContent () throws FileNotFoundException, IOException 
{
	try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\smaibam\\git\\MyProject\\MyProject\\file\\input.txt"))) {
	    StringBuilder sb = new StringBuilder();
	    String line = br.readLine();

	    while (line != null) {
	        sb.append(line);
	        sb.append(System.lineSeparator());
	        line = br.readLine();
	    }
	    //System.out.print(sb.toString());//
	    return sb.toString();
	    
	}
	
}

public static void main( String[] args ) throws FileNotFoundException, IOException, JSONException  {
	
	JsonParser test = new JsonParser();
	Set<String> result = new HashSet<String>();

	JSONObject jsonObj = new JSONObject(test.getFileContent());
	JSONArray jsonArr = jsonObj.getJSONArray("members");
	test.jsonArrayToSet(jsonArr, result, "firstName", "familyMembers", false);
	System.out.println(result);
}
}
	





	
	

