import java.util.HashMap;
import java.util.Map;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class LinkShortener {
    private static Map<String, String> urlMap = new HashMap<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print("Enter a URL to shorten (or 'exit' to quit): ");
            String input = scanner.nextLine();
            
            if ("exit".equalsIgnoreCase(input)) {
                break;
            } else {
                String shortLink = shortenURL(input);
                System.out.println("Shortened URL: " + shortLink);
                urlMap.put(shortLink, input);
            }
        }
        
        scanner.close();
    }
    
    public static String shortenURL(String url) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(url.getBytes());
            StringBuilder sb = new StringBuilder();
            
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            
            return sb.toString().substring(0, 8); // You can adjust the length of the short link as needed.
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
