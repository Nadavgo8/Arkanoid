

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Class name: Background.
 */
public class Background implements Sprite {
    private Color color;
    private String levelName;

    /**
     * A constructor.
     *
     * @param color     - the color.
     * @param levelName - the level's name.
     */
    public Background(Color color, String levelName) {
        this.color = color;
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        int level = 0;

        switch (this.levelName) {
            case "Direct Hit":
                d.setColor(Color.BLUE);
                d.drawText((int) GameLevel.WIDTH / 2 - 65, 90, "Score a penalty!", 20);
                level = 1;
                // The scoreboard.
                d.setColor(Color.BLACK);
                d.fillRectangle(350, 20, 130, 50);
                d.setColor(Color.red);
                d.drawText(395, 35, "88:40", 15);
                d.drawText(390, 65, "0 : 0", 25);
                break;

            case "Wide Easy":
                d.setColor(Color.gray); //The stands.
                d.fillRectangle(20, 20, 760, 200);
                int count = 0;
                for (int j = 30; j < 230; j += 25) { //Drawing the fans.
                    for (int i = 32; i < (GameLevel.WIDTH - 20); i += 23) {
                        if ((count % 2) == 0) {
                            d.setColor(Color.BLACK);
                        } else {
                            d.setColor(Color.YELLOW);
                        }
                        d.fillCircle(i, j, 10);
                        count++;
                    }
                }
                //The commercials.
                d.setColor(Color.LIGHT_GRAY);
                d.fillRectangle(20, 220, 800, 30);
                d.setColor(Color.cyan.darker());
                d.drawText(40, 240,
                        "         Bar - Ilan University            Influencing  the  tomorrow,  today", 25);
                d.setColor(Color.BLUE);
                d.drawText((int) GameLevel.WIDTH / 2 - 200, 290,
                        "Overcome the defence! Don't worry, the fans wil get hit", 20);
                level = 2;
                // The scoreboard.
                d.setColor(Color.BLACK);
                d.fillRectangle(350, 20, 130, 50);
                d.setColor(Color.red);
                d.drawText(395, 35, "89:36", 15);
                d.drawText(390, 65, "1 : 0", 25);
                break;
            case "Green 3":
                level = 3;
                // The scoreboard.
                d.setColor(Color.BLACK);
                d.fillRectangle(350, 20, 130, 50);
                d.setColor(Color.red);
                d.drawText(395, 35, "89:56", 15);
                d.drawText(390, 65, "2 : 0", 25);
                d.setColor(Color.BLUE);
                d.drawText(300, 150, "Stop Hapoel's counter attack!", 20);
                d.setColor(Color.white);
                d.fillRectangle(20, 450, 800, 2);
                d.drawCircle(400, 450, 100);
                break;
            case "Final Four":
                // The scoreboard.
                d.setColor(Color.BLACK);
                d.fillRectangle(350, 20, 130, 50);
                d.setColor(Color.red);
                d.drawText(395, 35, "92:33", 15);
                d.drawText(390, 65, "2 : 0", 25);
                d.setColor(Color.BLUE);
                d.drawText(275, 150, "Stop Hapoel from scoring all of them!", 20);

                d.setColor(Color.white);
                d.fillRectangle((int) GameLevel.WIDTH / 2 - 150, 500, 2, 100);
                d.fillRectangle((int) GameLevel.WIDTH / 2 + 150, 500, 2, 100);
                d.fillRectangle((int) GameLevel.WIDTH / 2 - 150, 500, 300, 2);
                d.fillRectangle((int) GameLevel.WIDTH / 2 - 300, 350, 2, 250);
                d.fillRectangle((int) GameLevel.WIDTH / 2 + 300, 350, 2, 250);
                d.fillRectangle((int) GameLevel.WIDTH / 2 - 300, 350, 600, 2);
                d.fillCircle((int) GameLevel.WIDTH / 2, 450, 4);
                break;
            default:
                System.out.println("Error");
                break;
        }
        if (level == 1 || level == 2) {
            // Drawing the goal and lines.
            d.setColor(Color.white);
            d.fillRectangle((int) GameLevel.WIDTH / 2 - 200, 100, 5, 150);
            d.fillRectangle((int) GameLevel.WIDTH / 2 + 200, 100, 5, 150);
            d.fillRectangle((int) GameLevel.WIDTH / 2 - 200, 100, 400, 5);
            d.fillRectangle(20, 250, 800, 2);
            d.fillRectangle((int) GameLevel.WIDTH / 2 - 300, 250, 2, 150);
            d.fillRectangle((int) GameLevel.WIDTH / 2 + 300, 250, 2, 150);
            d.fillRectangle((int) GameLevel.WIDTH / 2 - 300, 400, 600, 2);
        }
    }

    @Override
    public void timePassed() {

    }
}
