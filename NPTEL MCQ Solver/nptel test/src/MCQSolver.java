package src;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//import org.openqa.selenium.firefox.FirefoxDriver; 

public class MCQSolver {
	static String email;
	static String passi;
	static int score;

	public static void main(String[] args) // throws InterruptedException
	{
		email = "********@gmail.com";
		passi = "*********";

		try {
			System.setProperty("webdriver.chrome.driver", "c:/chromedriver.exe");
			WebDriver d = new ChromeDriver();
			d.get("https://onlinecourses.nptel.ac.in");

			d.findElement(By.id("login-div")).click();
			d.findElement(By.id("Email")).sendKeys(email);
			d.findElement(By.id("Passwd")).sendKeys(passi);
			d.findElement(By.id("signIn")).click();
			d.get("https://onlinecourses.nptel.ac.in/noc15_cs07");
			d.get("https://onlinecourses.nptel.ac.in/noc15_cs07/assessment?name=139");

			// To Find Initial Score

			WebElement but = d
					.findElement(By
							.xpath("//div[@class='qt-grade-assessment gcb-pull-right']//button[@class='gcb-button qt-check-answer-button']"));
			// By.xpath("//div[@class='page']//div[@id='_loginButton']")
			// WebElement
			// button=but.findElement(By.xpath("//button[@class='gcb-button qt-check-answer-button']"));
			but.click();
			/*
			 * Long time=System.currentTimeMillis();
			 * while(System.currentTimeMillis()<(time+10000)) {
			 * 
			 * }
			 */
			WebElement text = d.findElement(By.className("gcb-top-content"));
			String s = text.getText().toString();
			int k;
			for (k = 0; k < s.length(); k++) {
				if (s.charAt(k) == '%') {
					break;
				}
			}
			System.out.println("score=" + text.getText() + "   % is at" + k
					+ " " + s.charAt(70));
			int t = k;
			while (s.charAt(t) != ' ') {
				t--;

			}
			// char[] scor=new char[3];
			String sco = "";
			t++;
			int p = 0;
			while (s.charAt(t) != '.') {
				System.out.print(s.charAt(t));
				sco = sco.concat(s.charAt(t) + "");
				// scor[p]=s.charAt(t);
				p++;
				t++;
			}
			// score=Integer.parseInt(scor.toString());
			System.out.println("Score is " + sco);
			score = Integer.parseInt(sco);

			// Come back
			d.get("https://onlinecourses.nptel.ac.in/noc15_cs07/assessment?name=139");
			// List all div's
			List<WebElement> elementsRoot = d
					.findElements(By
							.xpath("//div[@class='gcb-cols gcb-question-counter-increment gcb-question-row']"));
			// int c=0;
			int count = elementsRoot.size();
			System.out.println("Number of questions is: " + count);

			for (int i = 0; i < count; i++) {
				System.out.println("Question is " + (i + 1));
				List<WebElement> elementsRot = d
						.findElements(By
								.xpath("//div[@class='gcb-cols gcb-question-counter-increment gcb-question-row']"));
				System.out.println("New total questions " + elementsRot.size());
				WebElement e = elementsRot.get(i);
				try {
					WebElement elementsRoots = e.findElement(By
							.className("qt-choices"));

					List<WebElement> elementsRootss = elementsRoots
							.findElements(By.tagName("input"));
					System.out.println("Type is "
							+ elementsRootss.get(0).getAttribute("radio"));

					System.out.println(" " + (i + 1) + "-"
							+ elementsRootss.size());
					int c;
					for (c = 0; c < elementsRootss.size(); c++) {
						System.out.println("Enetered " + (c + 1));

						List<WebElement> elementsRt = d
								.findElements(By
										.xpath("//div[@class='gcb-cols gcb-question-counter-increment gcb-question-row']"));

						WebElement ef = elementsRt.get(i);

						// WebElement
						// elementsRts=ef.findElement(By.className("qt-choices"));

						List<WebElement> elementsRtss = ef.findElements(By
								.tagName("input"));

						// check c
						System.out.println("page loaded");
						// List<WebElement> elementsRootsss =
						// e.findElements(By.tagName("input"));
						elementsRtss.get(c).click();
						// ele.click();
						System.out.println("Checkbox clicked");
						WebElement butt = d
								.findElement(By
										.xpath("//div[@class='qt-grade-assessment gcb-pull-right']//button[@class='gcb-button qt-check-answer-button']"));

						butt.click();
						// check if score increaded
						System.out.println("Button clickeed");
						WebElement textt = d.findElement(By
								.className("gcb-top-content"));

						String ss = textt.getText().toString();
						System.out.println("String is " + ss);
						int kk;
						for (kk = 0; kk < ss.length(); kk++) {
							if (ss.charAt(kk) == '%') {
								break;
							}
						}
						System.out.println("% pos retrieved at " + kk);
						int tt = kk;
						while (ss.charAt(tt) != ' ') {
							System.out.println(ss.charAt(tt) + "  " + tt);
							tt--;

						}
						// char[] scor=new char[3];
						String scoo = "";
						tt++;
						int pp = 0;
						scoo = "";
						while (ss.charAt(tt) != '.') {
							System.out.print(ss.charAt(tt) + " " + tt);
							scoo = scoo.concat(ss.charAt(tt) + "");
							// scor[p]=s.charAt(t);
							pp++;
							tt++;
						}
						// score=Integer.parseInt(scor.toString());
						System.out.println("Score is " + scoo);
						if (score < Integer.parseInt(scoo)) {
							score = Integer.parseInt(scoo);

							d.get("https://onlinecourses.nptel.ac.in/noc15_cs07/assessment?name=139");

							break;
						}
						score = Integer.parseInt(scoo);
						System.out.println("New Score is " + score);
						d.get("https://onlinecourses.nptel.ac.in/noc15_cs07/assessment?name=139");

					}

				} catch (Exception eq) {
					System.out.println("failure");
				}
				// c++;
			}
			System.out.println("Number of questions: ");

			/*
			 * for(int i=0;i<count;i++) { elementsRoot.get(i).getAttribute(arg0)
			 * }
			 */

			System.out.println(d.getTitle());
			Thread.sleep(10000);
		} catch (Exception e) {
			System.out.println("err" + e.getMessage());
		}

		// d.quit();
	}

}
