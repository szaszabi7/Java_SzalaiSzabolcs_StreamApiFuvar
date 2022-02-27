package hu.petrik.streamapifuvar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Fuvar> fuvarLista;

    public static void main(String[] args) {
        beolvasas("fuvar.csv");

        feladat1();
        feladat2(6185);
        feladat3();
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
        System.out.printf("2. feladat: A 6185-ös azonosítójú taxis %d db fuvarból %.2f$ bevételt szerzett\n", fuvarok, bevetel);
    }

    private static void feladat3() {
        double tavolsag = fuvarLista.stream().mapToDouble(Fuvar::getTavolsag).sum();
        System.out.printf("3. feladat: Összesen %.2f mérföldet tettek meg a texisok", tavolsag);
    }
}
