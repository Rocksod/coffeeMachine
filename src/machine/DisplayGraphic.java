package machine;

public class DisplayGraphic {
    public static final String MAIN_TITTLE = " ----------------------------------------------------------------\n" +
            "|               Coffee Machine By Smetanin Kirill                |\n" +
            " ----------------------------------------------------------------";
    public static final String MAIN_MENU = " -------------------------------- \n" +
            "|1:        Buy Coffee            |\n" +
            " -------------------------------- \n" +
            "|2:      Fill Ingredient         |\n" +
            " -------------------------------- \n" +
            "|3:        Take Money            |\n" +
            " -------------------------------- \n" +
            "|4:     Status of Ingredient     |\n" +
            " -------------------------------- \n" +
            "|5:          Exit                |\n" +
            " -------------------------------- \n";

    public static final String COFFEE_CHOISE = " -------------------------------- \n" +
            "|1:        Espresso              |\n" +
            " -------------------------------- \n" +
            "|2:         Latte                |\n" +
            " -------------------------------- \n" +
            "|3:       Cappuccino             |\n" +
            " -------------------------------- \n" +
            "|4:        Main menu             |\n" +
            " -------------------------------- \n";

    public static final String GOODBYE_TITTLE = " ----------------------------------------------------------------\n" +
            "|                Have a nice day!  =)                            |\n" +
            " ----------------------------------------------------------------";

    public static final String COFFEE_CUP = "   ( (\n" +
            "    ) )\n" +
            "  ........\n" +
            "  |      |]\n" +
            "  \\      /    \n" +
            "   `----'";

    public void progressPercentage(int remain, int total) {
        if (remain > total) {
            throw new IllegalArgumentException();
        }
        int maxBareSize = 10; // 10unit for 100%
        int remainPercent = ((100 * remain) / total) / maxBareSize;
        char defaultChar = '-';
        String icon = "*";
        String bare = new String(new char[maxBareSize]).replace('\0', defaultChar) + "]";
        StringBuilder bareDone = new StringBuilder();

        bareDone.append("[");
        for (int i = 0; i < remainPercent; i++) {
            bareDone.append(icon);
        }
        String bareRemain = bare.substring(remainPercent, bare.length());
        System.out.print("\r" + bareDone + bareRemain + " " + remainPercent * 10 + "%");
        if (remain == total) {
            System.out.print("\n");
        }
    }

    public void
    showProgressBarr(int speed) {
        System.out.println();
        for (int i = 0; i <= 200; i = i + 20) {
            progressPercentage(i, 200);
            try {
                Thread.sleep(speed);
            } catch (Exception e) {
            }
        }
        System.out.println();
    }
}
