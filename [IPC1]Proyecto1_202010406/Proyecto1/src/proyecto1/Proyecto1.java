package proyecto1;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author dennis
 */
public class Proyecto1 {

    static Login login;
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        login = new Login();
        login.displayLogin();
    }    
}
