package machine;

import static machine.DisplayGraphic.*;

public class CoffeeMachine {
    private int milk = 540;
    private int coffeeBeans = 120;
    private int water = 400;
    private int cups = 9;
    private int money = 550;
    private String input;
    private State state = State.READY;

    DisplayGraphic displayGraphic = new DisplayGraphic();

    CoffeeMachine (int milk, int coffeeBeans, int water, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cups = cups;
        this.money = money;
    }

    State getState() {
       return this.state;
    }

    void start() {
        System.out.println(MAIN_TITTLE);
         displayGraphic.showProgressBarr(100);
        whatMachineHas();
    }

    void stop() {
        System.out.println(GOODBYE_TITTLE);
        this.state = State.SHUTDOWN;
    }

    private void ready() {
        this.state = State.READY;
        System.out.println();
        System.out.println(MAIN_MENU);
    }

    public void inputProcess(String input) {
        this.input = input;

        switch (this.state) {
            case READY:
                readyCommandProcess();
                break;
            case WATER_INPUT:
            case MILK_INPUT:
            case BEANS_INPUT:
            case CUPS_INPUT:
                fill();
                break;
            case BUY_CHOICE:
                buy();
                break;
            default:
                System.out.println("!!Unknown input state!! Please enter a correct button number");
                ready();
                break;
        }
    }

    public void readyCommandProcess() {
        System.out.println();
        switch (input) {
            case "1": // buy
                buy();
                break;
            case "2": // fill
                fill();
                break;
            case "3": // take
                take();
                break;
            case "4": // remaining
                whatMachineHas();
                break;
            case "5": // exit
                stop();
                break;
            default:
                System.out.println("!!Unknown command!! Please enter a correct button number");
                ready();
                break;
        }
    }

    private void buy() {
        switch (this.state) {
            case READY:
                System.out.print(COFFEE_CHOISE);
                this.state = State.BUY_CHOICE;
                break;
            case BUY_CHOICE:
                boolean enough = isEnough(this.input);

                switch (this.input) {
                    case "1": // espresso
                        if (enough) {
                            this.water -= 250;
                            this.coffeeBeans -= 16;
                            this.cups -= 1;
                            this.money += 4;
                        }
                        break;
                    case "2": // latte
                        if (enough) {
                            this.water -= 350;
                            this.milk -= 75;
                            this.coffeeBeans -= 20;
                            this.cups -= 1;
                            this.money += 7;
                        }
                        break;
                    case "3": // cappuccino
                        if (enough) {
                            this.water -= 200;
                            this.milk -= 100;
                            this.coffeeBeans -= 12;
                            this.cups -= 1;
                            this.money += 6;
                        }
                        break;
                    case "4":
                        break;
                    default:
                        System.out.println("Unknown buy command");
                        break;
                }
                ready();
                break;
            default:
                System.out.println("Unknown buy state");
                ready();
                break;
        }
    }

    private void fill() {
        switch (this.state) {
            case READY:
                System.out.print("Write how many ml of water do you want to add: ");
                System.out.println();
                this.state = State.WATER_INPUT;
                break;
            case WATER_INPUT:
                this.water += Integer.parseInt(this.input);
                System.out.print("Write how many ml of milk do you want to add: ");
                System.out.println();
                this.state = State.MILK_INPUT;
                break;
            case MILK_INPUT:
                this.milk += Integer.parseInt(this.input);
                System.out.print("Write how many grams of coffee beans do you want to add: ");
                System.out.println();
                this.state = State.BEANS_INPUT;
                break;
            case BEANS_INPUT:
                this.coffeeBeans += Integer.parseInt(this.input);
                System.out.print("Write how many disposable cups of coffee do you want to add: ");
                System.out.println();
                this.state = State.CUPS_INPUT;
                break;
            case CUPS_INPUT:
                this.cups += Integer.parseInt(this.input);
                ready();
                break;
            default:
                System.out.println("Unknown fill state");
                ready();
                break;
        }
    }

    private void take() {
        System.out.println("I gave you $" + this.money);
        this.money = 0;
        ready();
    }

    private void whatMachineHas() {
        System.out.println("The coffee machine has:");
        System.out.println(this.water + " of water");
        System.out.println(this.milk + " of milk");
        System.out.println(this.coffeeBeans + " of coffee beans");
        System.out.println(this.cups + " of disposable cups");
        System.out.println("$" + this.money + " of money");
        ready();
    }

    private boolean isEnough(String type) {
        boolean enough = false;

        int waterLimit;
        int milkLimit;
        int beansLimit;

        switch (type) {
            case "1": // espresso
                waterLimit = 250;
                milkLimit = 0;
                beansLimit = 16;
                break;
            case "2": // latte
                waterLimit = 350;
                milkLimit = 75;
                beansLimit = 20;
                break;
            case "3": // cappuccino
                waterLimit = 200;
                milkLimit = 100;
                beansLimit = 12;
                break;
            default:
                return false;
        }
        if (this.water < waterLimit) {
            System.out.println("Sorry, not enough water!");
        } else if (this.milk < milkLimit) {
            System.out.println("Sorry, not enough milk!");
        } else if (this.coffeeBeans < beansLimit) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (this.cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            enough = true;
            System.out.println("Start making you a coffee!");
            displayGraphic.showProgressBarr(200);
            System.out.println("You coffee is ready!");
            System.out.println(COFFEE_CUP);
        }

        return enough;
    }

}
