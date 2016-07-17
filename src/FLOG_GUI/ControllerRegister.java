/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FLOG_GUI;

import FLOG_LOGIC.Multiplayer;
import com.sun.java.swing.plaf.motif.MotifButtonListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    FLOG_LOGIC.Multiplayer multiplayer=new Multiplayer();
    
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    public static final Pattern VALID_USER_REGEX = 
    Pattern.compile("[^a-zA-Z0-9_]", Pattern.CASE_INSENSITIVE);
    
    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
    
    public static boolean SpecialCharacterExist(String user){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(user);
        return matcher.find();
    }
    public ControllerRegister(PanelRegister panelRegister, GameScreen gameScreen) {
        this.panelRegister = panelRegister;
        this.gameScreen = gameScreen;
        initializeRegisterListeners();
    }

    private void cancelClick()
    {
        gameScreen.changeScreen(DataForUI.STR_LOGIN, DataForUI.STR_REGISTER);
    }
    
    private void signUpClick(String uname, String pass, String conPass, String email)
    {
        if(!isUsernameExists(uname))
        {
            if(!SpecialCharacterExist(uname)){
            if(validateEmail(email)){
                if(validatePass(pass, conPass))
                {
                //code for registering user on server
                    if(multiplayer.registerUser(uname, email, pass)){
                    JOptionPane.showMessageDialog(gameScreen, "You have Successfully Registered!","Registered",JOptionPane.INFORMATION_MESSAGE);
                    gameScreen.changeScreen(DataForUI.STR_LOGIN, DataForUI.STR_REGISTER);
                    }
                    else{
                    JOptionPane.showMessageDialog(gameScreen, "Something went wrong!","Try Again", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                JOptionPane.showMessageDialog(gameScreen, "Passwords does Not match!","Try Again", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
               JOptionPane.showMessageDialog(gameScreen, "Invalid email!","Try Again", JOptionPane.ERROR_MESSAGE);
            }
            }
            else{
                JOptionPane.showMessageDialog(gameScreen, "Invalid User name!","Try Again", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(gameScreen, "User '"+uname+"' Already exists!","Try Again", JOptionPane.ERROR_MESSAGE);
        
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
        if(multiplayer.getUser(user))
        {
            return true;
        }
        return false;
    }
    
    private void initializeRegisterListeners() 
    {
        final int _signup =4;
        final int _cancel=5;
        final int _username=1;
        final int _pass = 2;
        final int _conpass = 3;
        final int _email=6;
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
                
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_register_h.png"));
                panelRegister.setIcon_Register(imgIcon);
                
                JTextField uname = (JTextField)panelRegister.getCompCon(_username);
                JTextField email = (JTextField)panelRegister.getCompCon(_email);
                JPasswordField pass = (JPasswordField)panelRegister.getCompCon(_pass);
                JPasswordField conPass = (JPasswordField)panelRegister.getCompCon(_conpass);
                String str_pass = new String(pass.getPassword());
                String str_conPass = new String(conPass.getPassword());
                
                if(!(email.toString().equals("")||str_pass.equals("")||str_conPass.equals("")||uname.equals(""))){
                    signUpClick(uname.getText().trim(), str_pass,str_pass,email.getText());
                }
                else{
                    JOptionPane.showMessageDialog(gameScreen, "Details incomplete!","Try Again", JOptionPane.ERROR_MESSAGE);
                }
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_register_c.png"));
                panelRegister.setIcon_Register(imgIcon);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_register_h.png"));
                panelRegister.setIcon_Register(imgIcon);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_register_h.png"));
                panelRegister.setIcon_Register(imgIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_register_n.png"));
                panelRegister.setIcon_Register(imgIcon);
            }
        });
        
        panelRegister.getCompCon(_cancel).addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cancelClick();
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_cancel_h.png"));
                panelRegister.setIcon_Cancel(imgIcon);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_cancel_n.png"));
                panelRegister.setIcon_Cancel(imgIcon);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_cancel_h.png"));
                panelRegister.setIcon_Cancel(imgIcon);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_cancel_h.png"));
                panelRegister.setIcon_Cancel(imgIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/btn_cancel_n.png"));
                panelRegister.setIcon_Cancel(imgIcon);
            }
        });
        
       
    }
    
    
}
