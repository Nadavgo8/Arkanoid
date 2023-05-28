// 209702745 Nadav Gonen

import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nadav Gonen nadav.gonen1@live.biu.ac.il
 * @version 1.0
 * @since 2022-09-03
 */
public class Ass6Game {
    /**
     * The main function.
     *
     * @param args - input.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        GameFlow gf = new GameFlow(new AnimationRunner(gui), gui.getKeyboardSensor(), gui);
        List<LevelInformation> levelInformations = new ArrayList<>();
        for (String arg : args) {
            try {
                int temp = Integer.parseInt(arg);
                switch (temp) {
                    case 1:
                        levelInformations.add(new Level1());
                        break;
                    case 2:
                        levelInformations.add(new Level2());
                        break;
                    case 3:
                        levelInformations.add(new Level3());
                        break;
                    case 4:
                        levelInformations.add(new Level4());
                        break;
                    default:
                        break;
                }
            } catch (Exception exception) {
                System.out.println("Error!");
            }
        }
        if (levelInformations.isEmpty()) {
            levelInformations.add(new Level1());
            levelInformations.add(new Level2());
            levelInformations.add(new Level3());
            levelInformations.add(new Level4());
        }
        gf.runLevels(levelInformations);
    }
}
