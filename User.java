import java.util.Scanner;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Metode login yang menerima Scanner sebagai parameter
    public boolean login(Scanner scanner) {
        System.out.println("+-----------------------------------------------------+");
        System.out.println("Log in");
        System.out.print("Username: ");
        String inputUsername = scanner.nextLine().trim();
        System.out.print("Password: ");
        String inputPassword = scanner.nextLine().trim();
        String captcha = "123ABC";
        System.out.println("Captcha: " + captcha);
        System.out.print("Masukkan Captcha: ");
        String inputCaptcha = scanner.nextLine().trim();

        if (inputUsername.equals(username) && 
            inputPassword.equals(password) &&
            inputCaptcha.equalsIgnoreCase(captcha)) {
            System.out.println("\nLogin berhasil!");
            return true;
        } else {
            System.out.println("\nLogin gagal, silakan coba lagi.");
            return false;
        }
    }
}
