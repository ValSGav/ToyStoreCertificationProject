package model;

import model.impl.Toy;

import java.util.*;

public class Raffle {

    static Random rnd;

    static {
        rnd = new Random();
    }

    private int totalWeight;

    private PriorityQueue<Raffled> availablePrizes; //очередь разыгранных призов на выдачу
    private ArrayList<Raffled> setOfPrizeForTheDrawling; // список призов для розыгрыша

    /*
        Конструктор
     */
    public Raffle() {
        this.availablePrizes = new PriorityQueue<Raffled>();
        this.setOfPrizeForTheDrawling = new ArrayList<Raffled>();
        setTotalWeight();
    }

    /*
        Выбор очередного приза из списка призов в соответствии с алгоритмом
        розыгрыша: первый этап: выбираем планку, ниже которой призы не попадают в розыгрыш,
        вторй этап: из призов первого этапа случайным образом выбираем приз.
    */
    public void draw(int count) throws RuntimeException {
        for (int i = 0; i < count; i++) {
            if (!this.setOfPrizeForTheDrawling.isEmpty()) {
                ArrayList<Raffled> prizeStepTwo = new ArrayList<Raffled>();
                boolean isDrawn = false;
                while (!isDrawn) {
                    int stepOne = totalWeight - rnd.nextInt(totalWeight);
                    for (Raffled toy : setOfPrizeForTheDrawling) {
                        if (toy.getWeight() >= stepOne) {
                            prizeStepTwo.add(toy);
                        }
                    }
                    if (!prizeStepTwo.isEmpty()) {
                        int stepTwo = rnd.nextInt(prizeStepTwo.size());
                        Raffled prize = prizeStepTwo.get(stepTwo);
                        availablePrizes.add(prize);
                        setOfPrizeForTheDrawling.remove(prize);
                        setTotalWeight();
                        isDrawn = true;
                    }
                }
            } else {
                throw new RuntimeException("Закончились призы для розыгрыша!");
            }
        }
    }

    public void draw() throws RuntimeException {
        draw(1);
    }

    public void addPrizeToDrawingList(Raffled prize, int weight) {
        prize.setWeight(weight);
        setOfPrizeForTheDrawling.add(prize);
        setTotalWeight();
    }

    public Raffled getPrize() throws RuntimeException {
        if (availablePrizes.isEmpty())
            throw new RuntimeException("Нет разыгранных призов доступных для выдачи!");
        return availablePrizes.poll();
    }

    private void setTotalWeight() {
        this.totalWeight = 0;


        for (Raffled prize : this.setOfPrizeForTheDrawling) {
            int currWeight = prize.getWeight();
            if (currWeight > this.totalWeight)
                this.totalWeight = currWeight;
        }

    }
}
