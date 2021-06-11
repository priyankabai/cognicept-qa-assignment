import org.openqa.selenium.By;		
import org.openqa.selenium.WebDriver;		
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;

public class cognicepts {				
    public static void main(String[] args) {									

    	System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver.exe");					
        WebDriver driver = new ChromeDriver();	
        driver.get("https://dev.cognicept.systems");	
        driver.manage().window().maximize();
        load();
        
        //Signup
        driver.findElement(By.xpath("//button[@type='button']")).click();
        load();
        driver.findElement(By.name("email")).sendKeys(input.mailid);
        driver.findElement(By.name("first_name")).sendKeys(input.firstName);
        driver.findElement(By.name("last_name")).sendKeys(input.lastName);
        driver.findElement(By.name("phone_number")).sendKeys(input.contact);
        driver.findElement(By.name("organization_name")).sendKeys(input.company);
        driver.findElement(By.name("password")).sendKeys(input.password);
        driver.findElement(By.xpath("//button[@type=\'button\']")).click();
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        By toaster = By.cssSelector(".notification-message");
        try {
        wait.until(ExpectedConditions.visibilityOfElementLocated(toaster));
        if(driver.findElement(toaster).isDisplayed())
        {
       	String toast=driver.findElement(By.xpath("//h4[@class='title']")).getText();        
       	String message=driver.findElement(By.xpath("//div[@class='message']")).getText();  
       	if(toast.equals("Success"))
       	{
       		System.out.println("Test case passed : Registeration "+toast+"\n"+message);
       	}
       	else
       	{
       		System.out.println("Test case failed : Registeration "+toast+"\n"+message);
       	}
        }
        }
        catch (TimeoutException ex) {
        	System.out.println("Invalid credentials");
            throw ex;
        }
        
        
        //Redirecting to Login page
        load();
        String expectedUrl = "https://dev.cognicept.systems/login";
        String actualUrl = driver.getCurrentUrl();
        if(expectedUrl.equals(actualUrl))
        {
            System.out.println("Redirecting to Login page is successfull : "+actualUrl);
        }else
        {
            System.out.println("Not redirected to Login page");
        }
        
        //Login
        driver.findElement(By.name("username")).sendKeys(input.mailid);
        driver.findElement(By.name("password")).sendKeys(input.password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        load();
      
        //Verifying Successful login
        String loginUrl = driver.getCurrentUrl();
        if(loginUrl.startsWith("https://dev.cognicept.systems/robotops"))
        {
            System.out.println("Login successfull : ");
        }else
        {
            System.out.println("Login failed");
        }

        //Navigating to Robots page
        driver.findElement(By.linkText("Robots")).click();
        load();
        
        //Adding Robot
        driver.findElement(By.xpath("//*[text()='New Robot']")).click();
        driver.findElement(By.xpath("(//input[@type='string'])[8]")).sendKeys("Sample Robot");
        WebElement model = driver.findElement(By.xpath("//*[text()='Select Model']"));
        model.click();
        driver.findElement(By.xpath("//*[text()='Crystal']")).click();
        load();
        driver.findElement(By.xpath("(//*[text()='Add Robot'])[2]")).click();
        System.out.println("Added Robot");
        
        //Deleting Robot
        load();
        driver.findElement(By.xpath("//div[@id='robot-list-action-btns']/button[3]")).click();  
        driver.findElement(By.cssSelector(".cg-btn-danger")).click();
        System.out.println("Deleted Robot");
        
        //Logging out
        load();
        driver.findElement(By.cssSelector(".btn.btn-danger.btn-lg")).click();
        System.out.println("Logged out");
    }
    public static void load(){
        try
        {
        	System.out.println("Loading");
        	Thread.sleep(5000);
        }
        catch(InterruptedException ie)
        {
        }
      }
}
