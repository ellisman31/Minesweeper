package configuration.gameConfiguration;

import configuration.Configuration;
import configuration.Table;

public class GameLogic {
    private final Configuration configuration;
    private boolean isGameOver;
    private boolean isCurrentTable;

    public GameLogic(Configuration configuration) {
        this.configuration = configuration;
        this.isGameOver = false;
        this.isCurrentTable = false;
    }

    public Table gameCondition(Table table, GamePatternDesign selectedGamePatternDesign) {
        Table[][] gameTable = getConfiguration().getGameTable();
        for (int row = 0; row <= getConfiguration().getTableRowSize()-1; row++) {
            for (int col = 0; col <= getConfiguration().getTableColSize()-1; col++) {
                Table currentTable = gameTable[row][col];
                if (currentTable == table) {
                    setCurrentTable(true);
                    int mineAroundThePlacement = mineAroundThePlacementCondition(gameTable, row, col);
                    table.getGamePattern().setMineAroundThePlacement(mineAroundThePlacement);
                    checkIfThePlacementIsOnMine(table, selectedGamePatternDesign);
                    break;
                }
            }
            if (isCurrentTable()) {
                setCurrentTable(false);
                break;
            }
        }
        return table;
    }

    private void checkIfThePlacementIsOnMine(Table table, GamePatternDesign selectedGamePatternDesign) {
        if (table.getGamePatternDesign().equals(GamePatternDesign.M)) {
            if (selectedGamePatternDesign.equals(GamePatternDesign.H)) {
                table.setGamePatternDesign(GamePatternDesign.X);
                table.setTablePattern(table.getGamePattern().getPatternForTable(GamePatternDesign.X));
                setGameOver(true);
            }
            if (selectedGamePatternDesign.equals(GamePatternDesign.F)) {
                table.setMineOnMap(table.getMineOnMap() - 1);
                if (table.getMineOnMap() == 0) {
                    setGameOver(true);
                }
            }
        }
    }

    private int mineAroundThePlacementCondition(Table[][] gameTable, int row, int col) {
        boolean isMinusColValid = col - 1 >= 0 && col - 1 <= getConfiguration().getTableColSize();
        boolean isPositiveColValid = col + 1 <= getConfiguration().getTableColSize();
        boolean isMinusRowValid = row - 1 >= 0 && row - 1 <= getConfiguration().getTableRowSize();
        boolean isPositiveRowValid = row + 1 <= getConfiguration().getTableRowSize();
        int mineAroundThePlacement = 0;

        if (isPositiveRowValid) {
            if (gameTable[row+1][col].getGamePatternDesign().equals(GamePatternDesign.M)) {
                mineAroundThePlacement++;
            }
            if (isPositiveColValid) {
                if (gameTable[row][col+1].getGamePatternDesign().equals(GamePatternDesign.M)) {
                    mineAroundThePlacement++;
                }
                if (gameTable[row+1][col+1].getGamePatternDesign().equals(GamePatternDesign.M)) {
                    mineAroundThePlacement++;
                }
            }
            if (isMinusColValid) {
                if (gameTable[row][col-1].getGamePatternDesign().equals(GamePatternDesign.M)) {
                    mineAroundThePlacement++;
                }
                if (gameTable[row+1][col-1].getGamePatternDesign().equals(GamePatternDesign.M)) {
                    mineAroundThePlacement++;
                }
            }

            if (isMinusRowValid) {
                if (gameTable[row-1][col].getGamePatternDesign().equals(GamePatternDesign.M)) {
                    mineAroundThePlacement++;
                }
                if (isPositiveColValid) {
                    if (gameTable[row-1][col+1].getGamePatternDesign().equals(GamePatternDesign.M)) {
                        mineAroundThePlacement++;
                    }
                }
                if (isMinusColValid) {
                    if (gameTable[row-1][col-1].getGamePatternDesign().equals(GamePatternDesign.M)) {
                        mineAroundThePlacement++;
                    }
                }
            }
        }

        return mineAroundThePlacement;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public boolean isCurrentTable() {
        return isCurrentTable;
    }

    public void setCurrentTable(boolean currentTable) {
        isCurrentTable = currentTable;
    }
}
