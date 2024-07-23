package PlusWorld;

import byowTools.TileEngine.TERenderer;
import byowTools.TileEngine.TETile;
import byowTools.TileEngine.Tileset;

public class PlusWorld {

    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        addPlus(world, 30, 15, 5);

        ter.renderFrame(world);
    }

    /**
     * Adds a plus shape to the world at the specified position.
     *
     * @param world The world to draw in.
     * @param centerX The x-coordinate of the center of the plus.
     * @param centerY The y-coordinate of the center of the plus.
     * @param size The size of the plus (length of each arm).
     */
    public static void addPlus(TETile[][] world, int centerX, int centerY, int size) {
        TETile tile = Tileset.FLOWER;

        // Draw the center part (size x size)
        for (int x = centerX - size / 2; x <= centerX + size / 2; x++) {
            for (int y = centerY - size / 2; y <= centerY + size / 2; y++) {
                world[x][y] = tile;
            }
        }

        // Draw the vertical arms (each arm should be 5x5)
        for (int x = centerX - size / 2; x <= centerX + size / 2; x++) {
            for (int y = centerY - size - size / 2; y < centerY - size / 2; y++) {
                world[x][y] = tile;
            }
            for (int y = centerY + size / 2 + 1; y <= centerY + size + size / 2; y++) {
                world[x][y] = tile;
            }
        }

        // Draw the horizontal arms (each arm should be 5x5)
        for (int y = centerY - size / 2; y <= centerY + size / 2; y++) {
            for (int x = centerX - size - size / 2; x < centerX - size / 2; x++) {
                world[x][y] = tile;
            }
            for (int x = centerX + size / 2 + 1; x <= centerX + size + size / 2; x++) {
                world[x][y] = tile;
            }
        }
    }
}
