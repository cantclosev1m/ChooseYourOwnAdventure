package adventuregame;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileReader;
import java.lang.StringBuilder;
import java.util.List;  



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        File file = new File("game.json");
        FileReader reader = new FileReader(file);
        StringBuilder s = new StringBuilder();
        
        while(true)
        {
            int c = reader.read();
            if (c ==  -1) break;
            s.append((char)c);
        }
        
        String file_contents = s.toString();
        System.out.println(file_contents);

        ObjectMapper objectMapper = new ObjectMapper();

        JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, Foo.class)
        
        List<Object> arr = objectMapper.readValue(file_contents, new TypeReference<List<Object>>(){});
        Map<String, Object> data = objectMapper.readValue(file_contents, Map.class);
        
        // Accessing individual fields
      //  String name = (String) data.get("name");
      //  int age = (int) data.get("age");
      //  String city = (String) data.get("city");
         
    }
}
