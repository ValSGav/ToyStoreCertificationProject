import controller.Controller;
import model.Raffle;
import model.Saver;
import view.View;

public class Main {
    public static void main(String[] args) {

        String filename = "prizes.txt";//для теста

        View view = new View();
        Saver saver = new Saver(filename);
        Raffle raffle = new Raffle();
        Controller controller = new Controller(view, raffle, saver);

        controller.run();

    }
}