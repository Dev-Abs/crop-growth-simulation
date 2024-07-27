package weather;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Weather {
    protected static double PAR,RAIN,SRAD,Tmax,Tmin;
    protected static int DATE;
    private File WEATHER_IN;

    public void Initialization(File WEATHER_IN){
        System.out.println("Weather's Initialization called!");
        try{
            Scanner scanner = new Scanner(WEATHER_IN);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Use regular expressions to match the variable name and value
                Matcher matcher = Pattern.compile("([a-zA-Z_]+):\\s*(\\S+)").matcher(line);
                if (matcher.find()) {
                    if(matcher.group(1).equals("Tmin")){
                        Tmin=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("Tmax")){
                        Tmax=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("RAIN")){
                        RAIN=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("SRAD")){
                        SRAD=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("PAR")){
                        PAR=Double.parseDouble(matcher.group(2));
                    }
                    if(matcher.group(1).equals("DATE")){
                        DATE=Integer.parseInt(matcher.group(2));
                    }
                }
            }
            scanner.close();
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    public void setTmax(double tmax) {
        Tmax = tmax;
    }

    public void setTmin(double tmin) {
        Tmin = tmin;
    }

    public void setSRAD(double SRAD) {
        this.SRAD = SRAD;
    }

    public void setDATE(int DATE) {
        this.DATE = DATE;
    }

    public void setPAR(double PAR) {
        this.PAR = PAR;
    }

    public void setRAIN(double RAIN) {
        this.RAIN = RAIN;
    }

    public double getTmin() {
        return Tmin;
    }

    public double getTmax() {
        return Tmax;
    }

    public double getSRAD() {
        return SRAD;
    }

    public double getPAR() {
        return PAR;
    }

    public double getRAIN() {
        return RAIN;
    }

    public int getDATE() {
        return DATE;
    }

    public void rateCalculation(){

    }
    public void close(){

    }

}
