package configuration.gameConfiguration;

public class GamePattern {

    private int mineAroundThePlacement;

    public GamePattern() {
    }

    public String getPatternForTable(GamePatternDesign gamePatternDesign) {
        String pattern = "";
        if (gamePatternDesign.equals(GamePatternDesign.D)) {
            pattern = "[   ]";
        } else if (gamePatternDesign.equals(GamePatternDesign.F)) {
            pattern = "[ F ]";
        } else if(gamePatternDesign.equals(GamePatternDesign.H)) {
            pattern = "[ " + getMineAroundThePlacement() + " ]";
        } else if (gamePatternDesign.equals(GamePatternDesign.M)) {
            pattern = "[ * ]";
        }
        else if (gamePatternDesign.equals(GamePatternDesign.X)) {
            pattern = "[ X ]";
        }

        return pattern;
    }

    public int getMineAroundThePlacement() {
        return mineAroundThePlacement;
    }

    public void setMineAroundThePlacement(int mineAroundThePlacement) {
        this.mineAroundThePlacement = mineAroundThePlacement;
    }
}
