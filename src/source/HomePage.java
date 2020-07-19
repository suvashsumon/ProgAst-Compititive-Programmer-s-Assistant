package source;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import java.awt.Choice;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.Date;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomePage extends JFrame {

	private JPanel contentPane;
	private JTextField txtFolderPath;
	private JTextField txtFileName;
	private JTextField txtLink;
	Choice choiceExtentions;
	Choice choiceWebsite;
	File selectedFolderFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("resource")
			public void run() {
				File error_logFile = new File("src/data/error_log.txt"); // for trace error record
				File templateFile = new File("template.txt"); //for template
				
				try {
					if (!templateFile.exists()) {
						templateFile.createNewFile();
					}
					
					HomePage frame = new HomePage();
					frame.setVisible(true);
					
				} catch (Exception e) {
					try {
						FileWriter errorLogWriter = new FileWriter(error_logFile,true);
						errorLogWriter.write(e.toString());
						//errorLogWriter.write("Hello Bro");
						errorLogWriter.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomePage() {
		setTitle("ProgAst - Programmer's Assistant");
		setIconImage(Toolkit.getDefaultToolkit().getImage(HomePage.class.getResource("/source/developer.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 300);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPath = new JLabel("Folder Path");
		lblPath.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPath.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblPath.setBounds(25, 47, 105, 14);
		contentPane.add(lblPath);
		
		JLabel lblWebsite = new JLabel("Website");
		lblWebsite.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWebsite.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblWebsite.setBounds(35, 72, 95, 14);
		contentPane.add(lblWebsite);
		
		JLabel lblFileName = new JLabel("Problem ID");
		lblFileName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFileName.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblFileName.setBounds(25, 97, 105, 14);
		contentPane.add(lblFileName);
		
		JLabel lblDate = new JLabel("Problem Link");
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblDate.setBounds(25, 125, 105, 14);
		contentPane.add(lblDate);
		
		txtFolderPath = new JTextField();
		txtFolderPath.setToolTipText("");
		txtFolderPath.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		txtFolderPath.setBounds(140, 44, 322, 20);
		contentPane.add(txtFolderPath);
		txtFolderPath.setColumns(10);
		
		String[] webList = {"Codeforces", "", "Atcoder","Spoj","LightOJ","VJudge",
								"UVA_Online_Judge", "URI_Online_Judge",
								"Hackerrank","LeetCode","Local_Contest"};
		choiceWebsite = new Choice();
		for(int i=0; i<11; i++) {
			choiceWebsite.add(webList[i]);
		}
		choiceWebsite.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		choiceWebsite.setBounds(140, 69, 350, 20);
		contentPane.add(choiceWebsite);
		
		JButton btnNewButton = new JButton("Create & Open");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createFile();
				writeToFile();
				openFile();
			}
		});
		btnNewButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		btnNewButton.setBackground(new Color(0, 191, 255));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(330, 166, 160, 29);
		contentPane.add(btnNewButton);
		
		txtFileName = new JTextField();
		txtFileName.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		txtFileName.setBounds(140, 94, 259, 20);
		contentPane.add(txtFileName);
		txtFileName.setColumns(10);
		
		String[] fileTypeStrings = {".cpp",".c",".py",".java",".cs",".php",".js"};
		choiceExtentions = new Choice();
		for(int i=0; i<7;i++) {
			choiceExtentions.add(fileTypeStrings[i]);
		}
		choiceExtentions.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		choiceExtentions.setBounds(416, 94, 74, 20);
		contentPane.add(choiceExtentions);
		
		txtLink = new JTextField();
		txtLink.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		txtLink.setBounds(140, 122, 350, 20);
		contentPane.add(txtLink);
		txtLink.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Create");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createFile();
				writeToFile();
				JOptionPane.showMessageDialog(null, "Created");
			}
		});
		btnNewButton_1.setBackground(new Color(255, 140, 0));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		btnNewButton_1.setBounds(215, 166, 105, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Change Template");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Template template = new Template();
				template.setVisible(true);
				template.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(138, 43, 226));
		btnNewButton_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		btnNewButton_2.setBounds(34, 166, 171, 29);
		contentPane.add(btnNewButton_2);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				contentPane.add(jFileChooser);
				jFileChooser.showOpenDialog(null);
				selectedFolderFile = jFileChooser.getSelectedFile();
				txtFolderPath.setText(selectedFolderFile.getAbsolutePath());
				
				
			}
		});
		button.setBackground(new Color(211, 211, 211));
		button.setIcon(new ImageIcon(HomePage.class.getResource("/source/icons8-folder-16.png")));
		button.setBounds(462, 44, 28, 20);
		contentPane.add(button);
		
		JLabel lblDeveloperSuvash = new JLabel("Developer : Suvash Kumar, www.suvashkumar.xyz");
		lblDeveloperSuvash.setForeground(new Color(255, 99, 71));
		lblDeveloperSuvash.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDeveloperSuvash.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeveloperSuvash.setBounds(35, 206, 455, 29);
		contentPane.add(lblDeveloperSuvash);
	}
	
	/**
	 * creating a new file
	 */
	
	void createFile()
	{
		String textPath = txtFolderPath.getText();
		Path path = Paths.get(textPath);
		String nameString = txtFileName.getText();
		String extentionString = choiceExtentions.getSelectedItem();
		String webString = choiceWebsite.getSelectedItem();
		String pathString;
		if(webString.equalsIgnoreCase("")) pathString = path+"\\"+webString+nameString+extentionString;
		else pathString = path+"\\"+webString+"_"+nameString+extentionString;
		File file = new File(pathString);
		try {
			file.createNewFile();
			//JOptionPane.showMessageDialog(null, "Created");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Cann't create this file :(");
		}
	}
	
	/*
	 * write down the template code to created file
	 */
	
	void writeToFile()
	{
		Date date = new Date();
		String textPath = txtFolderPath.getText();
		Path path = Paths.get(textPath);
		String nameString = txtFileName.getText();
		String extentionString = choiceExtentions.getSelectedItem();
		String webString = choiceWebsite.getSelectedItem();
		String linkString = "Problem Link: "+ txtLink.getText();
		String pathString;
		if(webString.equalsIgnoreCase("")) pathString = path+"\\"+webString+nameString+extentionString;
		else pathString = path+"\\"+webString+"_"+nameString+extentionString;
		File file = new File(pathString);
		try {
			FileWriter fileWriter = new FileWriter(pathString,true);
			fileWriter.write("// This code is generated by ProgAst on "+date.toString()+"\n");
			fileWriter.write("// Details: https://github.com/suvashsumon/ProgAst-Compititive-Programmer-s-Assistant"+"\n");
			fileWriter.write("// File Name: "+webString+"-"+nameString+extentionString+"\n");
			fileWriter.write("// "+linkString+"\n"+"\n");
			fileWriter.write(getTemplateText());
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Cann't write to file :(");
		}
	}
	
	/*
	 * parsing data from template
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
	 * opening file via associated application
	 */
	
	void openFile()
	{
		String textPath = txtFolderPath.getText();
		Path path = Paths.get(textPath);
		String nameString = txtFileName.getText();
		String extentionString = choiceExtentions.getSelectedItem();
		String webString = choiceWebsite.getSelectedItem();
		String pathString;
		if(webString.equalsIgnoreCase("")) pathString = path+"\\"+webString+nameString+extentionString;
		else pathString = path+"\\"+webString+"_"+nameString+extentionString;
		File file = new File(pathString);

        try {
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null, "Cann't open this file :(");
        }
	}
}
