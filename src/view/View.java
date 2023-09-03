package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class View {


    public Character showMenu(HashMap<Character, String> menu) {
        for (Map.Entry<Character, String> entry : menu.entrySet()) {
            System.out.printf("%s: %s", entry.getKey(), entry.getValue());
            System.out.println();
        }
        Scanner scan = new Scanner(System.in, "cp1251");
        String result = scan.nextLine();

        if (menu.containsKey(result.charAt(0)))
            return result.charAt(0);
        else
            return 'e';

    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
