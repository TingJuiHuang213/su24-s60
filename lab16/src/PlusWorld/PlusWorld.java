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

        // 在 (30, 15) 位置繪製一個大小為3的加號
        addPlus(world, 30, 15, 3);

        ter.renderFrame(world);
    }

    private static void addPlus(TETile[][] world, int centerX, int centerY, int size) {
        for (int x = centerX - size; x <= centerX + size; x++) {
            world[x][centerY] = Tileset.WALL;
        }
        for (int y = centerY - size; y <= centerY + size; y++) {
            world[centerX][y] = Tileset.WALL;
        }
    }
}
