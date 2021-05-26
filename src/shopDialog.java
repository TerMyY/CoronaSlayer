import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class shopDialog extends JDialog {

    JPanel panel = new JPanel();
    FlowLayout flowLayout = new FlowLayout();
    JLabel description = new JLabel();
    JLabel virus = new JLabel();
    MenuButton back = new MenuButton("Back");
    MenuButton lockdown = new MenuButton("Lockdown-35♛");
    MenuButton research = new MenuButton("Research-35♛");
    MenuButton advancedMedicine = new MenuButton("Advanced medicine-35♛");
    MenuButton burnCorpses = new MenuButton("Burn corpses-15♛");
    MenuButton painkillers = new MenuButton("Painkillers-15♛");
    MenuButton coldResistRemove = new MenuButton("No cold resist-15♛");
    MenuButton heatResistRemove = new MenuButton("No heat resist-15♛");
    MenuButton medResistRemove = new MenuButton("No med resist-15♛");
    MenuButton airEffRemove = new MenuButton("No air efficiency-15♛");
    MenuButton touchEffRemove = new MenuButton("No touch efficiency-15♛");

    ActionListener backListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false); dispose();
        }
    };

    shopDialog(Country country) {

        ActionListener lockdownListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuButton button = (MenuButton) e.getSource();
                if(country.name.equals("China") && Game.score > 195) { country.lockdown = true; Game.score -= 195; button.setVisible(false); }
                if(!country.name.equals("China") && Game.score > 55) { country.lockdown = true; Game.score -= 55; button.setVisible(false); }

            }
        };

        ActionListener researchListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuButton button = (MenuButton) e.getSource();
                if(Game.score > 35) { country.researchBuff = 5; Game.score -= 35; button.setVisible(false); }
            }
        };
        ActionListener advancedMedicineListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuButton button = (MenuButton) e.getSource();
                if(Game.score > 35) { country.virus.lethal = false; Game.score -= 35;  button.setVisible(false); burnCorpses.setVisible(false); country.virus.plague = false; }

            }
        };
        ActionListener burnCorpsesListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuButton button = (MenuButton) e.getSource();
                if(Game.score > 15) { country.virus.plague = false; Game.score -= 15; button.setVisible(false); }
            }
        };
        ActionListener painKillersListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuButton button = (MenuButton) e.getSource();
                if(Game.score > 15) { country.virus.painful = false; Game.score -= 15; button.setVisible(false); }
            }
        };
        ActionListener coldResistRemoveListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuButton button = (MenuButton) e.getSource();
                if(Game.score > 15) { country.virus.coldResist = false; Game.score -= 15; button.setVisible(false); }
            }
        };
        ActionListener heatResistRemoveListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuButton button = (MenuButton) e.getSource();
                if(country.virus == null || !country.virus.plague) button.setVisible(false);
                if(Game.score > 15) { country.virus.heatResist = false; Game.score -= 15; button.setVisible(false); }
            }
        };
        ActionListener medResistRemoveListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuButton button = (MenuButton) e.getSource();
                if(Game.score > 15) { country.virus.medResist = false; Game.score -= 15; button.setVisible(false); }
            }
        };
        ActionListener airEffRemoveListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuButton button = (MenuButton) e.getSource();
                if(Game.score > 15) { country.virus.airEfficient = false; Game.score -= 15; button.setVisible(false); }
            }
        };
        ActionListener touchEffRemoveListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuButton button = (MenuButton) e.getSource();
                if(Game.score > 15) { country.virus.touchEfficient = false; Game.score -= 15; button.setVisible(false); }
            }
        };
        String climate = country.climateType == Country.ClimateType.COLD ? " cold climate;" :
                country.climateType == Country.ClimateType.MEDIUM ? " medium climate;" : " warm climate;";
        String economy = country.economyType == Country.EconomyType.POOR ? " poor economy;" :
                country.economyType == Country.EconomyType.MEDIUM ? " medium economy;" : " rich economy;";
        String density = country.densityType == Country.DensityType.LOW ? " low density;" :
                country.densityType == Country.DensityType.MEDIUM ? " medium density;" : " high density;";
        String size = country.sizeType == Country.SizeType.SMALL ? " small size;" :
                country.sizeType == Country.SizeType.MEDIUM ? " medium size;" : " large size;";
        String humidity = country.humidityType == Country.HumidityType.DRY ? " dry humidity;" :
                country.humidityType == Country.HumidityType.MEDIUM ? " medium humidity;" : " wet humidity;";
        String tourism = country.tourismType == Country.TourismType.LOW ? " low tourism;" :
                country.tourismType == Country.TourismType.MEDIUM ? " medium tourism;" : " high tourism;";
        String isLockdown = country.lockdown ? " on lockdown;" : "";
        String isResearch = country.researchBuff != 1 ? " in research;" : "";
        if(country.name.equals("China")) { lockdown.setText("Lockdown-195♛"); }
        description.setText("<html>" + country.name + ":" + size + economy + "<br>" + climate + density + "<br>" + humidity + tourism + isLockdown + isResearch);
        if(country.virus != null) {

            String coldResist = country.virus.coldResist ? " cold resistant;" : "";
            String heatResist = country.virus.heatResist ? " heat resistant;" : "";
            String medResist = country.virus.medResist ? " resistant to medicament;" : "";
            String airEfficient = country.virus.airEfficient ? " air efficient;" : "";
            String touchEfficient = country.virus.touchEfficient ? " touch efficient;" : "";
            String painful = country.virus.painful ? " painful;" : "";
            String lethal = country.virus.lethal ? " lethal;" : "";
            String plague = country.virus.plague ? " plague;" : "";
            virus.setText("<html>" + "Virus:" + coldResist + heatResist + medResist + "<br>" + airEfficient + touchEfficient + painful + "<br>" + lethal + plague);

        }
        description.setBackground(Main.BACKGROUND_COLOR);
        description.setFont(MenuButton.MONOFONT_THIN);
        description.setForeground(MenuButton.CRIMSON);
        virus.setBackground(Main.BACKGROUND_COLOR);
        virus.setFont(MenuButton.MONOFONT_THIN);
        virus.setForeground(MenuButton.CRIMSON);
        lockdown.addActionListener(lockdownListener);
        back.addActionListener(backListener);
        research.addActionListener(researchListener);
        advancedMedicine.addActionListener(advancedMedicineListener);
        burnCorpses.addActionListener(burnCorpsesListener);
        painkillers.addActionListener(painKillersListener);
        coldResistRemove.addActionListener(coldResistRemoveListener);
        heatResistRemove.addActionListener(heatResistRemoveListener);
        medResistRemove.addActionListener(medResistRemoveListener);
        airEffRemove.addActionListener(airEffRemoveListener);
        touchEffRemove.addActionListener(touchEffRemoveListener);
        lockdown.setVisible(!country.lockdown);
        research.setVisible(country.researchBuff == 1);
        lockdown.setFont(MenuButton.MONOFONT_THIN);
        back.setFont(MenuButton.MONOFONT_THIN);
        research.setFont(MenuButton.MONOFONT_THIN);
        advancedMedicine.setFont(MenuButton.MONOFONT_THIN);
        burnCorpses.setFont(MenuButton.MONOFONT_THIN);
        painkillers.setFont(MenuButton.MONOFONT_THIN);
        coldResistRemove.setFont(MenuButton.MONOFONT_THIN);
        heatResistRemove.setFont(MenuButton.MONOFONT_THIN);
        medResistRemove.setFont(MenuButton.MONOFONT_THIN);
        airEffRemove.setFont(MenuButton.MONOFONT_THIN);
        touchEffRemove.setFont(MenuButton.MONOFONT_THIN);
        lockdown.setPreferredSize(new Dimension(495, 150));
        research.setPreferredSize(lockdown.getPreferredSize());
        advancedMedicine.setPreferredSize(lockdown.getPreferredSize());
        burnCorpses.setPreferredSize(lockdown.getPreferredSize());
        painkillers.setPreferredSize(lockdown.getPreferredSize());
        coldResistRemove.setPreferredSize(lockdown.getPreferredSize());
        heatResistRemove.setPreferredSize(lockdown.getPreferredSize());
        medResistRemove.setPreferredSize(lockdown.getPreferredSize());
        airEffRemove.setPreferredSize(lockdown.getPreferredSize());
        touchEffRemove.setPreferredSize(lockdown.getPreferredSize());
        burnCorpses.setVisible(country.virus != null && country.virus.plague);
        advancedMedicine.setVisible(country.virus != null && country.virus.lethal);
        painkillers.setVisible(country.virus != null && country.virus.painful);
        coldResistRemove.setVisible(country.virus != null && country.virus.coldResist);
        heatResistRemove.setVisible(country.virus != null && country.virus.heatResist);
        medResistRemove.setVisible(country.virus != null && country.virus.medResist);
        airEffRemove.setVisible(country.virus != null && country.virus.airEfficient);
        touchEffRemove.setVisible(country.virus != null && country.virus.touchEfficient);
        panel.add(description);
        panel.add(virus);
        panel.add(lockdown);
        panel.add(research);
        panel.add(advancedMedicine);
        panel.add(burnCorpses);
        panel.add(painkillers);
        panel.add(coldResistRemove);
        panel.add(heatResistRemove);
        panel.add(medResistRemove);
        panel.add(airEffRemove);
        panel.add(touchEffRemove);
        panel.add(back);
        panel.setBackground(Main.BACKGROUND_COLOR);
        panel.setLayout(flowLayout);
        add(panel);
        setUndecorated(true);
        setResizable(false);
        setModal(true);
        setSize(Main.SCREEN_SIZE);
        setVisible(true);
        setTitle("Corona Slayer");
        setLocationRelativeTo(null);

        Timer updater = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(country.virus != null) {

                    String coldResist = country.virus.coldResist ? " cold resistant;" : "";
                    String heatResist = country.virus.heatResist ? " heat resistant;" : "";
                    String medResist = country.virus.medResist ? " resistant to medicament;" : "";
                    String airEfficient = country.virus.airEfficient ? " air efficient;" : "";
                    String touchEfficient = country.virus.touchEfficient ? " touch efficient;" : "";
                    String painful = country.virus.painful ? " painful;" : "";
                    String lethal = country.virus.lethal ? " lethal;" : "";
                    String plague = country.virus.plague ? " plague;" : "";
                    burnCorpses.setVisible(country.virus.plague);
                    advancedMedicine.setVisible(country.virus.lethal);
                    painkillers.setVisible(country.virus.painful);
                    coldResistRemove.setVisible(country.virus.coldResist);
                    heatResistRemove.setVisible(country.virus.heatResist);
                    medResistRemove.setVisible(country.virus.medResist);
                    airEffRemove.setVisible(country.virus.airEfficient);
                    touchEffRemove.setVisible(country.virus.touchEfficient);
                    virus.setText("<html>" + "Virus:" + coldResist + heatResist + medResist + "<br>" + airEfficient + touchEfficient + painful + "<br>" + lethal + plague);

                }


                lockdown.setVisible(!country.lockdown);
                research.setVisible(country.researchBuff == 1);
                String isResearch = country.researchBuff != 1 ? " in research;" : "";
                String isLockdown = country.lockdown ? " on lockdown;" : "";
                description.setText("<html>" + country.name + ":" + size + economy + "<br>" + climate + density + "<br>" + humidity + tourism + isLockdown + isResearch);

            }
        });
        Thread updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                updater.start();
            }
        });
        updateThread.start();

    }


}
