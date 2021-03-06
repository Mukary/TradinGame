package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Util {
	
	/**
	 * Applies the SHA1 encryption on a String
	 * @param input the string to encrypt
	 * @return The encrypted string
	 * @throws NoSuchAlgorithmException
	 */
	public static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }       
        return sb.toString();
	}
	
	/**
	 * Utility method displaying an alert box on read-only mode
	 * @param type the type of message to display (error, confirmation etc)
	 * @param title The title of the alert box
	 * @param content The message to display
	 */
	public static void displayAlert(AlertType type, String title, String content){
		Alert alert = new Alert(type);
		alert.setHeaderText(null);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}

}
