package byow.lab13;
import java.awt.Font;
import byow.Core.RandomUtils;
import edu.princeton.cs.introcs.StdDraw;

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

        //TODO: Initialize random number generator
    }

    public String generateRandomString(int n) {
        StringBuilder s = new StringBuilder();

        while (n > 0) {
            s.append((char) (rand.nextInt(26) + 'a'));
            n -= 1;
        }
        //TODO: Generate random string of letters of length n
        return s.toString();
    }

    public void drawFrame(String s) {
//        Font font = new Font("Monaco", Font.BOLD, 30);
//        StdDraw.setFont(font);
        show(s,"Watch", s.length());
        sleep(500);
        StdDraw.clear();
        show("","Watch", s.length());
        StdDraw.show();
        sleep(500);



        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
    }

    public void flashSequence(String letters) {

        sleep(1000);

        for (char c : letters.toCharArray()) {
            show(Character.toString(c),"Watch", letters.length());
            sleep(1000);

            StdDraw.clear();
            show( "","Watch", letters.length());
            StdDraw.show();
            sleep(500);
        }


        //TODO: Display each character in letters, making sure to blank the screen between letters
    }

    public String solicitNCharsInput(int n) {
        StringBuilder inputString = new StringBuilder();
        int n2 = n;
        StdDraw.clear();
        show("", "Type", n2);
        StdDraw.show();
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                inputString.append(StdDraw.nextKeyTyped());

                StdDraw.clear();
                show(inputString.toString(), "Type", n2);
                StdDraw.show();
//                show(inputString.toString());
                n -= 1;
                if (n == 0) {
                    return inputString.toString();
                }
            }

        }


        //TODO: Read n letters of player input
    }

    public  void  sleep(int millis) {
        try {
            // 让当前线程睡眠1000毫秒（1秒）
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // 当线程被中断时需要处理的异常
            Thread.currentThread().interrupt(); // 重新设置中断状态
            System.err.println("睡眠被中断: " + e.getMessage());
        }
    }



    private void show(String s,String status,int n) {
        StdDraw.clear();
        StdDraw.text(5,height -1,"Round: " + n);
        StdDraw.textLeft(width / 2 -3, height - 1, status);
        StdDraw.textRight(width, height -1,"you are star!");
        StdDraw.text(width / 2,height / 2,s);
        StdDraw.show();
    }



    public void startGame() {
        int n = 1;

        while (true) {
//            show("回合" + n);
//            sleep(1000);

            String gameString = generateRandomString(n);
            drawFrame(gameString);
            flashSequence(gameString);

            String inputString =  solicitNCharsInput(n);
            if (inputString.equals(gameString)) {

                show(inputString + " = " + gameString,"Watch", n);
                sleep(1000);

                show("You Win","Watch", n);
                sleep(1000);

                n += 1;

            } else {
                show(inputString + " != "  + gameString,"Watch", n);
                sleep(1000);
                show("You lose","Watch", n);
                sleep(1000);
                return;
            }
        }


        //TODO: Set any relevant variables before the game starts

        //TODO: Establish Engine loop
    }

}
