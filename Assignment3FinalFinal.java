package assignment3finalfinal;  
 public class Assignment3FinalFinal {  
   public static void main(String[] args) {  
   //ModifiedGarwick gm = new ModifiedGarwick(21,3,1,.5);  
   //gm.initializeStep3(.5);  
   // Garwick garwick = new Garwick(21,3,1,.5);  
   // garwick.initializeStep3();  
     Assignment3FinalFinal a = new Assignment3FinalFinal();  
     a.uniformOrSkewed(true,true); // uniform Garwick  
     a.uniformOrSkewed(true,false); //uniform Garwickm  
   a.uniformOrSkewed(false,true); //skewed Garwick  
  a.uniformOrSkewed(false,false); //skewed Garwickm  
   }  
   public void uniformOrSkewed(boolean trueForUniformFalseforSkewed,boolean garwickOrModified)  
   {  
     if (trueForUniformFalseforSkewed)  
     {  
     if (garwickOrModified) System.out.println("\t\t"+"Uniform Garwick");  
     else System.out.println("\t\t"+"Uniform GarwickModified");  
     System.out.println("Spurts in 1");  
     System.out.println("P\t"+"70%Moves\t"+"100%Moves\t"+"OverFlows");  
     table(1,1,garwickOrModified,true);  
     table(.5,1,garwickOrModified,true);  
     table(0,1,garwickOrModified,true);  
     System.out.println("-----------------------------------------");  
     System.out.println("Spurts in 20");  
     table(1,20,garwickOrModified,true);  
     table(.5,20,garwickOrModified,true);  
     table(0,20,garwickOrModified,true);  
     System.out.println("-----------------------------------------");  
     System.out.println("Spurts in 50");  
     table(1,50,garwickOrModified,true);  
     table(.5,50,garwickOrModified,true);  
     table(0,50,garwickOrModified,true);  
     }  
     else  
     {  
     if (garwickOrModified) System.out.println("\t\t"+"Skewed Garwick");  
     else System.out.println("\t\t"+"Skewed GarwickModified");  
     System.out.println("Spurts in 1");  
     System.out.println("P\t"+"70%Moves\t"+"100%Moves\t"+"OverFlows");  
     table(1,1,garwickOrModified,false);  
     table(.5,1,garwickOrModified,false);  
     table(0,1,garwickOrModified,false);  
     System.out.println("-----------------------------------------");  
     System.out.println("Spurts in 20");  
     table(1,20,garwickOrModified,false);  
     table(.5,20,garwickOrModified,false);  
     table(0,20,garwickOrModified,false);  
     System.out.println("-----------------------------------------");  
     System.out.println("Spurts in 50");  
     table(1,50,garwickOrModified,false);  
     table(.5,50,garwickOrModified,false);  
     table(0,50,garwickOrModified,false);  
     }  
   }  
   public void table(double p, int spurts,boolean garwickOrModified, boolean uniform)  
   {  
     if (garwickOrModified){  
     int count = 1;  
     int sumMoves100P =0;  
     int sumMoves70P=0;  
     int sumOverFlows100P=0;  
     while (count<=10)  
     {  
     Garwick garwick = new Garwick(1000,10,spurts,p);  
     garwick.initializeGarwick(uniform);  
     sumMoves100P+=garwick.movements;  
     sumMoves70P+=garwick.numberMoves70Percent;  
     sumOverFlows100P+=garwick.stackOverFlowCount;  
     count++;  
     }  
     double AverageMoves100P=sumMoves100P/10.0;  
     double AverageMoves70P=sumMoves70P/10.0;  
     double AverageOverFlows100P = sumOverFlows100P/10.0;  
     System.out.println(p+"\t"+AverageMoves70P+"\t\t"+AverageMoves100P+"\t\t"+AverageOverFlows100P);  
     }  
     else  
     {  
     int count = 1;  
     int sumMoves100P =0;  
     int sumMoves70P=0;  
     int sumOverFlows100P=0;  
     while (count<=10)  
     {  
     ModifiedGarwick garwickM = new ModifiedGarwick(1000,10,spurts,p);  
     garwickM.initializeGarwick(uniform);  
     sumMoves100P+=garwickM.movements;  
     sumMoves70P+=garwickM.numberMoves70Percent;  
     sumOverFlows100P+=garwickM.stackOverFlowCount;  
     count++;  
     }  
     double AverageMoves100P=sumMoves100P/10.0;  
     double AverageMoves70P=sumMoves70P/10.0;  
     double AverageOverFlows100P = sumOverFlows100P/10.0;  
     System.out.println(p+"\t"+AverageMoves70P+"\t\t"+AverageMoves100P+"\t\t"+AverageOverFlows100P);  
     }  
   }  
 }  