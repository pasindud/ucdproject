/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frog;

/**
 *
 * @author Pasindu
 */
public class Frog {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here
  }

  /*
  public static void generateDemoUsers(){
      Multiplayer multiplayer = new Multiplayer();
      boolean result;
      String player2 = "player2";
      String player3 = "player3";
      multiplayer.setOnline(player2);
      multiplayer.setOnline(player3);
  }
  */

  /*
  public static void main(String[] args){

      System.out.println(
              "Running demo search users online multiplayer code.");
      Multiplayer multiplayer = new Multiplayer();
      generateDemoUsers();
      String username = "pasindu";
      String email = "pasindu@gmail.com";
      String pwd = "password";
      boolean result;
  */

  // Register the new  user.
  /*  Following code would register the user.
      Currently commented because the users are already registered.

      result = multiplayer.registerUser(username, email, pwd);

      if (result) {
          System.out.println("User registered.");
      } else {
          System.out.println("User already registered.");
      }
  */

  /**
   *
   * result = multiplayer.login(username, pwd);
   * if (result) {
   * System.out.println("User logedin.");
   * } else {
   * System.out.println("User details incorrect.");
   * return;
   * } **/

  /***
   * try {
   * multiplayer.setOnline(username);
   * } catch (Exception e){
   * System.out.println("Error occured when setting online - " + e.getMessage());
   * }
   *
   * // Get the users currently online;
   * /**
   * List<String> nowOnlineUsers = new ArrayList<String>();
   * try {
   * nowOnlineUsers = multiplayer.getOnlineUsers();
   * System.out.println("Users currently online are...");
   * for (int i = 0; i < nowOnlineUsers.size(); i++) {
   * System.out.println(nowOnlineUsers.get(i));
   * }
   * } catch (Exception e){
   * System.out.println("getOnlineUsers exceptions - " + e.getMessage());
   * }
   */

  /*
      Currently been commented, becuase the code dose not show anything
      unless the whole system is implmented.

      SetUserOnlineThread setUserOnlineThread =
                                      new SetUserOnlineThread(username);
      setUserOnlineThread.setDaemon(true);
      setUserOnlineThread.start();
  */
  // }

}
