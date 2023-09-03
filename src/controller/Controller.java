package controller;

import model.Raffle;
import model.Saver;
import model.impl.Toy;
import view.View;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Controller {

    View view;
    Raffle raffle;
    Saver saver;

    public Controller(View view, Raffle raffle, Saver saver) {
        this.view = view;
        this.raffle = raffle;
        this.saver = saver;
    }

    public void run() {
        Character answer;
        do {
            answer = view.showMenu(getMenu());
            try {
                switch (answer) {
                    case '1' -> this.addToyToTheDrawing();
                    case '2' -> this.draw();
                    case '3' -> this.giveAPrize();
                    case '4' -> view.showMessage("Exit");
                    default -> view.showMessage("Error");
                }
            } catch (IOException | RuntimeException e) {
                System.out.println(e.getMessage());
            }
        } while (answer != '4');
    }

    private HashMap<Character, String> getMenu() {
        HashMap<Character, String> menu = new HashMap<>();
        menu.put('1', ". - Add a toy to the drawing");
        menu.put('2', ". - Raffle a toy");
        menu.put('3', ". - Give a toy");
        menu.put('4', ". - Exit");
        return menu;
    }

    private void addToyToTheDrawing() throws RuntimeException {
        System.out.print("Введите название игрушки: ");
        Scanner sc = new Scanner(System.in, "cp1251");
        String toy_name = sc.nextLine();
        Toy toy = new Toy(toy_name);
        System.out.print("Введите 'вес' игрушки в розыгрыше: ");
        int weight = sc.nextInt();
        this.raffle.addPrizeToDrawingList(toy, weight);
        System.out.printf("Добавлена в розыгрыш игрушка %s(id:%d) с 'весом' %d%n", toy.getName(), toy.getId(), toy.getWeight());
    }

    private void giveAPrize() throws IOException, RuntimeException {
        Toy toy = (Toy) this.raffle.getPrize();
        this.saver.save(toy);
        System.out.printf("Выдана игрушка %s(id:%d)%n", toy.getName(), toy.getId());
    }

    private void draw() throws RuntimeException {
        this.raffle.draw();
        System.out.println("Проведен розыгрыш игрушки");
    }

}