package configuration;

import configuration.gameConfiguration.GamePatternDesign;
import util.Util;

public class Configuration extends ConfigurationBase {

    private final int tableRowSize;
    private final int tableColSize;
    private int plantRandomMineCounter = 0;

    public Configuration() {
        super();
        this.tableRowSize = getTable().getRowSize();
        this.tableColSize = getTable().getColSize();
        initializeTable();
    }

    @Override
    public void initializeTable() {
        for (int row = 0; row < getTableRowSize(); row++) {
            for (int col = 0; col < getTableColSize(); col++) {
                initializeRandomMineAfterTableGeneration(getPlantRandomMineCounter(), row, col);
            }
        }
    }

    @Override
    public void printTable() {
        System.out.println("Mine left: " + getTable().getMineOnMap());
        for (int row = 0; row < getTableRowSize(); row++) {
            for (int col = 0; col < getTableColSize(); col++) {
                Table currentTable = getGameTable()[row][col];
                if (!currentTable.getGamePatternDesign().equals(GamePatternDesign.M)) {
                    System.out.print(currentTable.getGamePattern().getPatternForTable(currentTable.getGamePatternDesign()));
                } else if (currentTable.getGamePatternDesign().equals(GamePatternDesign.M) && isGameOver()) {
                    System.out.print(currentTable.getGamePattern().getPatternForTable(currentTable.getGamePatternDesign()));
                }
                else {
                    System.out.print(currentTable.getGamePattern().getPatternForTable(GamePatternDesign.D));
                }
            }
            System.out.println(" ");
        }
    }

    @Override
    public void initializeRandomMineAfterTableGeneration(int mineCounter, int row, int col) {
        Table table = new Table();
        table.setGamePatternDesign(GamePatternDesign.D);
        if (Util.getRandom().nextBoolean()) {
            if (getTable().getMineOnMap() > getPlantRandomMineCounter()) {
                table.setGamePatternDesign(GamePatternDesign.M);
                setPlantRandomMineCounter(getPlantRandomMineCounter()+1);
            }
        }
        addTableToGameTable(table, row, col);
    }

    public int getTableRowSize() {
        return tableRowSize;
    }

    public int getTableColSize() {
        return tableColSize;
    }

    public int getPlantRandomMineCounter() {
        return plantRandomMineCounter;
    }

    public void setPlantRandomMineCounter(int plantRandomMineCounter) {
        this.plantRandomMineCounter = plantRandomMineCounter;
    }
}
