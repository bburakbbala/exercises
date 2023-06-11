package ysaProject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) throws URISyntaxException, IOException {
		Scanner in = new Scanner(System.in);
		int araKatmanNoron;
		double momentum, ogrenmeKatsayisi, error;
		int epoch, sec = 0;
		YSA ysa = null;
		do {
			System.out.println("1. Eğitim ve Test");
			System.out.println("2. Çıkış");
			System.out.println("=>");
			sec = in.nextInt();
			switch(sec) {
			case 1:
				System.out.println("Ara Katman Nöron Sayısı: ");
				araKatmanNoron = in.nextInt();
				System.out.println("Momentum: ");
				momentum = in.nextDouble();
				System.out.println("Öğrenme Katsayısı: ");
				ogrenmeKatsayisi = in.nextDouble();
				System.out.println("Min Error: ");
				error = in.nextDouble();
				System.out.println("Max Epoch Sayısı: ");
				epoch = in.nextInt();
				ysa = new YSA(10, araKatmanNoron, ogrenmeKatsayisi, error, epoch);
				ysa.trainAndTestMomentumsuz();
				ysa.writeEpochEpochErrorsToFile("epochEpochErrors.txt");
				ysa.trainAndTestMomentumlu(momentum);
				break;
			}
		} while(sec != 2);
		in.close();
	}

}
