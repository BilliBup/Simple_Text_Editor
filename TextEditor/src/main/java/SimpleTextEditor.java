import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.io.FileNotFoundException;


public class SimpleTextEditor implements ActionListener {
    JFrame frame;
    JTextArea jTextArea;
    JMenuBar jMenuBar;
    JMenu File;
    JMenu Edit;
    JMenu Close;
    JMenuItem Open,newFile,Save;
    JMenuItem cut,copy,paste,print;
    JMenuItem closeEditor;
    
    
    
    public SimpleTextEditor(){
        // created the frame
        frame = new JFrame("Simple Text Editor");
        frame.setBounds(0,0,1000,800);
        
        // add text area
        jTextArea = new JTextArea("Welcome");
        jTextArea.setBounds(0, 0, 800, 600);
        frame.add(jTextArea);
        
        // initialize menubar
        jMenuBar = new JMenuBar();
        
        // menubar items 
        File = new JMenu("File");
        Edit = new JMenu("Edit");
        Close = new JMenu("Close");
        
        // add items in menubar
        jMenuBar.add(File);
        jMenuBar.add(Edit);
        jMenuBar.add(Close);
        
        // adding items in file open,newFile,save
        Open = new JMenuItem("Open");
        File.add(Open);
        Open.addActionListener(this);
        newFile = new JMenuItem("newFile");
        File.add(newFile);
        newFile.addActionListener(this);
        Save = new JMenuItem("Save");
        File.add(Save);
        Save.addActionListener(this);
        
        // adding items in edit  cut,copy,paste,print
        cut = new JMenuItem("cut");
        Edit.add(cut);
        cut.addActionListener(this);
        copy = new JMenuItem("copy");
        Edit.add(copy);
        copy.addActionListener(this);
        paste = new JMenuItem("paste");
        Edit.add(paste);
        paste.addActionListener(this);
        print = new JMenuItem("print");
        Edit.add(print);
        print.addActionListener(this);
        
        // adding item in close exit
        closeEditor = new JMenuItem("Exit");
        Close.add(closeEditor);
        closeEditor.addActionListener(this);
        
        //attaching menubar in frame
        frame.setJMenuBar(jMenuBar);
      
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    
    
    public static void main(String[] args){
        SimpleTextEditor fr = new SimpleTextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("copy")){
            jTextArea.copy();
        }
        else if(s.equals("cut")){
            jTextArea.cut();
        }
        else if(s.equals("paste")){
            jTextArea.paste();
        }
        else if(s.equals("print")){
            try{
                jTextArea.print();
            }
            catch(PrinterException ex){
                throw new RuntimeException(ex);
            }
        }
        else if(s.equals("newFile")){
            jTextArea.setText("");
        }
        else if(s.equals("Exit")){
            System.exit(0);
        }
        else if(s.equals("Open")){
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showOpenDialog(null);
            
            if(ans == JFileChooser.APPROVE_OPTION){
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                String s1="",s2;
                try{
                   BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                   s2 = bufferedReader.readLine();
                    while((s2 = bufferedReader.readLine())!=null){
                        s1+=s2+"\n";
                    }
                 jTextArea.setText(s1);
                }
                catch(FileNotFoundException ex){
                  throw new RuntimeException(ex);
                }
                catch(IOException ex){
                  throw new RuntimeException(ex);
                }
                
            }
        }
        else if(s.equals("Save")){
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showSaveDialog(null);
            if(ans == JFileChooser.APPROVE_OPTION){
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer;
                try {
                    writer = new BufferedWriter(new FileWriter(file,false));
                    writer.write((jTextArea.getText()));
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                
            }
        }
        
    }
    
}
 