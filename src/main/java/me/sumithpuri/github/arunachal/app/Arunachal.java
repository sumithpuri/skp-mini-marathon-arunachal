package me.sumithpuri.github.arunachal.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;

import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import me.sumithpuri.github.arunachal.spring.dynamic.sample.AccountingBean;

/**
 * MIT License
 *
 * Copyright (c) 2018-19,	Sumith Kumar Puri

 * GitHub URL 			https://github.com/sumithpuri
 * Code Sample			Brainbench Spring 2.5 Certification [+ Spring In Action Samples]
 * Sample Topic			Core Spring (Dynamic Languages)
 * Certificate URL		https://goo.gl/X321kd
 * Package Prefix 		me.sumithpuri.github.arunachal
 * Project Codename		arunachal
 * Contact E-Mail		code@sumithpuri.me
 * Contact WhatsApp		+91 9591497974
 *
 *
 * Permission is hereby  granted,  free  of  charge, to  any person  obtaining a  copy of  this  software and associated 
 * documentation files (the "Software"), to deal in the  Software without  restriction, including without limitation the 
 * rights to use, copy, modify, merge, publish, distribute, sub-license and/or sell copies of the Software and to permit 
 * persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in  all copies or substantial portions of the 
 * Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS  OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE 
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES  OR  OTHER  LIABILITY, WHETHER IN AN ACTION  OF  CONTRACT, TORT OR 
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
public class Arunachal {

	AccountingBean accountingBean;
	static boolean update = false;
	private static String scriptFile = Paths.get("").toAbsolutePath().toString() + File.separatorChar + "target"
			+ File.separatorChar + "classes" + File.separatorChar + "DynamicAccount.rb";
	
	
	Arunachal(AccountingBean bean) {
		this.accountingBean = bean;
	}
	
	public void displayAndPatch() {
		
		try {
			if (!update) {
				
				// demonstration of a dynamic patch or update to an enterprise application
				// can also be used to build dynamic plugin, extensions based applications
				
				// XXX Step 01: Read Dynamic Updates from FileSystem 
				// (In Real-World, Possibly from a Network Stream)
				System.out.println("Account Information - Original File");
				System.out.println("===================================");
				this.accountingBean.displayAccountInformation();
				System.out.print("Receiving Dynamic Updates from Server...");
				File file = new File(scriptFile);
				
				FileReader reader = new FileReader(file);				
				char cbuf[] = new char[100000];
				reader.read(cbuf, 0, (int)file.length());
				String line = new String(cbuf);
				
				line=line.replaceAll("300", "400");
				line=line.replaceAll("499", "299");
				line=line.replaceAll("M/S. Spring in Action", "M/S. Spring in Action(Offer)");
				line=line.trim();
	
				// XXX Step 02: Delete the Read File (For Convenience, Deleting the File and Re-Creating)
				// (In Real-World, there may be an Intelligent File Update/Patch Mechanism)
				reader.close();
				file.delete();
				
				
				// XXX Step 02: Delete the Read File (For Convenience, Deleting the File and Re-Creating)
				file = new File(scriptFile);
				file.createNewFile();
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				writer.write(line);
				writer.flush();
				writer.close();
				System.out.println(" Patched Accounting System Ver 1.3");
				System.out.println("----------------------------------");
				System.out.println("Spring-Script Magic Show");
				System.out.println("------------------------");
				
				Thread.currentThread().sleep(1000);
				update = true;
				
				
				// XXX Step 03: Re-Read the File Into a Character Buffer and Print on Console
				file = new File(scriptFile);
				reader = new FileReader(file);
				
				cbuf = new char[100000];
				reader.read(cbuf, 0, (int)file.length());
				line = new String(cbuf);
				
				System.out.println("********** File Contents After Patch/Update **********");
				System.out.println("######################################################");
				System.out.println(line);
			} else {
				
				System.out.println("Account Information - Patched/Updated");
				System.out.println("=====================================");
				this.accountingBean.displayAccountInformation();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}		

	public static void main(String args[]) {

		System.out.println("Copyright (c) 2018-19,	Sumith Kumar Puri");
		System.out.println();
		System.out.println("Project Codename      Arunachal");
		System.out.println("Project Description   Core Spring (Dynamic Languages)");
		System.out.println("Certification         Brainbench Spring 2.5 Certification");
		System.out.println("Certificate URL	      https://goo.gl/X321kd");
		System.out.println("[Developer Notes]     [01] Use Java Version 9.0+ Compiler");
		System.out.println();
		
		AbstractRefreshableApplicationContext ctx = new ClassPathXmlApplicationContext("accounting.xml");
		Arunachal accountingMain = new Arunachal((AccountingBean) ctx.getBean("accountingbean"));
		
		accountingMain.displayAndPatch();
		
		ctx.refresh();
		
		for(int i=0;i<1000000;i++); //poll
		for(int i=0;i<1000000;i++); //poll
		for(int i=0;i<1000000;i++); //poll
		
		accountingMain = new Arunachal((AccountingBean) ctx.getBean("accountingbean"));
		
		accountingMain.displayAndPatch();
	}
}
