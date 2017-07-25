package SnapShotUtility;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;

public class SnapshotUtilty {

    static Random r= new Random(System.currentTimeMillis());
	static int random = r.nextInt(1000);
	static int count=0;
	

	public static void snapShots(WebDriver driver,String snapname) throws IOException {
		try {
			Thread.sleep(1000);
			TakesScreenshot Sshot = (TakesScreenshot) driver;
			File source = Sshot.getScreenshotAs(OutputType.FILE);
			File dest=new File("C:\\Users\\Hituj Velukar\\Desktop\\Data\\"+snapname+random+".png");

			FileUtils.copyFile(source, dest);
			
			System.out.println("CAPTURED"+ count);
			count=count+1;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(" Snap Exeption" + e.getMessage());
		}

	}

}
