package me.justicepro.notemaker;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Note extends JFrame {

	private JPanel contentPane;
	private String id;
	public JTextArea textArea;
	
	private boolean edit = false;
	
	/**
	 * Create the frame.
	 * @param noteFile 
	 * @wbp.parser.constructor
	 */
	public Note(NoteFile noteFile) {
		setTitle("Create Note");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTextArea textArea = new JTextArea();
		contentPane.add(textArea, BorderLayout.CENTER);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (edit) {
					Application.map.put(id, textArea.getText());
				}else {
					id = JOptionPane.showInputDialog(null, "ID Name?");
					Application.map.put(id, textArea.getText());
					edit = true;
				}
			}
		});
		contentPane.add(btnSave, BorderLayout.SOUTH);
		
		
	}
	
	/**
	 * Create the frame.
	 * @param noteFile 
	 */
	public Note(NoteFile noteFile, String id, String text) {
		setTitle("Create Note");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		textArea = new JTextArea();
		textArea.setText(text);
		contentPane.add(textArea, BorderLayout.CENTER);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Application.map.put(id, textArea.getText());
			}
		});
		contentPane.add(btnSave, BorderLayout.SOUTH);
		
		
	}

}
