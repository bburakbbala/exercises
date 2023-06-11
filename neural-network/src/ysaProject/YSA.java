package ysaProject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TransferFunctionType;


public class YSA {
	private static File dosya;
	NeuralNetwork<BackPropagation> sinirselAg;
	DataSet data;
	DataSet foldedTrainData = new DataSet(700);
	DataSet[] dataSet;
	MomentumBackpropagation mbp;
	BackPropagation bp;
	int kFolds;
	int araKatmanNoronSayisi;
	double learningRate;
	double error;
	int epoch;
	double[] epochEpochErrors;
	double toplamEgitimHata;
	double toplamTestHata;
	
	public YSA(int kFolds, int araKatmanNoronSayisi, double learningRate, double error,
			int epoch) throws URISyntaxException {
		this.kFolds = kFolds;
		this.araKatmanNoronSayisi = araKatmanNoronSayisi;
		this.learningRate = learningRate;
		this.error = error;
		this.epoch = epoch;
		this.toplamEgitimHata = 0;
		this.toplamTestHata = 0;
		
		dosya = new File(YSA.class.getResource("data.txt").toURI());
		data = DataSet.createFromFile(dosya.getPath(), 3, 1, ";"); // 3 input 1 output
		data.shuffle(); // for random selection
		sinirselAg = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 3, araKatmanNoronSayisi, 1);
		// 3 input aranoron 1 output
		
	}
	
	public void trainAndTestMomentumsuz() throws URISyntaxException, IOException {
		initBackPropagation();
		
		learn(this.bp);
		
		System.out.println("Momentumsuz:");
		System.out.println("K-Fold Ortalama Egitim Hata: " + toplamEgitimHata / kFolds);
		System.out.println("K-Fold Ortalama Test Hata: " + toplamTestHata / kFolds);
		//sinirselAg.save("trained.nnet");
	}

	private void learn(BackPropagation bp) {
		DataSet[] folds = createKFolds(data, this.kFolds);
		toplamEgitimHata = 0;
		toplamTestHata = 0;
		
		// dataSet[0] egitim
		// dataSet[1] test
		for(int i = 0; i < kFolds; i++) {
			sinirselAg.randomizeWeights(); // ağ sıfırlanır
			dataSet = folds[i].createTrainingAndTestSubsets(0.7, 0.3); // %70 train %30 test
			sinirselAg.learn(dataSet[0]); // egitim
			foldedTrainData.addAll(dataSet[0]);
			toplamEgitimHata += bp.getTotalNetworkError();
			toplamTestHata += test(sinirselAg, dataSet[1]); // test
		}
	}
	
	public void writeEpochEpochErrorsToFile(String filename) throws IOException {
		initBackPropagation();
		
		// epoch epoch hata
		File file = new File(filename);
		FileWriter fileWriter = new FileWriter(file);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		epochEpochErrors = new double[epoch];
		for(int i = 0; i < epoch; i++) {
			sinirselAg.getLearningRule().doOneLearningIteration(foldedTrainData);
			if(i == 0)
				epochEpochErrors[i] = 1;
			else
				epochEpochErrors[i] = sinirselAg.getLearningRule().getTotalNetworkError();
			printWriter.println(epochEpochErrors[i]);
		}
		printWriter.close();
	}

	private void initBackPropagation() {
		// egitim parametreleri momentumsuz
		bp = new MomentumBackpropagation();
		bp.setMaxIterations(epoch);
		bp.setMaxError(error);
		bp.setLearningRate(learningRate);
		sinirselAg.setLearningRule(bp);
	}
	
	public void trainAndTestMomentumlu(double momentum) throws URISyntaxException {
		initMomentumBackpropagation(momentum);
		
		learn(this.mbp);
		
		System.out.println("Momentumlu:");
		System.out.println("K-Fold Ortalama Egitim Hata: " + toplamEgitimHata / kFolds);
		System.out.println("K-Fold Ortalama Test Hata: " + toplamTestHata / kFolds);
		//sinirselAg.save("trainedMomentum.nnet");
	}

	private void learn(MomentumBackpropagation mbp) {
		DataSet[] folds = createKFolds(data, this.kFolds);
		toplamEgitimHata = 0;
		toplamTestHata = 0;
		
		for(int i = 0; i < kFolds; i++) {
			sinirselAg.randomizeWeights(); // ağ sıfırlanır
			dataSet = folds[i].createTrainingAndTestSubsets(0.7, 0.3);
			sinirselAg.learn(dataSet[0]); // egitim
			toplamEgitimHata += mbp.getTotalNetworkError();
			toplamTestHata += test(sinirselAg, dataSet[1]); // test
		}
	}

	private void initMomentumBackpropagation(double momentum) {
		// egitim parametreleri momentum
		mbp = new MomentumBackpropagation();
		mbp.setMaxIterations(epoch);
		mbp.setMaxError(error);
		mbp.setLearningRate(learningRate);
		mbp.setMomentum(momentum);
		sinirselAg.setLearningRule(mbp);
	}
	
	private double test(NeuralNetwork<BackPropagation> nn, DataSet testVeriSeti) {
		double toplamHata = 0;
		for(DataSetRow dr : testVeriSeti) {
			nn.setInput(dr.getInput());
			nn.calculate();
			toplamHata += mse(dr.getDesiredOutput(), nn.getOutput());
		}
		return toplamHata / testVeriSeti.size();
	}
	
	private double mse(double[] beklenen, double[] cikti) {
		double birSatirVeriHata = 0;
		for(int i = 0; i < cikti.length; i++) {
			birSatirVeriHata += Math.pow(beklenen[i] - cikti[i], 2);
		}
		return birSatirVeriHata / cikti.length;
	}
	
	private DataSet[] createKFolds(DataSet dataset, int k) {
		DataSet[] folds = new DataSet[k];
		int rows = dataset.size() / k;
		for(int i = 0, j = -1; i < dataset.size(); i++) {
			if(i % rows == 0) {
				j++;
				folds[j] = new DataSet(3, 1); // input output
			}
			folds[j].add(dataset.getRowAt(i));
		}
		return folds;
	}
}
