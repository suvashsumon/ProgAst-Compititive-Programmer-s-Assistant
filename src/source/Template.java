package source;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import data.*;

public class Template extends JFrame {

	private JPanel contentPane;
	JTextArea txtTemplate = new JTextArea();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Template() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Template.class.getResource("/source/developer.png")));
		setTitle("Template");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 486, 360);
		setBounds(100, 100, 600, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCreateYourTemplate = new JLabel("Create your template");
		lblCreateYourTemplate.setBounds(10, 11, 565, 24);
		lblCreateYourTemplate.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		lblCreateYourTemplate.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCreateYourTemplate);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveTemplate();
				dispose();
			}
		});
		btnSave.setBounds(248, 408, 89, 37);
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setBackground(new Color(0, 255, 0));
		btnSave.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		contentPane.add(btnSave);
		
		JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setBounds(10, 46, 450, 216);
		scrollPane.setBounds(10, 46, 565, 350);
		contentPane.add(scrollPane);
		
		
		
		txtTemplate.setText(getTemplateText());
		txtTemplate.setFont(new Font("Monospaced", Font.PLAIN, 17));
		//txtTemplate.setText("#include<bits/stdc++.h>\r\nusing namespace std;\r\n\r\nint main()\r\n{\r\n\t// write your code\r\n\treturn 0;\r\n}");
		scrollPane.setViewportView(txtTemplate);
	}
	
	/*
	 * parse text from template
	 */
	
	String getTemplateText()
	{
		String contentString = "";
		File  inputFile = new File("template.txt");
		try {
			FileReader fileReader = new FileReader(inputFile);
			Scanner scanner = new Scanner(fileReader);
			
			while(scanner.hasNext()) {
				contentString = contentString+scanner.nextLine()+"\n";
			}
			try {
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Something goes wrong :(");
			}
			//System.out.println(contentString);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Something goes wrong :(");
		}
		return contentString;
	}
	
	/*
	 * saving template
	 */
	
	void saveTemplate()
	{
		String contentString = txtTemplate.getText();
		File filepathFile = new File("template.txt");
		try {
			FileWriter fileWriter = new FileWriter(filepathFile);
			fileWriter.write(contentString);
			fileWriter.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Template does't save. Something goes wrong :(");
		}
	}
}
