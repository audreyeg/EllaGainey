package sort;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.TextField;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EtchedBorder;

public class UI extends Frame{
	
	private static JFrame frame;
	private static AllSorts sortalgo;
	private static String[] algorithmList = {"Bubble Sort", "Selection Sort", "Quick Sort", "Merge Sort", 
			"Shell Sort", "Insertion Sort"};

	//constructor
	public UI(AllSorts sort) {
		sortalgo = sort;
		frame = new JFrame("Sort Visualizer!");
		setUpFrame();
		setUpPanel();
		//setUpArray();
		frame.setVisible(true);
	}
	
	public static void setUpFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,635);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		//frame.pack();

	}
	
	public static void setUpPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(20,20,750,60);
		panel.setBorder(BorderFactory.createTitledBorder("Algorithm Viewer"));
		
		JComboBox alg = new JComboBox(algorithmList);
		alg.setBounds(10,25,120,25);
		panel.add(alg);
		
		//SORT
		JButton sort = new JButton("Sort");
		sort.setBounds(170,25,100,25);
		panel.add(sort);
		
		//SHUFFLE
		JButton shuffle = new JButton("Shuffle");
		shuffle.setBounds(290,25,100,25);
		panel.add(shuffle);
		
		//SIZE
		JLabel sizeLabel = new JLabel("Size");
		sizeLabel.setHorizontalAlignment(JLabel.LEFT);
		sizeLabel.setBounds(420,25,50,25);
		panel.add(sizeLabel);
		JSlider size = new JSlider(JSlider.HORIZONTAL,1,6,1);
		size.setMajorTickSpacing(50);
		size.setBounds(480,25,100,25);
		size.setPaintTicks(false);
		panel.add(size);
		
		//SPEED
		JLabel speedLabel = new JLabel("Speed");
		speedLabel.setHorizontalAlignment(JLabel.LEFT);
		speedLabel.setBounds(580,25,50,25);
		panel.add(speedLabel);
		JSlider speed = new JSlider(JSlider.HORIZONTAL,0,100,15);
		speed.setMajorTickSpacing(5);
		speed.setBounds(630,25,100,25);
		speed.setPaintTicks(false);
		panel.add(speed);
		
		frame.add(panel);
		
		
	}
//	
//	public static void setUpArray() {
//		int[] arrayToPrint = sortalgo.getOriginalArray();
//		String arrayString;
//		arrayString = "{";
//		for (int i = 0; i < arrayToPrint.length; i++) {
//			
//			if (i < arrayToPrint.length - 1) {
//				arrayString = arrayString + arrayToPrint[i] + ", ";
//			}
//			else if (i < arrayToPrint.length) {
//				arrayString = arrayString + arrayToPrint[i];
//			}
//		}
//
//		arrayString = arrayString + "}";
//		//TextField text = new TextField(arrayString);
//		//frame.getContentPane().add(text);
//	}
	
}
