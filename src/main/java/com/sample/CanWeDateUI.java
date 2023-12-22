/*
    CanWeDate - system ekspercki do decydowania o potencjalnych partnerach romantycznych
    Copyright (C) 2023, Kamil Małecki, Oskar Kiliańczyk

    Niniejszy program jest wolnym oprogramowaniem; możesz go 
    rozprowadzać dalej i/lub modyfikować na warunkach Powszechnej
    Licencji Publicznej GNU, wydanej przez Fundację Wolnego
    Oprogramowania - według wersji 3-ciej tej Licencji.

    Niniejszy program rozpowszechniany jest z nadzieją, iż będzie on 
    użyteczny - jednak BEZ JAKIEJKOLWIEK GWARANCJI, nawet domyślnej 
    gwarancji PRZYDATNOŚCI HANDLOWEJ albo PRZYDATNOŚCI DO OKREŚLONYCH 
    ZASTOSOWAŃ. W celu uzyskania bliższych informacji - Powszechna 
    Licencja Publiczna GNU. 

    Z pewnością wraz z niniejszym programem otrzymałeś też egzemplarz 
    Powszechnej Licencji Publicznej GNU (GNU General Public License);
    jeśli nie - napisz do Free Software Foundation, Inc., 675 Mass Ave,
    Cambridge, MA 02139, USA.
 */

package com.sample;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class CanWeDateUI extends JPanel {

	private static final long serialVersionUID = 510l;

    private JTextArea        questionArea;
        
    private ButtonGroup		 answerGroup;
        
    private JPanel			 questionPanel;
        
    public String			 answer;
    
    public JFrame            frame;

    public CanWeDateUI(String question, String[] options) {
    	super( new BorderLayout() );
        answer = null;
        
        //Create main vertical split panel
        JSplitPane splitPane = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
        add( splitPane, BorderLayout.CENTER );

        //create top half of split panel and add to parent
        JPanel topHalf = new JPanel();
        topHalf.setLayout( new BoxLayout( topHalf, BoxLayout.X_AXIS ) );
        topHalf.setBorder( BorderFactory.createEmptyBorder( 5, 5, 0, 5 ) );
        topHalf.setMinimumSize( new Dimension( 400, 50 ) );
        topHalf.setPreferredSize( new Dimension( 450, 250 ) );
        splitPane.add( topHalf );

        //create bottom top half of split panel and add to parent
        JPanel bottomHalf = new JPanel( new BorderLayout() );
        bottomHalf.setMinimumSize( new Dimension( 400, 50 ) );
        bottomHalf.setPreferredSize( new Dimension( 450, 300 ) );
        splitPane.add( bottomHalf );
            
        JPanel questionContainer = new JPanel();
        questionContainer.setLayout(new BoxLayout(questionContainer, BoxLayout.Y_AXIS));
        questionContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        bottomHalf.add(questionContainer);

        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        questionContainer.add(questionPanel);
            
        answerGroup = new ButtonGroup();
        questionContainer.add(questionPanel);

        //Create panel for checkout button and add to bottomHalf parent
        JPanel checkoutPane = new JPanel();
        JButton buttonConfirm = new JButton( "Confirm" );
        buttonConfirm.setVerticalTextPosition( AbstractButton.CENTER );
        buttonConfirm.setHorizontalTextPosition( AbstractButton.LEADING );
        buttonConfirm.addMouseListener( new ConfirmButtonHandler() );
            
        checkoutPane.add( buttonConfirm );
            
        bottomHalf.add( checkoutPane, BorderLayout.NORTH );
            
        questionArea = new JTextArea( 4, 100 );
        questionArea.setLineWrap(true);
        questionArea.setEditable(false);
            
        JScrollPane questionAreaPane = new JScrollPane( questionArea,
                                                      ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                                                      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        topHalf.add( questionAreaPane, BorderLayout.CENTER );
        
        setButtons(options);
        questionArea.setText(question);
        
        this.createAndShowGUI(true);
    }

     public void createAndShowGUI(boolean exitOnClose) {
    	 //Create and set up the window.
         frame = new JFrame( "Can We Date?" );
         frame.setDefaultCloseOperation(exitOnClose ? JFrame.EXIT_ON_CLOSE : JFrame.DISPOSE_ON_CLOSE);

         setOpaque( true );
         frame.setContentPane( this );

         //Display the window.
         frame.pack();
         frame.setLocationRelativeTo(null); // Center in screen
         frame.setVisible( true );
     }
        
     private void removeButtons() {
    	 answerGroup.clearSelection();
    	 questionPanel.removeAll();
    	 redoPanel();
     }
        
     private void setButtons(String[] items) {
    	 for (int i=0; i<items.length; i++) {
    		 String option = items[i];
    		 JRadioButton button = new JRadioButton(option);
    		 answerGroup.add(button);
    		 questionPanel.add(button);
    		 redoPanel();
    	 }
     }
        
     private void redoPanel() {
    	 questionPanel.revalidate();
    	 questionPanel.repaint();
     }
        
     private String selectedButton() {
    	 Enumeration<AbstractButton> buttons = answerGroup.getElements();
    	 while (buttons.hasMoreElements()) {
    		 AbstractButton button = buttons.nextElement();
    		 if (button.isSelected()) return button.getText();
    	 }
    	 return null;
     }

     private class ConfirmButtonHandler extends MouseAdapter {
    	 public void mouseReleased(MouseEvent e) {
    		 String selectedButtonString = selectedButton();
    		 if (selectedButtonString == null) return;
    		 answer = selectedButtonString;
    		 frame.setVisible(false);
    	 }
     }
}
