package configuration;

import configuration.gameConfiguration.GamePattern;
import configuration.gameConfiguration.GamePatternDesign;

public class Table {

    private int rowSize;
    private int colSize;
    private int mineOnMap;
    private String tablePattern;
    private GamePatternDesign gamePatternDesign;
    private final GamePattern gamePattern;

    public Table() {
        this.rowSize = 9;
        this.colSize = 9;
        this.mineOnMap = 10;
        this.gamePattern = new GamePattern();
    }

    public int getRowSize() {
        return rowSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public int getColSize() {
        return colSize;
    }

    public void setColSize(int colSize) {
        this.colSize = colSize;
    }

    public int getMineOnMap() {
        return mineOnMap;
    }

    public void setMineOnMap(int mineOnMap) {
        this.mineOnMap = mineOnMap;
    }

    public String getTablePattern() {
        return tablePattern;
    }

    public void setTablePattern(String tablePattern) {
        this.tablePattern = tablePattern;
    }

    public GamePatternDesign getGamePatternDesign() {
        return gamePatternDesign;
    }

    public void setGamePatternDesign(GamePatternDesign gamePatternDesign) {
        this.gamePatternDesign = gamePatternDesign;
    }

    public GamePattern getGamePattern() {
        return gamePattern;
    }
}
