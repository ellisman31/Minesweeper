package configuration.placementConfiguration;

import configuration.Configuration;
import configuration.Table;
import util.Util;
import configuration.gameConfiguration.GamePatternDesign;

import java.util.Arrays;

public class Placement extends PlacementBase {

    public Placement(Configuration configuration) {
        super(configuration);
    }

    @Override
    public void askForPlacement() {
        checkIfPlacementValid(getConfiguration().getTableRowSize(), getRowPlacementName());
        checkIfPlacementValid(getConfiguration().getTableColSize(), getColPlacementName());
        Table currentTable = getConfiguration().getGameTable()[getRowPlacement()-PLACEMENT_CORRECTOR][getColPlacement()-PLACEMENT_CORRECTOR];
        applyGameAndTablePatternDesign(currentTable);
    }

    private GamePatternDesign askForGamePatternDesign() {
        setValidPlacementName(false);
        while (!isValidPlacementName()) {
            System.out.print("Please type what game pattern would you like to place down. FLAG[F] - NORMAL[H]: ");
            setPlacementName(Util.getScanner().next().toUpperCase());
            setValidPlacementName(getPlacementName().equals(GamePatternDesign.F.name()) || getPlacementName().equals(GamePatternDesign.H.name()));
            if (isValidPlacementName()) {
                break;
            }
        }
        return GamePatternDesign.valueOf(getPlacementName());
    }

    private void checkIfPlacementValid(int maximumLength, String positionName) {
        boolean isPlacementValid = true;
        int placement = 0;
        setValidInput(false);

        while (!isValidInput()) {
            if (positionName.equals(getRowPlacementName())) {
                if (!isPlacementValid) {
                    System.out.print("The row placement is invalid! Please re-enter your placement: ");
                } else {
                    System.out.print("Please enter a row placement: ");
                }
            } else if (positionName.equals(getColPlacementName())) {
                if (!isPlacementValid) {
                    System.out.print("The col placement is invalid! Please re-enter your placement: ");
                } else {
                    System.out.print("Please enter a col placement: ");
                }
            }
            //CHECK IF THE INPUT IS INVALID.
            if (Util.getScanner().hasNextInt()) {
                placement = Util.getScanner().nextInt();
            } else {
                Util.getScanner().next();
            }

            isPlacementValid = placement > 0 && placement <= maximumLength;
            setValidInput(isPlacementValid);
            if (isValidInput()) {
                if (positionName.equals(getRowPlacementName())) {
                    setRowPlacement(placement);
                } else if (positionName.equals(getColPlacementName())) {
                    setColPlacement(placement);
                }
                break;
            }
        }
    }

    private void applyGameAndTablePatternDesign(Table currentTable) {
        if (!currentTable.getGamePatternDesign().equals(GamePatternDesign.H) &&
                !currentTable.getGamePatternDesign().equals(GamePatternDesign.F)) {
            GamePatternDesign gamePatternDesign = askForGamePatternDesign();
            currentTable = getGameLogic().gameCondition(currentTable, gamePatternDesign);
            if (!currentTable.getGamePatternDesign().equals(GamePatternDesign.X)) {
                if (gamePatternDesign.equals(GamePatternDesign.F)) {
                    currentTable.setGamePatternDesign(GamePatternDesign.F);
                    currentTable.setTablePattern(getConfiguration().getTable().getGamePattern().getPatternForTable(GamePatternDesign.F));
                } else if (gamePatternDesign.equals(GamePatternDesign.H)) {
                    currentTable.setGamePatternDesign(GamePatternDesign.H);
                    currentTable.setTablePattern(currentTable.getGamePattern().getPatternForTable(GamePatternDesign.H));
                }
            }
            Table[][] gameTable = getConfiguration().getGameTable();
            //SET CURRENT MINE ON MAP FOR ALL TABLES TO HAVE THE SAME VALUES.
            int currentMineOnMap = currentTable.getMineOnMap();
            Arrays.stream(gameTable).forEach(i -> Arrays.stream(i).forEach(j -> j.setMineOnMap(currentMineOnMap)));

            gameTable[getRowPlacement()-PLACEMENT_CORRECTOR][getColPlacement()-PLACEMENT_CORRECTOR] = currentTable;
            getConfiguration().setTable(currentTable);
            getConfiguration().setGameTable(gameTable);
        } else {
            System.out.println("The given placement is already selected! Please try it again!");
            askForPlacement();
        }
    }
}