package plant;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import weather.Weather;

public class Plant {
    private WEATHER weather= new  WEATHER();
    private File PLANT_IN;
    private double PT,PG,PD,ROW_SPC,DIAT,
            dn,rm,di,tb,swfac,EMP1,EMP2,nb,N,LAI,P1,SLA,dwc,dwr,
            dwf,dw,fc,intot,w,wc,wr,wf,Lfmax,dLAI;
    private static double Int=0;
    private int veg_days=0;
    private int rep_days=0;
    private static Boolean matured=false;

    public void setDi(double di) {
        this.di = di;
    }

    public void setDIAT(double DIAT) {
        this.DIAT = DIAT;
    }

    public void setDn(double dn) {
        this.dn = dn;
    }

    public void setDw(double dw) {
        this.dw = dw;
    }

    public void setEMP1(double EMP1) {
        this.EMP1 = EMP1;
    }

    public void setEMP2(double EMP2) {
        this.EMP2 = EMP2;
    }

    public void setPD(double PD) {
        this.PD = PD;
    }

    public void setLAI(double LAI) {
        this.LAI = LAI;
    }

    public void setN(double n) {
        N = n;
    }

    public void setPG(double PG) {
        this.PG = PG;
    }

    public void setDwc(double dwc) {
        this.dwc = dwc;
    }

    public void setNb(double nb) {
        this.nb = nb;
    }

    public void setDwf(double dwf) {
        this.dwf = dwf;
    }

    public void setDwr(double dwr) {
        this.dwr = dwr;
    }

    public void setFc(double fc) {
        this.fc = fc;
    }

    public void setP1(double P1) {
        this.P1 = P1;
    }

    public void setPT(double PT) {
        this.PT = PT;
    }

    public void setRm(double rm) {
        this.rm = rm;
    }

    public void setROW_SPC(double ROW_SPC) {
        this.ROW_SPC = ROW_SPC;
    }

    public void setSLA(double SLA) {
        this.SLA = SLA;
    }

    public void setIntot(double intot) {
        this.intot = intot;
    }

    public void setSwfac(double swfac) {
        this.swfac = swfac;
    }

    public void setTb(double tb) {
        this.tb = tb;
    }

    public void setW(double w) {
        this.w = w;
    }

    public void setWc(double wc) {
        this.wc = wc;
    }

    public void setWr(double wr) {
        this.wr = wr;
    }

    public void setLfmax(double lfmax) {
        this.Lfmax = lfmax;
    }

    public double getDi() {
        return di;
    }

    public double getDIAT() {
        return DIAT;
    }

    public double getDn() {
        return dn;
    }

    public double getDw() {
        return dw;
    }

    public double getDwc() {
        return dwc;
    }

    public double getPD() {
        return PD;
    }

    public double getDwf() {
        return dwf;
    }

    public double getDwr() {
        return dwr;
    }

    public double getEMP1() {
        return EMP1;
    }

    public double getEMP2() {
        return EMP2;
    }

    public double getFc() {
        return fc;
    }
	public double getWf() {
		return wf;
	}

	public void setWf(double wf) {
		this.wf = wf;
	}

    public double getIntot() {
        return intot;
    }

    public double getLAI() {
        return LAI;
    }

    public double getN() {
        return N;
    }

    public double getNb() {
        return nb;
    }

    public double getPG() {
        return PG;
    }

    public double getP1() {
        return P1;
    }

    public double getPT() {
        return PT;
    }

    public double getRm() {
        return rm;
    }

    public double getROW_SPC() {
        return ROW_SPC;
    }

    public double getSLA() {
        return SLA;
    }

    public double getSwfac() {
        return swfac;
    }

    public double getTb() {
        return tb;
    }

    public double getW() {
        return w;
    }

    public double getWc() {
        return wc;
    }

    public double getWr() {
        return wr;
    }

    public double getLfmax() {
        return Lfmax;
    }
    private void PTS(){
        PT=1-0.0025*Math.pow(0.25* weather.getTmin()+0.75* weather.getTmax()-26,2);
    }
    private void LAIS() {
        double a;
        if(N<=Lfmax) {
            dn = rm * PT;
            N +=dn;
            a = Math.exp(EMP2 * (N - nb));
            System.out.println(swfac);
            dLAI = swfac * PT * PD * EMP1 * dn * (a / (1 + a));
            veg_days++;
        }
        else {
            di = ((weather.getTmax() + weather.getTmin()) / 2) - tb;
            Int += di;
            System.out.println(Int);
            dLAI = dLAI - (PD * di * SLA * P1);
            rep_days++;
        }
        double w2;
        w2=PG/2;
        dwc=w2-wc;
        dwr=w2-wr;
        dwf=PG;
        dw=dwf+dwr+dwc;
        dw*=PD;
    }


    private void PGS(){
    double y1=1.5-0.768*Math.pow((Math.pow(( ROW_SPC*0.01),2)*PD),0.1);
    PG=(weather.getSRAD()/PD)*(1.0-Math.exp(-y1*LAI));
    }
    public void Initialization(File PLANT_IN){
        System.out.println("Plant's Initialization called");
        try{
            this.PLANT_IN=PLANT_IN;
            Scanner scanner = new Scanner(this.PLANT_IN);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Use regular expressions to match the variable name and value
                Matcher matcher = Pattern.compile("([a-zA-Z_]+):\\s*(\\S+)").matcher(line);
                if (matcher.find()) {
                    if(matcher.group(1).equals("EMP_ONE")){
                        EMP1=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("Int")){
                        Int=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("EMP_TWO")){
                        EMP2=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("fc")){
                        fc=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("intot")){
                        intot=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("LAI")){
                        LAI=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("Lfmax")){
                        Lfmax=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("N")){
                        N=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("nb")){
                        nb=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("P1")){
                        P1=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("PD")){
                        PD=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("rm")){
                        rm=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("SLA")){
                        SLA=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("tb")){
                        tb=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("wc")){
                        wc=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("veg_days")){
                        veg_days=Integer.parseInt(matcher.group(2));
                    }
                    if(matcher.group(1).equals("rep_days")){
                        rep_days=Integer.parseInt(matcher.group(2));
                    }
                    if(matcher.group(1).equals("w")){
                        w=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("swfac")){
                        swfac=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("wr")) {
                        wr = Double.parseDouble(matcher.group(2));
                    }
                }
            } scanner.close();
        }catch (Exception e){
            e.getStackTrace();
        }
    
    }
    public void rateCalculation(){
        PTS();
        PGS();
        LAIS();
    }
    public void Integration(){
        w+=dw;
        wc+=dwc;
        setWf(getWf() + dwf);
        wr+=dwr;
        LAI+=dLAI;
        N+=dn;
        if(Int>=intot){
            System.out.println("Plant matured!!!");
            matured=true;
        }
    }
    public void output(){
        try{
           FileWriter fileWriter= new FileWriter(this.PLANT_IN);
           PrintWriter printWriter= new PrintWriter(fileWriter);
            printWriter.println("EMP_ONE:" + EMP1);
            printWriter.println("EMP_TWO:" + EMP2);
            printWriter.println("intot:" + intot);
            printWriter.println("fc:" + fc);
            printWriter.println("LAI:" + LAI);
            printWriter.println("w:" + w);
            printWriter.println("wc:" + wc);
            printWriter.println("wr:" + wr);
            printWriter.println("PD:" + PD);
            printWriter.println("Lfmax:" + Lfmax);
            printWriter.println("N:" + N);
            printWriter.println("nb:" + nb);
            printWriter.println("P1:" + P1);
            printWriter.println("rm:" + rm);
            printWriter.println("SLA:" + SLA);
            printWriter.println("tb:" + tb);
            printWriter.println("veg_days:" + veg_days);
            printWriter.println("rep_days:" + rep_days);
            printWriter.println("Int:" + Int);
            printWriter.close();
        }catch (Exception e){
            e.getStackTrace();
        }

    }

    public void close() {
        PLANT_IN=null;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Do you want to save the output? (yes/no)");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                System.out.println("Output saved.");
            } else {
                System.out.println("Output not saved.");
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
