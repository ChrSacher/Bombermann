/**
 * Einfacher Logger dem man aus und an stellen kann
 * 
 * @author Christian Sacher
 * @version (a version number or a date)
 */
public class Logger  
{
    // instance variables - replace the example below with your own
    private static boolean isLoggerEnabled = true;
    
    public void enableLogging()
    {
        isLoggerEnabled = true;
    }
    
    public  void disabledLogging()
    {
        isLoggerEnabled = false;
    }
    
    public void logError(String errorMessage)
    {
        if(isLoggerEnabled) System.out.println("Error: " + errorMessage);
    }
    
    public void logWarning(String errorMessage)
    {
        if(isLoggerEnabled)System.out.println("Warning: " + errorMessage);
    }
    
     public void log(String errorMessage)
    {
       if(isLoggerEnabled) System.out.println(errorMessage);
    }
}
