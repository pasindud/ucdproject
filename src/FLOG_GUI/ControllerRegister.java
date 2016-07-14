/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_GUI;

import com.sun.java.swing.plaf.motif.MotifButtonListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Dushan Galappaththi
 */
public class ControllerRegister {
    
    int mouseX=0;
    int mouseY=0;
    PanelRegister panelRegister;
    GameScreen gameScreen;

    public ControllerRegister(PanelRegister panelRegister, GameScreen gameScreen) {
        this.panelRegister = panelRegister;
        this.gameScreen = gameScreen;
        initializeRegisterListeners();
    }

    private void cancelClick()
    {
        gameScreen.changeScreen(DataForUI.STR_LOGIN, DataForUI.STR_REGISTER);
    }
    
    private void signUpClick(String uname, String pass, String conPass)
    {
        if(!isUsernameExists(uname))
        {
            if(validatePass(pass, conPass))
            {
                //code for registering user on server
                JOptionPane.showMessageDialog(gameScreen, "You have Successfully Registered!","Registered",JOptionPane.INFORMATION_MESSAGE);
                gameScreen.changeScreen(DataForUI.STR_LOGIN, DataForUI.STR_REGISTER);
            }
            else
            {
               JOptionPane.showMessageDialog(gameScreen, "Passwords does Not match!","Try Again", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(gameScreen, "User '"+uname+"' Already exists","Try Again", JOptionPane.ERROR_MESSAGE);
        
        }
    
    }
    
    private void exitClick()
    {
        System.exit(0);
    }
    
    private boolean validatePass(String pass, String conPass)
    {
        System.out.println(""+pass);
        System.out.println(""+conPass);
        if(pass.equals(conPass))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private boolean isUsernameExists(String user)
    {
        //code of checking uname list from server
        return false;
    }
    
    private void initializeRegisterListeners() 
    {
        final int _signup =4;
        final int _cancel=5;
        final int _username=1;
        final int _pass = 2;
        final int _conpass = 3;
        final int _exit=0;
        final int _TopBorder=0;
        
        panelRegister.getCompCon(_TopBorder).addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
               
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
        
        panelRegister.getCompCon(_TopBorder).addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                 gameScreen.moveScreen(e.getXOnScreen(), e.getYOnScreen(),mouseX,mouseY);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                
            }
        });
        
        panelRegister.getCompTop(_exit).addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               exitClick();
            }

            @Override
            public void mousePressed(MouseEvent e) {
               
            }

            @Override
            public void mouseReleased(MouseEvent e) {
              
            }

            @Override
            public void mouseEntered(MouseEvent e) {
    
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
        
        panelRegister.getCompCon(_signup).addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                JTextField uname = (JTextField)panelRegister.getCompCon(_username);
                JPasswordField pass = (JPasswordField)panelRegister.getCompCon(_pass);
                JPasswordField conPass = (JPasswordField)panelRegister.getCompCon(_conpass);
                String str_pass = new String(pass.getPassword());
                String str_conPass = new String(conPass.getPassword());
                
                signUpClick(uname.getText(), str_pass,str_pass);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        panelRegister.getCompCon(_cancel).addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cancelClick();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
    
    
}
