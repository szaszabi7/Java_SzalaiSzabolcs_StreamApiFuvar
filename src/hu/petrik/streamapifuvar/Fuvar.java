package hu.petrik.streamapifuvar;

public class Fuvar {
    private int taxi_id;
    private String indulas;
    private int idotartam;
    private double tavolsag;
    private double viteldij;
    private double borravalo;
    private String fizetes_modja;

    public int getTaxi_id() {
        return taxi_id;
    }

    public void setTaxi_id(int taxi_id) {
        this.taxi_id = taxi_id;
    }

    public String getIndulas() {
        return indulas;
    }

    public void setIndulas(String indulas) {
        this.indulas = indulas;
    }

    public int getIdotartam() {
        return idotartam;
    }

    public void setIdotartam(int idotartam) {
        this.idotartam = idotartam;
    }

    public double getTavolsag() {
        return tavolsag;
    }

    public void setTavolsag(double tavolsag) {
        this.tavolsag = tavolsag;
    }

    public double getViteldij() {
        return viteldij;
    }

    public void setViteldij(double viteldij) {
        this.viteldij = viteldij;
    }

    public double getBorravalo() {
        return borravalo;
    }

    public void setBorravalo(double borravalo) {
        this.borravalo = borravalo;
    }

    public String getFizetes_modja() {
        return fizetes_modja;
    }

    public void setFizetes_modja(String fizetes_modja) {
        this.fizetes_modja = fizetes_modja;
    }

    public Fuvar(String sor) {
        String[] adat = sor.replace(',', '.').split(";");

        this.taxi_id = Integer.parseInt(adat[0]);
        this.indulas = adat[1];
        this.idotartam = Integer.parseInt(adat[2]);
        this.tavolsag = Double.parseDouble(adat[3]);
        this.viteldij = Double.parseDouble(adat[4]);
        this.borravalo = Double.parseDouble(adat[5]);
        this.fizetes_modja = adat[6];
    }

    @Override
    public String toString() {
        return String.format("ID: %4d, Dátum: %-19s, Időtartam: %4d másodperc, Távolság: %5.2f, mérföld Viteldíj: %5.2f$, Borravaló: %5.2f$, Fizetési mód: %s",
                this.taxi_id,
                this.indulas,
                this.idotartam,
                this.tavolsag,
                this.viteldij,
                this.borravalo,
                this.fizetes_modja);
    }
}
