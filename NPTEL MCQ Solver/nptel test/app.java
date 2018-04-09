import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class app extends Applet implements ActionListener {
	Button b;
	static String email;
	static String passi;
	static int score;
	public Label Title, progress, sc, report;

	public void init() {
		BoxLayout mainLayout = new BoxLayout(this, BoxLayout.Y_AXIS); // Create
																		// vertical
																		// layout
																		// for
																		// the
																		// applet
		super.setLayout(mainLayout);
		Panel firstRow = new Panel();
		Panel secondRow = new Panel();
		Panel thirdrow = new Panel();
		Panel four = new Panel();
		Title = new Label("NPTEL QUIZ");
		// add(Title);
		firstRow.add(Title);

		b = new Button("START QUIZ");
		// add(b);
		secondRow.add(b);
		progress = new Label("\nProgress: 0/0");
		thirdrow.add(progress);
		// add(progress);
		sc = new Label("\nScore: 0%");
		// add(sc);
		thirdrow.add(sc);
		report = new Label("LOG: It is working good");
		// four.add(report);
		// add(report);
		b.addActionListener(this);
		super.add(firstRow);
		super.add(secondRow);
		super.add(thirdrow);
		super.add(four);
	}

	public void paint(Graphics g) {
		// update(g);
		/*
		 * g.drawString("NPTEL Quiz", 80, 20);
		 * g.drawString("Progress: "+0+"/"+0,80,50);
		 * g.drawString("Score: 0%",450,50);
		 */

	}

	public void actionPerformed(ActionEvent evt) {
		b.setLabel("Started");

		email = "********@gmail.com";
		passi = "******";

		try {
			System.setProperty("webdriver.chrome.driver", "c:/chromedriver.exe");
			WebDriver d = new ChromeDriver();
			d.get("https://onlinecourses.nptel.ac.in");

			d.findElement(By.id("login-div")).click();
			d.findElement(By.id("Email")).sendKeys(email);
			d.findElement(By.id("Passwd")).sendKeys(passi);
			d.findElement(By.id("signIn")).click();
			// d.get("https://onlinecourses.nptel.ac.in/noc15_cs07");
			d.get("https://onlinecourses.nptel.ac.in/noc15_cs07/assessment?name=151");
			/*
			 * // To Find Initial Score List<WebElement>
			 * but=d.findElements(By.tagName
			 * ("faqDiv"));//d.findElements(By.xpath
			 * ("//button[@class='gcb-button qt-check-answer-button']")); //
			 * WebElement but=d.findElement(By.xpath(
			 * "//div[@id='gcb-main-body']//div[@class='qt-assessment-button-bar']//div[@class='qt-grade-assessment']"
			 * ));//button[@class='gcb-button qt-check-answer-button']"));
			 * //By.xpath("//div[@class='page']//div[@id='_loginButton']")
			 * //WebElement button=but.findElement(By.xpath(
			 * "//button[@class='gcb-button qt-check-answer-button']"));
			 * System.out.println("Element found "+but.size()); //but.click();
			 * System.out.println("Element found");
			 */
			/*
			 * Long time=System.currentTimeMillis();
			 * while(System.currentTimeMillis()<(time+10000)) {
			 * 
			 * }
			 */
			/*
			 * report.setText("LOG: Extracting initial score"); WebElement
			 * text=d.findElement(By.className("gcb-top-content")); String
			 * s=text.getText().toString(); int k; for( k=0;k<s.length();k++) {
			 * if(s.charAt(k)=='%') { break; } } //
			 * report.setText(report.getText
			 * ().concat("\nscore="+text.getText()+"   % is at"
			 * +k+" "+s.charAt(70)));
			 * System.out.println("score="+text.getText()+
			 * "   % is at"+k+" "+s.charAt(70)); int t=k;
			 * while(s.charAt(t)!=' ') { t--;
			 * 
			 * } //char[] scor=new char[3]; String sco = ""; t++; int p=0;
			 * while(s.charAt(t)!='.') { System.out.print(s.charAt(t));
			 * sco=sco.concat(s.charAt(t)+""); // scor[p]=s.charAt(t); p++; t++;
			 * } //score=Integer.parseInt(scor.toString()); //
			 * report.setText(report.getText().concat("\nScore is "+sco));
			 * System.out.println("Score is "+sco); score=Integer.parseInt(sco);
			 * report.setText("LOG: Initial score is "+score); //Come back
			 */
			// d.get("https://onlinecourses.nptel.ac.in/noc15_cs07/assessment?name=151");
			// List all div's
			score = 0;
			List<WebElement> elementsRoot = d.findElements(By
					.xpath("//li[@class='qt-question-item']"));
			// int c=0;
			int count = 16;// elementsRoot.size();
			report.setText("LOG: Total number of questions is: " + count);
			System.out.println("Number of questions is: " + count);

			for (int i = 0; i < count; i++) {
				System.out.println("Question is " + (i + 1));
				List<WebElement> elementsRot = d.findElements(By
						.xpath("//li[@class='qt-question-item']"));
				System.out.println("New total questions " + elementsRot.size());
				progress.setText("Progress: " + (i + 1) + "/" + count);
				report.setText("LOG: Solving question " + (i + 1));
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
						report.setText("LOG: Checking option " + (c + 1)
								+ " for question " + (i + 1));

						List<WebElement> elementsRt = d.findElements(By
								.xpath("li[@class='qt-question-item']"));

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
						List<WebElement> butt = d
								.findElements(By
										.xpath("//button[@class='gcb-button qt-check-answer-button']"));
						// WebElement
						// but=d.findElement(By.xpath("//div[@class='qt-assessment-button-bar']//div[@class='qt-grade-assessment']"));//button[@class='gcb-button
						// qt-check-answer-button']"));
						// By.xpath("//div[@class='page']//div[@id='_loginButton']")
						// WebElement
						// button=but.findElement(By.xpath("//button[@class='gcb-button qt-check-answer-button']"));
						butt.get(1).click();

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
							sc.setText("Score: " + score + "%");
							report.setText("LOG: Found solution for Question"
									+ (i + 1));
							d.get("https://onlinecourses.nptel.ac.in/noc15_cs07/assessment?name=151");

							break;
						}
						score = Integer.parseInt(scoo);
						sc.setText("Score: " + score + "%");
						System.out.println("New Score is " + score);
						d.get("https://onlinecourses.nptel.ac.in/noc15_cs07/assessment?name=151");

					}

				} catch (Exception eq) {
					System.out.println("failure");
				}
				// c++;
			}
			// System.out.println("Number of questions: ");

			/*
			 * for(int i=0;i<count;i++) { elementsRoot.get(i).getAttribute(arg0)
			 * }
			 */
			report.setText("LOG: Quiz successfullly completed! Final score is "
					+ score);
			// System.out.println(d.getTitle());
			Thread.sleep(10000);
			d.close();
		} catch (Exception e) {
			System.out.println("err" + e.getMessage());
		}

	}

}
