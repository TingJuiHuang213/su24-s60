package MemoryGame;

import byowTools.RandomUtils;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    /** The width of the window of this game. */
    private int width;
    /** The height of the window of this game. */
    private int height;
    /** The current round the user is on. */
    private int round;
    /** The Random object used to randomly generate Strings. */
    private Random rand;
    /** Whether or not the game is over. */
    private boolean gameOver;
    /** Whether or not it is the player's turn. Used in the last section of the
     * spec, 'Helpful UI'. */
    private boolean playerTurn;
    /** The characters we generate random Strings from. */
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    /** Encouraging phrases. Used in the last section of the spec, 'Helpful UI'. */
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
            "You got this!", "You're a star!", "Go Bears!",
            "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        long seed = Long.parseLong(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, long seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        this.rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(CHARACTERS[rand.nextInt(CHARACTERS.length)]);
        }
        return sb.toString();
    }

    public void drawFrame(String s) {
        /* Take the input string S and display it at the center of the screen,
         * with the pen settings given below. */
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.text(this.width / 2, this.height / 2, s);

        /* If the game is not over, display encouragement, and let the user know if they
         * should be typing their answer or watching for the next round. */
        if (!gameOver) {
            Font fontSmall = new Font("Monaco", Font.BOLD, 20);
            StdDraw.setFont(fontSmall);
            StdDraw.line(0, this.height - 2, this.width, this.height - 2);
            StdDraw.textLeft(0, this.height - 1, "Round: " + this.round);
            if (this.playerTurn) {
                StdDraw.text(this.width / 2, this.height - 1, "Type!");
            } else {
                StdDraw.text(this.width / 2, this.height - 1, "Watch!");
            }
            int randomEncouragement = RandomUtils.uniform(this.rand, ENCOURAGEMENT.length);
            StdDraw.textRight(this.width, this.height - 1, ENCOURAGEMENT[randomEncouragement]);
        }
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        for (int i = 0; i < letters.length(); i++) {
            drawFrame(String.valueOf(letters.charAt(i)));
            StdDraw.pause(1000);
            StdDraw.clear(Color.BLACK);
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        StringBuilder sb = new StringBuilder(n);
        drawFrame(""); // 清空屏幕
        for (int i = 0; i < n; i++) {
            while (!StdDraw.hasNextKeyTyped()) {
                // 等待用戶輸入一個字符
            }
            char c = StdDraw.nextKeyTyped();
            sb.append(c);
            drawFrame(sb.toString()); // 每次輸入一個字符後，顯示已輸入的字符
        }
        return sb.toString();
    }

    public void startGame() {
        this.gameOver = false;
        this.round = 1;

        while (!gameOver) {
            drawFrame("Round: " + round);
            StdDraw.pause(1500);

            String roundString = generateRandomString(round);
            flashSequence(roundString);

            this.playerTurn = true;
            String playerInput = solicitNCharsInput(round);
            this.playerTurn = false;

            if (!playerInput.equals(roundString)) {
                gameOver = true;
            } else {
                round += 1;
            }
        }

        drawFrame("Game Over! You made it to round: " + this.round);
    }
}
