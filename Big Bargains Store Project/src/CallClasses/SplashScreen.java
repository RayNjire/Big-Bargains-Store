<<<<<<< HEAD
package CallClasses;

import BeforeLogin.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Author and License
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * @author ray njire
 */
public class SplashScreen 
{
    public static void main (String[]args)
    {
        //Open Splash Screen Interface Page
        new SplashScreenInterface().setVisible(true);
        try
        {
            //Count 1 to 100
            for (int i = 0; i <=100; i++)
            {
                //Set a 40 microsecond delay between each ++
                Thread.sleep(40);
                SplashScreenInterface.LBLPercent.setText("" + i + "%");
                SplashScreenInterface.ProgressBar.setValue(i);                        

                if (i==100)
                {
                    Thread.sleep(1000);                                
                    SplashScreenInterface.ProgressBar.setVisible(false);
                    Thread.sleep(500);

                    //Blinking Welcome
                    SplashScreenInterface.LBLWelcome.enable();
                    SplashScreenInterface.LBLWelcome.setVisible(true);
                    Thread.sleep(500);
                    SplashScreenInterface.LBLWelcome.setVisible(false);
                    Thread.sleep(500);
                    SplashScreenInterface.LBLWelcome.setVisible(true);
                    Thread.sleep(500);
                    SplashScreenInterface.LBLWelcome.setVisible(false);
                    Thread.sleep(500);
                    SplashScreenInterface.LBLWelcome.setVisible(true);

                    //700 microsecond delay before opening the next page and automatically closing the splash screen
                    Thread.sleep(700);
                    new IndexPage().setVisible(true);
                    SplashScreenInterface.LBLClose.setVisible(true);
                }
            }
        }
        
        catch (Exception e)
        {
            try
            {
                SplashScreenInterface.LBLPercent.setText("Application Loading Failed, The Program will Close Shortly...");
                Thread.sleep(700);
                System.exit(0);
                
            } 
            
            catch (InterruptedException ex)
            {
                Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
    }
    
}
=======
package CallClasses;

import BeforeLogin.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Author and License
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * @author ray njire
 */
public class SplashScreen 
{
    public static void main (String[]args)
    {
        //Open Splash Screen Interface Page
        new SplashScreenInterface().setVisible(true);
        try
        {
            //Count 1 to 100
            for (int i = 0; i <=100; i++)
            {
                //Set a 40 microsecond delay between each ++
                Thread.sleep(40);
                SplashScreenInterface.LBLPercent.setText("" + i + "%");
                SplashScreenInterface.ProgressBar.setValue(i);                        

                if (i==100)
                {
                    Thread.sleep(1000);                                
                    SplashScreenInterface.ProgressBar.setVisible(false);
                    Thread.sleep(500);

                    //Blinking Welcome
                    SplashScreenInterface.LBLWelcome.enable();
                    SplashScreenInterface.LBLWelcome.setVisible(true);
                    Thread.sleep(500);
                    SplashScreenInterface.LBLWelcome.setVisible(false);
                    Thread.sleep(500);
                    SplashScreenInterface.LBLWelcome.setVisible(true);
                    Thread.sleep(500);
                    SplashScreenInterface.LBLWelcome.setVisible(false);
                    Thread.sleep(500);
                    SplashScreenInterface.LBLWelcome.setVisible(true);

                    //700 microsecond delay before opening the next page and automatically closing the splash screen
                    Thread.sleep(700);
                    new IndexPage().setVisible(true);
                    SplashScreenInterface.LBLClose.setVisible(true);
                }
            }
        }
        
        catch (Exception e)
        {
            try
            {
                SplashScreenInterface.LBLPercent.setText("Application Loading Failed, The Program will Close Shortly...");
                Thread.sleep(700);
                System.exit(0);
                
            } 
            
            catch (InterruptedException ex)
            {
                Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
    }
    
}
>>>>>>> 428d86ff0a10b23329e084423b3d910ec968e65a
