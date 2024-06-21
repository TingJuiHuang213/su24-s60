import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Bomb {
    public static void main(String[] args) {
        int phase = 2;
        if (args.length > 0) {
            phase = Integer.parseInt(args[0]);
        }

        Bomb b = new Bomb();
        if (phase >= 0) {
            b.phase0("39291226");
        }
        if (phase >= 1) {
            b.phase1(new int[]{0, 9, 3, 0, 8});
        }
        if (phase >= 2) {
            b.phase2("1680370154");
        }
    }

    public void phase0(String password) {
        System.err.println("You passed phase 0 with the password \"" + password + "\"");
    }

    public void phase1(int[] password) {
        System.err.println("You passed phase 1 with the password \"" + java.util.Arrays.toString(password) + "\"");
    }

    public void phase2(String password) {
        String[] passwordPieces = password.split(" ");

        Random r = new Random(1337);
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < 100000) {
            numbers.add(r.nextInt());
        }

        boolean correct = false;
        int i = 0;
        for (int number : numbers) {
            if (i == 1336) {
                i = 1337;
            }
            if (i == 1337 && Integer.parseInt(passwordPieces[0]) == number) {
                correct = true;
                System.out.println("Number: " + number);
            }
            i++;
        }
        if (!correct) {
            System.out.println("Phase 2 went BOOM!");
            System.exit(3);
        } else {
            System.err.println("You passed phase 2 with the password \"" + password + "\"");
        }
    }
}
