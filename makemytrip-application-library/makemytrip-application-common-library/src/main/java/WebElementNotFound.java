public class WebElementNotFound extends Exception
{
	private static final long serialVersionUID = 3461899582505930473L;
	private String message = null;
 
    public WebElementNotFound() {
        super();
    }
 
    public WebElementNotFound(String message) {
        super(message);
        this.message = message;
    }
 
    public WebElementNotFound(Throwable cause) {
        super(cause);
    }
 
    @Override
    public String toString() {
        return message;
    }
 
    @Override
    public String getMessage() {
        return message;
    }
}