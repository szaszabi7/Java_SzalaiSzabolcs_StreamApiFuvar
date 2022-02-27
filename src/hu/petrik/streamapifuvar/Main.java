package hu.petrik.streamapifuvar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static List<Fuvar> fuvarLista;

    public static void main(String[] args) {
        beolvasas("fuvar.csv");

        feladat1();
        feladat2(6185);
        feladat3();
        feladat4();
        feladat5();
        feladat6(4261);
        feladat7();
    }

    private static void beolvasas(String file) {
        fuvarLista = new ArrayList<>();

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            br.readLine();
            String sor = br.readLine();
            while (sor != null) {
                fuvarLista.add(new Fuvar(sor));
                sor = br.readLine();
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void feladat1() {
        System.out.println("1. feladat: " + fuvarLista.stream().count() + " db utazás került feljegyzésre");
    }

    private static void feladat2(int taxiId) {
        double bevetel = fuvarLista.stream().filter(fuvar -> fuvar.getTaxi_id() == taxiId).mapToDouble(fuvar -> fuvar.getViteldij() + fuvar.getBorravalo()).sum();
        long fuvarok = fuvarLista.stream().filter(fuvar -> fuvar.getTaxi_id() == taxiId).count();
        System.out.printf("2. feladat: A %d azonosítójú taxis %d db fuvarból %.2f$ bevételt szerzett\n", taxiId, fuvarok, bevetel);
    }

    private static void feladat3() {
        double tavolsag = fuvarLista.stream().mapToDouble(Fuvar::getTavolsag).sum();
        System.out.printf("3. feladat: Összesen %.2f mérföldet tettek meg a texisok\n", tavolsag);
    }

    private static void feladat4() {
        System.out.println("4. feladat: " + fuvarLista.stream().max(Comparator.comparingInt(Fuvar::getIdotartam)).get());
    }

    private static void feladat5() {
        System.out.println("5. feladat: " + fuvarLista.stream()
                .max((fuvar1, fuvar2) -> (int) (fuvar1.getBorravalo() / fuvar1.getViteldij()
                        - fuvar2.getBorravalo() / fuvar2.getViteldij())).get());
    }

    private static void feladat6(int taxiId) {
        double km = fuvarLista.stream().filter(fuvar -> fuvar.getTaxi_id() == taxiId).mapToDouble(Fuvar::getTavolsag).sum() * 1.6;
        System.out.printf("6. feladat: A %d azonosítójú taxi összesn %.2f km-et tett meg\n", taxiId, km);
    }

    private static void feladat7() {
        List<Fuvar> hibasFuvarok = fuvarLista.stream().filter((fuvar) -> fuvar.getIdotartam() > 0 && fuvar.getViteldij() > 0 && fuvar.getTavolsag() == 0).toList();
        long db = hibasFuvarok.stream().count();
        double idotartam = hibasFuvarok.stream().mapToDouble(Fuvar::getIdotartam).sum();
        double bevetel = hibasFuvarok.stream().mapToDouble(fuvar -> fuvar.getViteldij() + fuvar.getBorravalo()).sum();
        System.out.printf("""
                7. feladat: Híbás fuvarok száma: %d
                            Hibás fuvarok időtartama: %.2f
                            Hibás fuvarok bevétele: %.2f
                """, db, idotartam, bevetel);
    }
}
