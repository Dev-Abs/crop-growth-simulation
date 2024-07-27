package soil;

import java.io.*;

public class SoilWater {private WEATHER weather=new WEATHER();
    private PLANT plant= new PLANT();
    private File Soil_IN;

    private double SWFAC1,SWFAC2,ALB,EPA,INF,ST,WP,FC,S,SWC_ADJ,ETP,g,Rn,FCP,
            THE,ROF,DP,WTABLE,DWT,Train,TIRR,TESA,
            TEPA,TDRN,TINF,POT_INF,DRN,TROF,IRR,DRNP,ESP,EPP,ESA,CN,WPP,SWC,STP,EEQ,Tmed,a;
    private static int count=0;
    public void RUNOFF(){
        S=254*((100/CN)-1);

        if(count>0){
            if(POT_INF>0.2*S){
                ROF=(Math.pow(POT_INF-0.2*S,2))/(POT_INF+0.8*S);
            }
            else {
                ROF=0;
            }
        }
        count++;
    }
    public void STRESS(){
        THE=WP+0.75*(FC-WP);
        if (SWC < WP) {
                SWFAC1 = 0.0;}
        else if (SWC > THE)
                SWFAC1 = 1.0;
        else{
                SWFAC1 = (SWC - WP) / (THE - WP);
        SWFAC1 = Math.max(Math.min(SWFAC1, 1.0), 0.0);}

    }
    public void RC(){

    }
    public void DRAIN(){
        DRN=(SWC-FC)*DRNP;
    }
    public void ETPS(){

        ALB=0.1*Math.exp(-0.7*plant.getLAI())+0.2*(1-Math.exp(-0.7*plant.getLAI()));
        Tmed=0.6* weather.getTmax()+0.4* weather.getTmin();
        EEQ=weather.getSRAD()*(4.88*Math.pow(10,-3)-4.37*Math.pow(10,-3)*ALB)*(Tmed+29);
        ETP=a * (Rn / (g + (1 - a) * (Rn)));
        ESP=ETP*Math.exp(-0.7*plant.getLAI());
        EPP=ETP*(1-Math.exp(-0.7*plant.getLAI()));
    }
    public void ESAS(){
        if(SWC<WP){
            a=0;
        } else if (SWC>FC) {
            a=1;
        }
        else{
            a=(SWC-WP)*(SWC-FC);
        }
        ESA=ESP*a;
        EPA=EPP-SWFAC2;
    }
    public void WBA(){

    }

    public void Initialization(File Soil_IN){
        System.out.println("Soil Water's Initialization called!");
        this.Soil_IN=Soil_IN;
       
        try {
            Scanner scanner = new Scanner(this.Soil_IN);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Use regular expressions to match the variable name and value
                Matcher matcher = Pattern.compile("([a-zA-Z_]+):\\s*(\\S+)").matcher(line);
                if (matcher.find()) {
                    if (matcher.group(1).equals("DP")) {
                        DP = Double.parseDouble(matcher.group(2));
//                        System.out.println(DP);
                    }
                    if (matcher.group(1).equals("SWC")) {
                        SWC = Double.parseDouble(matcher.group(2));
                    }
                    if (matcher.group(1).equals("WPP")) {
                        WPP = Double.parseDouble(matcher.group(2));
                    }
                    if (matcher.group(1).equals("STP")) {
                        STP = Double.parseDouble(matcher.group(2));
                    }
                    if (matcher.group(1).equals("CN")) {
                        CN = Double.parseDouble(matcher.group(2));
                    }
                    if (matcher.group(1).equals("DRNP")) {
                        DRNP = Double.parseDouble(matcher.group(2));
                    }
                    if (matcher.group(1).equals("FCP")) {
                        FCP = Double.parseDouble(matcher.group(2));
                    }
                    if (matcher.group(1).equals("Rn")) {
                        Rn = Double.parseDouble(matcher.group(2));
                    }
                    if (matcher.group(1).equals("g")) {
                        g = Double.parseDouble(matcher.group(2));
                        }
                     if (matcher.group(1).equalsIgnoreCase("IRR")) {
                            IRR = Double.parseDouble(matcher.group(2));
                    }
                }
            }
            scanner.close();
        }catch (Exception e){
            e.getStackTrace();
        }
        try {
            File file = new File("PLANT_IN.txt");
            FileWriter fileWriter = new FileWriter(file,true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("swfac:" + (SWFAC2+SWFAC1/2));
            printWriter.close();
        }catch(Exception e){
            e.getStackTrace();
        }
        WP=DP*WPP*10;
        FC=DP*FCP*10;
        ST=DP*STP*10;
        RUNOFF();
        STRESS();
        Train=0;
        TIRR=0;
        TESA=0;
        TEPA=0;
        TROF=0;
        TDRN=0;
        TINF=0;
    }
    public void rateCalculation(){
        
   
        POT_INF= weather.getRAIN()+IRR;
        TIRR+=IRR;
        Train+= weather.getRAIN();
        DRAIN();
        ROF=0;
        if(POT_INF>0){
            RUNOFF();
        }
        INF=POT_INF-ROF;
        ETPS();
        ESAS();
    }
    public void Integration(){
        SWC=SWC+(INF-ESA-EPA-DRN);
        if(SWC>ST){
            ROF=ROF+(SWC-ST);
            SWC=ST;
        }
        else if(SWC<0){
            SWC_ADJ=SWC_ADJ-SWC;
            SWC=0;
        }
       STRESS();
        WTABLE = (SWC - FC) / (ST - FC) * DP * 10;
        DWT = DP * 10 - WTABLE;
        if (DWT > 250) {
                SWFAC2 = 1.0;}
        else{
                SWFAC2 = DWT / 250;}
    }
    public void output(){
        try{
            FileWriter fileWriter= new FileWriter(this.Soil_IN);
            PrintWriter printWriter= new PrintWriter(fileWriter);
            printWriter.println("SWFAC_ONE:" + SWFAC1);
            printWriter.println("SWFAC_TWO:" + SWFAC2);
            printWriter.println("ALB:" + ALB);
            printWriter.println("INF:" + INF);
            printWriter.println("ST:" + ST);
            printWriter.println("WP:" + WP);
            printWriter.println("FC:" + FC);
            printWriter.println("S:" + S);
            printWriter.println("SWC_ADJ:" + SWC_ADJ);
            printWriter.println("THE:" + THE);
            printWriter.println("ROF:" + ROF);
            printWriter.println("DP:" + DP);
            printWriter.println("DWT:" + DWT);
            printWriter.println("WTABLE:" + WTABLE);
            printWriter.println("Train:" + Train);
            printWriter.println("TIRR:" + TIRR);
            printWriter.println("TESA:" + TESA);
            printWriter.println("TEPA:" + TEPA);
            printWriter.println("TDRN:" + TDRN);
            printWriter.println("TINF:" + TINF);
            printWriter.println("POT_INF:" + POT_INF);
            printWriter.println("g:" + g);
            printWriter.println("Rn:" + Rn);
            printWriter.println("DRN:" + DRN);
            printWriter.println("TROF:" + TROF);
            printWriter.println("IRR:" + IRR);
            printWriter.println("DRNP:" + DRNP);
            printWriter.println("ESP:" + ESP);
            printWriter.println("EPP:" + EPP);
            printWriter.println("ESA:" + ESA);
            printWriter.println("CN:" + CN);
            printWriter.println("WPP:" + WPP);
            printWriter.println("SWC:" + SWC);
            printWriter.println("FCP:" + FCP);
            printWriter.println("EEQ:" + EEQ);
            printWriter.println("TMED:" + Tmed);
            printWriter.println("a:" + a);
            printWriter.close();
        }catch (Exception e){
            e.getStackTrace();
        }

    }
    public void close(){
        Soil_IN=null;
      
    }
}
