/**
 * Einfacher Logger dem man aus und an stellen kann
 * 
 * @author Christian Sacher
 * @version 11.12.17
 */
public class Logger  
{
    // instance variables - replace the example below with your own
    private static boolean isLoggerEnabled = false;
    
    public static void enableLogging()
    {
        isLoggerEnabled = true;
    }
    
    public static void disabledLogging()
    {
        isLoggerEnabled = false;
    }
    
    public static void logError(String errorMessage)
    {
        if(isLoggerEnabled) System.out.println("Error: " + errorMessage);
    }
    
    public static void logWarning(String errorMessage)
    {
        if(isLoggerEnabled)System.out.println("Warning: " + errorMessage);
    }
    
     public static void log(String message)
    {
       if(isLoggerEnabled) System.out.println(message);
    }
}
