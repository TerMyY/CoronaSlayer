public class Virus {

    boolean coldResist;
    int coldResistFactor;
    boolean heatResist;
    int heatResistFactor;
    boolean medResist;
    int medResistFactor;
    boolean airEfficient;
    int airEffFactor;
    boolean touchEfficient;
    int touchEffFactor;
    boolean lethal;
    int lethalFactor;
    boolean painful;
    int painFactor;
    boolean plague;
    int plagueFactor;


    Virus(Country country) {

        coldResist = Math.random() > 0.45;
        heatResist = Math.random() > 0.45;
        medResist = Math.random() > 0.45;
        airEfficient = Math.random() > 0.55;
        touchEfficient = Math.random() > 0.55;
        lethal = Math.random() > 0.55;
        plague = Math.random() > 0.75 && lethal;
        coldResistFactor = coldResist && country.climateType == Country.ClimateType.COLD ? 1 : 0;
        heatResistFactor = heatResist && country.climateType == Country.ClimateType.WARM ? 1 : 0;
        medResistFactor = medResist && country.economyType != Country.EconomyType.POOR ? 1 : 0;
        airEffFactor = airEfficient && country.tourismType != Country.TourismType.LOW ? 1 : 0;
        touchEffFactor = touchEfficient && country.densityType != Country.DensityType.LOW ? 1 : 0;
        lethalFactor = lethal ? 1 : 0;
        painFactor = painful ? 1 : 0;
        plagueFactor = plague ? 1 : 0;


    }


}
