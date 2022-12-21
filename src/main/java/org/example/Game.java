package org.example;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private static final int INITIAL_TOKENS = 10;
    private int tokens;
    private Car[] cars;
    private Random random;
    private Car selectedCar;
    private Game bet;


    public Game() {
        tokens = INITIAL_TOKENS;
        cars = new Car[]{new Car("Araba 1", "ō͡≡o"), new Car("Araba 2", "ō͡≡o"), new Car("Araba 3", "ō͡≡o"), new Car("Araba 4", "ō͡≡o")};
        random = new Random();
    }

    public void play() {
        while (tokens > 0) {
            bet();

            // Kazanan araba belirle
            Car winningCar = cars[random.nextInt(cars.length)];
            // Kazanan arabayı önde göster
            displayCars(winningCar);
            System.out.println("Kazanan araba: " + winningCar.getName());


            // Bahis yaptığınız araba kazandıysa token kazanın
            if (winningCar.getName().equals(selectedCar.getName())) {
                tokens += 3;
                System.out.println("Tebrikler, 3 token kazandınız!");

            }
            System.out.println("Bakiyeniz: "+tokens);
        }
    }

    public void displayCars(Car winningCar) {
        System.out.println("-------------------");
        System.out.println("Yarış pistinde arabalar hareket ediyor...");
        for (int i = 0; i < cars.length; i++) {
            // Her araba için hareket ettirme görseli oluştur
            StringBuilder sb = new StringBuilder();
            sb.append("  ");
            for (int j = 0; j < i; j++) {
                sb.append(" ");
            }
            sb.append(cars[i].getImage());

            // Kazanan araba varsa, öne çıkar
            if (cars[i].getName().equals(winningCar.getName())) {
                sb.insert(0, "  - -- ---");
            }
            System.out.println(sb.toString());

            // Hareket ettirme görselini 1 saniye bekle
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // Hata olursa, hata mesajı yazdır
                System.out.println("Hata: " + e.getMessage());
            }
        }
        System.out.println("-------------------");
    }



    private void bet() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Bahis yapmak istediğiniz araba numarasını girin (1-4): ");
            int carNumber = scanner.nextInt();

            if (carNumber >= 1 && carNumber <= 4) {
                // Geçerli bir araba numarası seçildi, bahis yapılıyor
                tokens--;
                selectedCar = cars[carNumber - 1];
                break;
            } else {
                // Geçersiz bir araba numarası seçildi, tekrar dene
                System.out.println("Geçersiz araba numarası. Lütfen tekrar deneyin.");
            }
        }
    }
}


