package com.isika.prestigeacademy.controllers;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.pie.PieChartOptions;
import org.primefaces.model.charts.polar.PolarAreaChartDataSet;
import org.primefaces.model.charts.polar.PolarAreaChartModel;
import org.primefaces.model.charts.polar.PolarAreaChartOptions;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;


@Named
@RequestScoped
public class StatistiquesController {


	private PieChartModel pieModel;
	private PolarAreaChartModel polarAreaModel;
	private BarChartModel barModel;


	@PostConstruct
	public void init() {
		createPieModel();
		createPolarAreaModel();
		createBarModel();
	}


	private void createPieModel() {
		pieModel = new PieChartModel();
		ChartData data = new ChartData();
	         
	        PieChartDataSet dataSet = new PieChartDataSet();
	        List<Number> values = new ArrayList<>();
	        values.add(85);
	        values.add(11);
	        values.add(4);
	        dataSet.setData(values);
	         
	        List<String> bgColors = new ArrayList<>();
	        bgColors.add("rgb(204, 138, 28)");
	        bgColors.add("rgb(156, 64, 70)");
	        bgColors.add("rgb(73, 113, 130)");
	        dataSet.setBackgroundColor(bgColors);
	         
	        data.addChartDataSet(dataSet);
	        List<String> labels = new ArrayList<>();
	        labels.add("Très Satisfait");
	        labels.add("À améliorer");
	        labels.add("Non satisfait");
	        data.setLabels(labels);
	        
	        
	        
	        PieChartOptions option = new PieChartOptions();
	        Title title = new Title();
	        title.setDisplay(true);
	        title.setText("Satifaction des Partenaires");
	        title.setFontSize(17);
	        option.setTitle(title);
	        
	        pieModel.setOptions(option);
	        pieModel.setData(data);
	    }
	 
	 private void createPolarAreaModel() {
	        polarAreaModel = new PolarAreaChartModel();
	        ChartData data = new ChartData();
	        
	        PolarAreaChartDataSet dataSet = new PolarAreaChartDataSet();
	        List<Number> values = new ArrayList<>();
	        values.add(78);
	        values.add(50);
	        values.add(90);
	        values.add(68);

	        dataSet.setData(values);
	         
	        List<String> bgColors = new ArrayList<>();
	        bgColors.add("rgb(204, 138, 28)");
	        bgColors.add("rgb(156, 64, 70)");
	        bgColors.add("rgb(73, 113, 130)");
	        bgColors.add("rgb(119, 119, 119)");
	        dataSet.setBackgroundColor(bgColors);
	         
	        data.addChartDataSet(dataSet);
	        List<String> labels = new ArrayList<>();
	        labels.add("Sommeliers");
	        labels.add("Gérants d'Hotel");
	        labels.add("Chefs de Rangs");
	        labels.add("Maitres d'Hotel");
	        data.setLabels(labels);
	         
	        PolarAreaChartOptions polarAreaOption = new PolarAreaChartOptions();
	        Title title = new Title();
	        title.setDisplay(true);
	        title.setText("Recrutements avant fin de formation");
	        title.setFontSize(17);
	        polarAreaOption.setTitle(title);
	        
	        polarAreaModel.setOptions(polarAreaOption);
	        polarAreaModel.setData(data);
	    }
	 
	 
	 public void createBarModel() {
	        barModel = new BarChartModel();
	        ChartData data = new ChartData();
	         
	        BarChartDataSet barDataSet = new BarChartDataSet();
	        barDataSet.setLabel(" ");
	         
	        List<Number> values = new ArrayList<>();
	        values.add(65);
	        values.add(59);
	        values.add(80);
	        values.add(81);
	        barDataSet.setData(values);
	        
	        List<String> bgColor = new ArrayList<>();
	        bgColor.add("rgba(204, 138, 28, 0.5)");
	        bgColor.add("rgba(156, 64, 70, 0.5)");
	        bgColor.add("rgba(73, 113, 130, 0.5)");
	        bgColor.add("rgba(119, 119, 119, 0.5)");
	        barDataSet.setBackgroundColor(bgColor);
	         
	        List<String> borderColor = new ArrayList<>();
	        bgColor.add("rgba(204, 138, 28)");
	        bgColor.add("rgba(156, 64, 70)");
	        bgColor.add("rgba(73, 113, 130)");
	        bgColor.add("rgba(119, 119, 119)");
	        barDataSet.setBorderColor(borderColor);
	        barDataSet.setBorderWidth(1);
	         
	        data.addChartDataSet(barDataSet);
	         
	        List<String> labels = new ArrayList<>();
	        labels.add("Participation Evenements");
	        labels.add("Satisfaction Partenaires");
	        labels.add("Recrutement");
	        labels.add("Taux Financement Globale");
	        data.setLabels(labels);
	        barModel.setData(data);
	         
	        //Options
	        BarChartOptions options = new BarChartOptions();
	        CartesianScales cScales = new CartesianScales();
	        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
	        CartesianLinearTicks ticks = new CartesianLinearTicks();
	        ticks.setBeginAtZero(true);
	        linearAxes.setTicks(ticks);
	        cScales.addYAxesData(linearAxes);
	        options.setScales(cScales);
	         
	        Title title = new Title();
	        title.setDisplay(true);
	        title.setText("Indicateurs de Bonne Santé");
	        title.setFontSize(17);
	        options.setTitle(title);

	 
	        barModel.setOptions(options);
	    }






	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}


	public PolarAreaChartModel getPolarAreaModel() {
		return polarAreaModel;
	}


	public void setPolarAreaModel(PolarAreaChartModel polarAreaModel) {
		this.polarAreaModel = polarAreaModel;
	}


	public BarChartModel getBarModel() {
		return barModel;
	}


	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}
	 
	 
	 
	 
	
	
}