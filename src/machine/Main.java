package machine;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 120, 540, 9, 550);

        coffeeMachine.start();

        while (coffeeMachine.getState() != State.SHUTDOWN) {
            coffeeMachine.inputProcess(scanner.next());
        }

    }
}

