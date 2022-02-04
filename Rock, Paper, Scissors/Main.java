import java.util.Scanner;
import java.security.SecureRandom;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;

class HMAC {
    static private byte[] key = new byte[16];

    static void keyGet() {
        System.out.println(String.format(("HMAC key: %032x\n"), new BigInteger(1, key)));
    }

    static void Sha3HmacMaker(String message) {
        SecureRandom rand = new SecureRandom();
        rand.nextBytes(key);
        byte[] resultHMAC = null;
        try {
            byte[] byteMessage = message.getBytes("UTF-8");
            SecretKeySpec secretKey = new SecretKeySpec(key, "HmacSHA3-256");
            Mac mac = Mac.getInstance("HmacSHA3-256");
            mac.init(secretKey);
            resultHMAC = mac.doFinal(byteMessage);
            System.out.println(String.format(("HMAC: %064x"), new BigInteger(1, resultHMAC)));
        } catch (Exception e) {
            System.out.println(e + " in Sha3HmacMaker");
        }
    }
}

class GameManage {
    private static String compMove = "";
    private static int compIndex;

    static String compMoveSet(String[] args) {
        SecureRandom rand = new SecureRandom();
        return compMove = args[compIndex = rand.nextInt(args.length)];
    }

    static void compMoveGet() {
        System.out.println("Computer move: " + compMove);
    }

    static void winChecker(int userIndex, int argc) {
        if (userIndex == compIndex)
            System.out.println("Draw");
        else if ((userIndex > compIndex && userIndex <= compIndex + argc / 2)
                || (userIndex < compIndex && userIndex < compIndex - argc / 2))
            System.out.println("You win!");
        else
            System.out.println("You lose!");
    }
}

class Interface {
    static void ASCIItable()
    {
        ///// ASCII table output /////
    }
}

public class Main {
    public static void main(String[] args) {
        if (args.length >= 3) {
            if (args.length % 2 == 1) 
            {
                Scanner input = new Scanner(System.in);
                String userMove = "";
                boolean isCorrectInput;
                while (true) 
                {
                    isCorrectInput = false;
                    HMAC.Sha3HmacMaker(GameManage.compMoveSet(args));

                    System.out.println("Available moves:");
                    for (int i = 0; i < args.length; i++)
                        System.out.println(String.format(("%d - %s"), i + 1, args[i]));
                    System.out.print("0 - exit\n? - help\nEnter your move: ");
                    userMove = input.nextLine();

                    if (userMove.equals("0") || userMove.equals("exit")) {
                        input.close();
                        break;
                    } else if (userMove.equals("?") || userMove.equals("help")) {
                        Interface.ASCIItable();
                        continue;
                    }
                    for (int i = 0; i < args.length; i++)
                        if (userMove.equals(String.format(("%d"), i + 1)) || userMove.equals(args[i])) {
                            GameManage.winChecker(i, args.length);
                            isCorrectInput = true;
                            break;
                        }

                    if (!isCorrectInput) {
                        System.out.println("Repeat your move correctly!\n");
                        continue;
                    }
                    GameManage.compMoveGet();
                    HMAC.keyGet();
                }
            } else
                System.out.println("\n Error!\n An even number of parameters have been entered!\n Restart program with odd number of parameters");
        } else
            System.out.println("\n Error!\n Insufficient number of parameters entered!\n Restart program with more than 3 parameters");
    }
}
