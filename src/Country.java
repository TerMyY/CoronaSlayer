import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Country {

    static ArrayList<Country> countries = new ArrayList<Country>();
    static double difficulty = Game.difficulty == Game.DIFFICULTY.HARD ? 1.75 : Game.difficulty == Game.DIFFICULTY.MEDIUM ? 1.5 : 1.35;
    Random r = new Random();
    double researchBuff = 1;
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!Game.isPaused) {
                Country destinationCountry;
                destinationCountry = countries.get( r.nextInt(countries.size()) );
                if (destinationCountry.tourismType == TourismType.HIGH && Math.random() > 0.75)
                    flyTo(destinationCountry);
                else if (destinationCountry.tourismType == TourismType.MEDIUM && Math.random() > 0.85)
                    flyTo(destinationCountry);
                else if (destinationCountry.tourismType == TourismType.LOW && Math.random() > 0.95)
                    flyTo(destinationCountry);
                if (!isDown) {

                    if(infected > 0 && virus.painful)
                    Game.vaccine += ( (10 / difficulty) * 0.7 ) * researchBuff;
                    else if(infected > 0) { Game.vaccine += ( (10 / difficulty) * 0.85 ) * researchBuff; }
                    else if(dead >= infected + population) {}
                    else { Game.vaccine += ( (10 / difficulty) ) * researchBuff; }

                }
            }

        }
    });
    Thread travelThread = new Thread(new Runnable() {
        @Override
        public void run() {
            timer.start();
        }
    });

    enum EconomyType {

        POOR,
        MEDIUM,
        RICH

    }

    EconomyType economyType;

    double economyFormula = economyType == EconomyType.POOR ? ( Math.random() * 50 ) + 35 : economyType == EconomyType.MEDIUM ? ( Math.random() * 335) + 175 : ( Math.random() * 1775 ) + 1050; // in USD millions
    double economy = Math.round( economyFormula * 100.0 ) / 100.0;

    enum DensityType {

        LOW,
        MEDIUM,
        HIGH

    }

    DensityType densityType;

    double densityFormula = densityType == DensityType.LOW ? ( Math.random() * 4.75 ) + 3 : densityType == DensityType.MEDIUM ? ( Math.random() * 7.75 ) + 5 : ( Math.random() * 35.75 ) + 17;
    double density = Math.round(densityFormula * 10.0) / 10.0;

    enum ClimateType {

        COLD,
        MEDIUM,
        WARM

    }

    ClimateType climateType;

    double climateFormula = climateType == ClimateType.COLD ? ( Math.random() * -55 ) + -15: climateType == ClimateType.MEDIUM ? ( Math.random() * -7 ) + 17  : ( Math.random() * 33 ) + 15;
    double climate = Math.round(climateFormula * 10.0) / 10.0;

    enum SizeType {

        SMALL,
        MEDIUM,
        LARGE

    }

    SizeType sizeType;

    double size;

    enum HumidityType {

        DRY,
        MEDIUM,
        WET

    }

    HumidityType humidityType;

    double humidityFormula = humidityType == HumidityType.DRY ? ( Math.random() * 175 ) + 35: humidityType == HumidityType.MEDIUM ? ( Math.random() * 1150 ) + 335 : ( Math.random() * 3355 ) + 1335;
    double humidity = Math.round(humidityFormula * 10.0) / 10.0;

    enum TourismType {

        LOW,
        MEDIUM,
        HIGH

    }

    TourismType tourismType;

    double tourismFormula = tourismType == TourismType.LOW ? ( Math.random() * 3 ) + 1 : tourismType == TourismType.MEDIUM ? ( Math.random() * 13) + 5 : ( Math.random() * 33) + 15;
    double tourism = Math.round(tourismFormula * 10.0) / 10.0;

    String name;

    double population;
    double infected;
    double dead;


    Country(String name, EconomyType economyType, DensityType densityType, ClimateType climateType, SizeType sizeType, double size, HumidityType humidityType, TourismType tourismType) {

        this.name = name;
        this.economyType = economyType;
        this.densityType = densityType;
        this.climateType = climateType;
        this.sizeType = sizeType;
        this.humidityType = humidityType;
        this.tourismType = tourismType;
        this.size = size;
        population = density * size;
        countries.add(this);
        travelThread.start();
        Game.population += population;

    }

    Timer spreadTimer;
    double spreadSpeed;
    double deadSpeed;
    boolean isInfected;
    boolean lockdown;
    boolean isDown;
    Virus virus;

    void setInfected() {

        Virus virus = new Virus(this);
        spreadSpeed  =  ( ( Math.pow(density, 2) +
                (climate * virus.coldResistFactor) + (climate * virus.heatResistFactor) +
                (economy * virus.medResistFactor) + (tourism * virus.airEffFactor) +
                (humidity * virus.touchEffFactor) + (dead * virus.plagueFactor)) ) / 3;
        deadSpeed = (float) (density * virus.lethalFactor) / 15;
        infected = 3;
        spreadTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Game.isPaused) {

                    if (population < 0) {population = 0;}
                    if (infected < 0) {infected = 0;}

                    if(infected > 0) {
                        double died = (Math.random() * deadSpeed);
                        dead += died;
                        infected -= died;
                        deadSpeed =  ( deadSpeed + infected / population );
                    }

                    if(population > 0 && infected > 0) {
                        infected += spreadSpeed;
                        population -= infected;
                        Game.population -= Math.min(Game.population, infected);
                        spreadSpeed = ( spreadSpeed + (Math.pow(infected, difficulty) * density) / population );
                    }

                }
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                spreadTimer.start();
            }
        });
        thread.start();
        isInfected = true;
        this.virus = virus;

    }

    void flyTo(Country country) {

        if(isInfected && !country.isInfected && Math.random() > 0.55 && !lockdown) {

            country.setInfected();

        }

    }


}
