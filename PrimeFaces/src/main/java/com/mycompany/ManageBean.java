/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author DominikKuszmiruk
 */
@Named(value = "manageBean")
@ManagedBean
@RequestScoped
public class ManageBean {

    private int firstNumber;
    private int secondNumber;
    private int result;
    private Date date;
    private ArrayList<Student> students;
    private LineChartModel lineModel;
    
        /**
     * Creates a new instance of ManageBean
     */
    public ManageBean() {
        this.createLineChart();
        students = new ArrayList<>();
        students.add(new Student("Rachel","Green", 5.0));
        students.add(new Student("Phoebe","Buffay", 3.8));
        students.add(new Student("Monica","Geller", 5.0));
        students.add(new Student("Joey","Tribbiani", 5.0));
        students.add(new Student("Chandler","Bing", 4.5));
        students.add(new Student("Ross","Geller", 4.5));

    }
    
    private void createLineChart(){
        lineModel = new LineChartModel();
        
        LineChartSeries sinSeries = new LineChartSeries();
        sinSeries.setLabel("Sine");
        LineChartSeries cosSeries = new LineChartSeries();
        cosSeries.setLabel("Cosine");
        
        for(int i=0; i<=360; i+=10){
            sinSeries.set(i, Math.sin(i));
            cosSeries.set(i, Math.cos(i));
        }
        lineModel.setTitle("Line chart");
        lineModel.addSeries(sinSeries);
        lineModel.addSeries(cosSeries);
        lineModel.setLegendPlacement(LegendPlacement.INSIDE);
        lineModel.setLegendPosition("e");
        
        lineModel.setZoom(true);        
    }
    
    public void addMessageForGrowl(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("growlResult", new FacesMessage("result", getFirstNumber() + "+" + getSecondNumber() + "=" + getResult()));
    }
    
    public void addNumbers(){
        setResult(getFirstNumber() + getSecondNumber());
        addMessageForGrowl();
    }

    /**
     * @return the firstNumber
     */
    public int getFirstNumber() {
        return firstNumber;
    }

    /**
     * @param firstNumber the firstNumber to set
     */
    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    /**
     * @return the secondNumber
     */
    public int getSecondNumber() {
        return secondNumber;
    }

    /**
     * @param secondNumber the secondNumber to set
     */
    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    /**
     * @return the result
     */
    public int getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return Date.from(Instant.now());
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the students
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    /**
     * @return the lineModel
     */
    public LineChartModel getLineModel() {
        return lineModel;
    }

    /**
     * @param lineModel the lineModel to set
     */
    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }
    
}
