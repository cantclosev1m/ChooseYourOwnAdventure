package adventuregame;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        String json = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}";

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> data = objectMapper.readValue(json, Map.class);

        // Accessing individual fields
        String name = (String) data.get("name");
        int age = (int) data.get("age");
        String city = (String) data.get("city");

        // Print the parsed data
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);    
    }
}
