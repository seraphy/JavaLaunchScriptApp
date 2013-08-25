package jp.seraphyware.javalaunchexample;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class SimpleJavaApp extends JFrame {

	public SimpleJavaApp() {
		try {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setTitle(getClass().getSimpleName());
			
			Container contentPane = getContentPane();
			contentPane.setLayout(new BorderLayout());
			
			JTextArea area = new JTextArea();
			JScrollPane scr = new JScrollPane(area);
			scr.setPreferredSize(new Dimension(600, 400));
			contentPane.add(scr, BorderLayout.CENTER);
			
			area.setText(dump());
			
			pack();
			
		} catch (Exception ex) {
			dispose();
			throw new RuntimeException(ex);
		}
	}
	
	
	private String dump() throws IOException {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		pw.println("[System Properties]");
		ArrayList<String> propNames = new ArrayList<String>();
		Enumeration<?> enm = System.getProperties().propertyNames();
		while (enm.hasMoreElements()) {
			String propName = (String) enm.nextElement();
			propNames.add(propName);
		}
		Collections.sort(propNames);
		for (String propName : propNames) {
			String value = System.getProperty(propName);
			pw.println(propName + "=" + value);
		}
		pw.println();
		
		pw.println("[Environments]");
		TreeMap<String, String> env = new TreeMap<String, String>(System.getenv());
		for (Map.Entry<String, String> entry : env.entrySet()) {
			String envName = entry.getKey();
			String value = entry.getValue();
			pw.println(envName + "=" + value);
		}
		pw.println();
		
		pw.println("[Locale]");
		Locale locale = Locale.getDefault();
		pw.println(locale.toString());
		pw.println();
		
		pw.println("[Runtime]");
		Runtime rt = Runtime.getRuntime();
		pw.println("maxMemory=" + rt.maxMemory());
		pw.println("totalMemory=" + rt.totalMemory());
		pw.println("freeMemory=" + rt.freeMemory());
		pw.println();

		pw.println("[Files: ~/Documents]");
		File userDir = new File(System.getProperty("user.home"));
		File dir = new File(userDir, "Documents").getCanonicalFile();
		pw.println(dir.toString());
		File[] files = dir.listFiles();
		if (files != null) {
			for (File file : files) {
				pw.println(file.toString());
			}
		}
		pw.println();

		return sw.toString();
	}

	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				SimpleJavaApp app = new SimpleJavaApp();
				app.setLocationByPlatform(true);
				app.setVisible(true);
			}
		});
	}
}
