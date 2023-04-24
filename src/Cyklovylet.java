import java.time.LocalDate;
import java.util.Date;

public class Cyklovylet {

    private String cil;
    private double delka;
    private LocalDate datum;

    public Cyklovylet(String cil, double delka, LocalDate datum) {
        this.cil = cil;
        this.delka = delka;
        this.datum = datum;
    }

    public String getCil() {
        return cil;
    }

    public void setCil(String cil) {
        this.cil = cil;
    }

    public double getDelka() {
        return delka;
    }

    public void setDelka(double delka) {
        this.delka = delka;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}
