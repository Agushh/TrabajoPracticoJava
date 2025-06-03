package festival.modelo.enums;

public enum EquipoFutbol {
    PSG("Ganador Champions","Campeon de liga","Campeon de copa"),
    BARCELONA("Semis champions","Campeon de liga","Campeon de copa"),
    LIVERPOOL("Cuartos Champions","Campeon de liga","Octavos de Copa");

    private final String champions;
    private final String liga;
    private final String copa;

    EquipoFutbol(String champions, String liga, String copa){
        this.champions=champions;
        this.liga=liga;
        this.copa=copa;
    }

    public String getChampions() {
        return champions;
    }

    public String getLiga() {
        return liga;
    }

    public String getCopa() {
        return copa;
    }


    @Override
    public String toString() {
        return champions + ", " + liga + ", " + copa;
    }
}
