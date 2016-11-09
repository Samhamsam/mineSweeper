package de.htwg.minesweeper.aview.tui;

        import com.google.gson.Gson;
        import de.htwg.minesweeper.aview.wui.MineSweeperDO;
        import de.htwg.minesweeper.controller.IController;
import util.observer.Event;
import util.observer.IObserver;

import java.util.Arrays;
import java.util.List;

public class TUI implements IObserver {
    private IController controller;

    public TUI(IController controller) {
        this.controller = controller;
        controller.addObserver(this);
    }

    public boolean answerOptions(String answer) {
        String answer2 = answer;
        boolean continu = true;
        List list = Arrays.asList(answer.split(","));
        if("c".equals(list.get(0))) {
            answer2 = "c";
        }

        byte var6 = -1;
        switch(answer2.hashCode()) {
            case 99:
                if(answer2.equals("c")) {
                    var6 = 3;
                }
                break;
            case 104:
                if(answer2.equals("h")) {
                    var6 = 2;
                }
                break;
            case 110:
                if(answer2.equals("n")) {
                    var6 = 1;
                }
                break;
            case 113:
                if(answer2.equals("q")) {
                    var6 = 0;
                }
        }

        switch(var6) {
            case 0:
                continu = false;
                this.controller.exitGame();
                break;
            case 1:
                this.controller.newGame();
                break;
            case 2:
                this.controller.hilfe();
                break;
            case 3:
                this.runSettings(list);
                break;
            default:
                this.controller.startGame(answer);
        }

        return continu;
    }

    private void runSettings(List<String> list) {
        this.controller.setRowAndColumnAndBombs(list, true);
        this.controller.notifyIfSettingsSet();
    }

    public void update(Event e) {
        this.printTui();
    }

    public StringBuilder printField(String[][] field) {
        StringBuilder result = new StringBuilder();

        for(int j = 0; j < this.controller.getRow(); ++j) {
            for(int i = 0; i < this.controller.getColumn(); ++i) {
                result.append(field[i][j]).append(" ");
            }

            result.append("\n");
        }

        return result;
    }

    public String printTui() {
        int status = this.controller.getStatusCode();
        if(status == 5) {
            return "NOT A NUMBER!";
        } else {
            new StringBuilder();
            StringBuilder result = new StringBuilder();
            StringBuilder field = this.printField(this.controller.getFeldText());
            if(!"".equals(this.controller.getFieldPosition())) {
                result.append("You typed: " + this.controller.getFieldPosition() + "\n");
            }

            if(status == 7) {
                result.append("You set row/column to: " + this.controller.getRow() + " and bombs to: " + this.controller.getNumberOfMines());
            }

            result.append(field.toString());
            if(status == 1 || status == 0) {
                result.append("Type: x,x | x is a number between 0 and 9(row, column):\n");
            }

            if(status == 2) {
                result.append("You Lost!");
            }

            if(status == 3) {
                result.append("You Won! " + this.controller.getTimeWon() + " Points!");
            }

            if(status == 4) {
                result.append(this.controller.getHelpText());
            }

            if(status == 2 || status == 3) {
                result.append("New Game? Type: n");
            }

            if(status == 6) {
                result.append("Set number of column/row and mines:");
            }

            return result.toString();
        }
    }

    public String getJson() {
        // new DO ->
        final MineSweeperDO mineSweeperDO = new MineSweeperDO();
        final Gson gson = new Gson();
        return gson.toJson(mineSweeperDO);
    }
}
