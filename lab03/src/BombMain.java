public class BombMain {
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
}





