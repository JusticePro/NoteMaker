package me.justicepro.notemaker;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JScrollPane;

public class Application extends JFrame {

	private JPanel contentPane;
	private NoteFile noteFile;
	
	public static HashMap<String, String> map;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					map = new HashMap<>();
					Application frame = new Application();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Application() {
		noteFile = new NoteFile("");
		setTitle("NoteMaker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 496);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("Note Documents (*.note)", "note"));
				if (chooser.showSaveDialog(null)!=JFileChooser.CANCEL_OPTION) {
					File file = chooser.getSelectedFile();
					noteFile = new NoteFile(textArea.getText());
					noteFile.setNotes(map);
					try {
						if (file.exists()) {
							NoteFile.saveToFile(noteFile, file);
						}else {
							NoteFile.saveToFile(noteFile, new File(file.getPath() + ".note"));
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		mnFile.add(mntmSave);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("Note Documents (*.note)", "note"));
				if (chooser.showOpenDialog(null)!=JFileChooser.CANCEL_OPTION) {
					File file = chooser.getSelectedFile();
					try {
						noteFile = NoteFile.loadFromFile(file);
						if (file.exists()) {
							noteFile = NoteFile.loadFromFile(file);
						}else {
							noteFile = NoteFile.loadFromFile(new File(file.getPath() + ".note"));
						}
						textArea.setText(noteFile.getBody());
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		mnFile.add(mntmOpen);
		
		JMenu mnNotes = new JMenu("Notes");
		menuBar.add(mnNotes);
		
		JMenuItem mntmCreateNote = new JMenuItem("Create Note");
		mntmCreateNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Note note = new Note(noteFile);
				note.setVisible(true);
			}
		});
		mnNotes.add(mntmCreateNote);
		
		JMenuItem mntmEditNote = new JMenuItem("Edit Note");
		mntmEditNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog(null, "ID?");
				if (noteFile.getNotes().containsKey(id)) {
					Note note = new Note(noteFile, id, noteFile.getNotes().get(id));
					note.setVisible(true);
					
				}else {
					JOptionPane.showMessageDialog(null, "That note does not exist.");
				}
			}
		});
		mnNotes.add(mntmEditNote);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		scrollPane.setViewportView(textArea);
		
	}

}
