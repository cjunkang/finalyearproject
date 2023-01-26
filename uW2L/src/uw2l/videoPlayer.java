/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uw2l;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

public class videoPlayer extends JFrame {
   private static final long serialVersionUID = 1L;
   public static String TITLE = "Video Player";
   //"C:\\Users\\harri\\OneDrive\\Documents\\NetBeansProjects\\uW2L\\src\\uw2l\\312lec\\312LectureTrial1.mp4"
   public static String VIDEO_PATH = "C:\\Users\\harri\\OneDrive\\Documents\\NetBeansProjects\\uW2L\\src\\uw2l\\312lec\\312LectureTrial2.mp4";
   private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
   private JButton playButton;
   private JButton stopButton;
   private JButton pauseButton;
   private JButton rewindButton;
   private JButton skipButton;
   public JLabel theTitle;
   public JLabel thelocate;

   
   public videoPlayer(String title) {
      super(title);
      //theTitle.setText(title);
      mediaPlayerComponent = new EmbeddedMediaPlayerComponent();		
   }
   public void initialize() {
      this.setBounds(100, 100, 600, 400);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.addWindowListener(new WindowAdapter() {
      @Override
         public void windowClosing(WindowEvent e) {
            mediaPlayerComponent.release();
            System.exit(0);
         }
      });    	
      JPanel contentPane = new JPanel();
      contentPane.setLayout(new BorderLayout());   	 
      contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);
      JPanel controlsPane = new JPanel();
      playButton = new JButton("Play");
      controlsPane.add(playButton); 
      stopButton = new JButton("Stop");
      controlsPane.add(stopButton);
      pauseButton = new JButton("Pause");
      controlsPane.add(pauseButton);
      rewindButton = new JButton("Rewind");
      controlsPane.add(rewindButton);
      skipButton = new JButton("Skip");
      controlsPane.add(skipButton);
      contentPane.add(controlsPane, BorderLayout.SOUTH);

      playButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            mediaPlayerComponent.mediaPlayer().controls().play();
         }
      });  
      pauseButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            mediaPlayerComponent.mediaPlayer().controls().pause();
         }
      });
      
      stopButton.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
            mediaPlayerComponent.mediaPlayer().controls().stop();
         }
      });
      
      rewindButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            mediaPlayerComponent.mediaPlayer().controls().skipTime(-14000);
         }
      });
      skipButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            mediaPlayerComponent.mediaPlayer().controls().skipTime(4000);
         }
      });
      this.setContentPane(contentPane);
      this.setVisible(true);
   }
   
   String name;
   
   public void loadVideo(String path) {
     
       mediaPlayerComponent.mediaPlayer().media().startPaused(path); 
       
       /*
       if(thelocate.getText().equals("CSCI312"))   {
          
          //path = "C:\\Users\\harri\\OneDrive\\Documents\\NetBeansProjects\\uW2L\\src\\uw2l\\312lec\\" + theTitle.getText();
          path = "/uw2l/312lec/" + theTitle.getText(); 
          System.out.println("path: " + path);
          mediaPlayerComponent.mediaPlayer().media().startPaused(path);
          System.out.println("path: " + path);
          //VIDEO_PATH = path;
       }*/
  
   }
  
  
   
   public static void main( String[] args ){
       
       String path= null;
      try {            
         UIManager.setLookAndFeel(
         UIManager.getSystemLookAndFeelClassName());
      } 
      catch (Exception e) {
         System.out.println(e);
      }
      videoPlayer application = new videoPlayer(TITLE);
      application.initialize(); 
      application.setVisible(true);  
      
      application.loadVideo(VIDEO_PATH);
   }
}
