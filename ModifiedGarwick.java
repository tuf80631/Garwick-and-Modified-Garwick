/*  
  * To change this license header, choose License Headers in Project Properties.  
  * To change this template file, choose Tools | Templates  
  * and open the template in the editor.  
  */  
 package assignment3finalfinal;  
 import java.util.Scanner;  
 /**  
  *  
  * @author 2016  
  */  
 public class ModifiedGarwick {  
   int[] table;  
   int[] top;  
   int[] base;  
   int[] increase;  
   double[] alloc;  
   int totalSpaceUsed = 0;  
   int sumOfIncrease = 0;  
   int remSpace = 0;  
   int tableMax = 0;  
   int tableBase = 0;  
   int[] oldTop;  
   int[] delta;  
   int[] newBase;  
   int[] stackSize;  
   double p;  
   int whichStack;  
   int movements = 0;  
   int spurts=1;  
   int numberStacks;  
   int stackOverFlowCount;  
   int numberMoves70Percent;  
   public ModifiedGarwick(int tableSize, int numberStacks, int spurts, double p) {  
     this.p = p;  
     this.spurts = spurts;  
     this.numberStacks = numberStacks;  
     tableMax = tableSize;  
     table = new int[tableSize+1];  
     stackSize = new int[numberStacks+1];  
     oldTop = new int[numberStacks+1];  
     top = new int[numberStacks+1];  
     base = new int[numberStacks + 2];  
     newBase = new int[numberStacks + 2];  
     increase = new int[numberStacks+1];  
     alloc = new double[numberStacks+1];  
     delta = new int[numberStacks+1];  
     int length = tableSize / numberStacks; //100  
     int count = 1;  
     int m = 0;  
     this.base[this.base.length-1]=tableSize;  
     while (count <= numberStacks)  
     {  
      if (count%2!=0)  
      {  
      this.base[count] = this.base[count-1];  
      this.oldTop[count]= this.base[count];  
      this.top[count] = this.base[count];  
      }  
      if (count%2==0)  
      {  
       this.base[count]=m+length;  
       this.top[count]=m+length+1;  
       this.oldTop[count]=m+length+1;  
      }  
      m += length;  
      count ++;  
     }  
     base[numberStacks+1] = tableSize;  
     remSpace = tableMax - tableBase - totalSpaceUsed;  
   }  
   public void garwick(int whichStack){  
     sumOfIncrease = 0;  
     totalSpaceUsed = 0;  
     this.whichStack = whichStack;  
     int i = 1;  
     while (i<=numberStacks){  
       if (i%2!= 0) {  
         stackSize[i] =top[i]-base[i];  
       } else {  
         stackSize[i]=base[i] - top[i] + 1;  
       }  
       if (stackSize[i]>0) {  
       totalSpaceUsed+= stackSize[i];}  
       i++;  
     }  
     int hy=1;  
     while (hy<=numberStacks){  
        if (hy%2!= 0) {  
         increase[hy] =top[hy] - oldTop[hy];  
       } else {  
         increase[hy] =oldTop[hy] - top[hy];  
       }  
       if (increase[hy]>0) {  
       sumOfIncrease += increase[hy];}  
       hy++;  
     }  
     remSpace= tableMax - tableBase - totalSpaceUsed;  
     if (remSpace< 0) {  
     } else {  
       stackOverFlowCount++;  
        int jk = 1;  
       while (jk<=numberStacks)  
       {  
        alloc[jk] =remSpace*((0.1)*(1.0/numberStacks)+(double)increase[jk]/(sumOfIncrease)*this.p*.90+  
        stackSize[jk]/(double)totalSpaceUsed*(1-this.p)*.90);  
        jk++;  
       }  
         double[] alloc2 = new double[numberStacks+1];  
       int ere = 1;  
       while (ere <=numberStacks)  
       {  
         alloc2[ere]=alloc[ere];  
         ere++;  
       }  
       int deltasum = 0;  
       int mxv = 1;  
       while (mxv <numberStacks)  
       {  
         delta[mxv]=(int)(alloc2[mxv]);  
         alloc2[mxv+1]+=alloc2[mxv]-delta[mxv];  
         deltasum += delta[mxv];  
         mxv++;  
       }  
         delta[numberStacks] = remSpace - deltasum;  
         newBase[1] = base[1];  
         int ih = 2;  
         while (ih<=numberStacks){  
           if (ih%2==0)  
           {  
           newBase[ih] = newBase[ih - 1] + stackSize[ih - 1] + delta[ih - 1] + delta[ih] + stackSize[ih];  
           }  
           else  
           {  
             newBase[ih]=newBase[ih-1];  
           }  
           ih++;  
         }  
       movingAlgorithm();  
       if (whichStack % 2 != 0) {  
         top[whichStack]++;  
       } else {  
         top[whichStack]--;  
       }  
       int hyu = 1;  
       while (hyu<=numberStacks)  
       {  
         oldTop[hyu]=top[hyu];  
         hyu++;  
       }  
     }  
   }  
   public void movingAlgorithm() {  
     base[numberStacks+1] = newBase[numberStacks+1];  
     if (whichStack % 2 != 0) {  
       top[whichStack]--;  
     } else {  
       top[whichStack]++;  
     }  
     int i = 2;  
     while ( i <= numberStacks + 1) {  
       if (newBase[i] < base[i]) {  
         int omega = base[i] - newBase[i];  
         if (i % 2 != 0) {  
           int yuh = base[i]+1;  
           while (yuh<=top[i])  
           {  
             movements++;  
             yuh++;  
           }  
         }  
         else {  
           int yuh = top[i];  
           while (yuh<=base[i])  
           {  
             movements++;  
             yuh++;  
           }  
         }  
         top[i] = top[i] - omega;  
         base[i] = newBase[i];  
         i++;  
       } else if (newBase[i] > base[i]) {  
         int j = numberStacks+1;  
         int mmm = i;  
         while (mmm<=numberStacks+1)  
         {  
            if (newBase[mmm] <= base[mmm]) {  
             j = mmm;  
             break;  
           }  
            mmm++;  
         }  
         int tre = j-1;  
         while (tre>=i)  
         {  
           int omega = newBase[tre] - base[tre];  
           if (tre % 2 == 0) {  
             int hss = base[tre];  
             while (hss>=top[tre])  
             {  
                   movements++;  
               hss--;  
             }  
               }  
           else {  
               int hyy = top[tre];  
               while (hyy>=base[tre]+1)  
               {  
                   movements++;  
                 hyy--;  
               }  
               }  
            base[tre] = newBase[tre];  
            top[tre] = top[tre] + omega;  
            tre--;  
         }  
         i = j - 1;  
       } else {  
         i++;  
       }  
     }  
   }  
 public void initializeStep3(double p)  
   {  
     Scanner in = new Scanner(System.in);  
     Scanner in2 = new Scanner(System.in);  
     String continue1 = "y";  
     int numberEntries;  
     while (continue1.equals("y")&&remSpace!=0)  
     {  
       System.out.print("enter which stack to add to");  
       whichStack = in.nextInt();  
       System.out.print("enter how many entries to the stack:");  
       numberEntries = in.nextInt();  
       int k = 0;  
       while (k<numberEntries)  
       {  
        if (whichStack%2!=0){  
         // if ((this.top[whichStack]+1)!=this.table.length)  
         // {this.table[this.top[whichStack]+1]=whichStack;}  
         this.top[whichStack]= this.top[whichStack]+1;  
          if (this.top[whichStack]>this.tableMax)  
         {  
           printStackInfo("Before Alloc");  
           this.garwick(whichStack);  
            printStackInfo("After Alloc");  
           System.out.println("Moves: " + this.movements);  
           System.out.println("OverFlow: " + this.stackOverFlowCount);  
         }  
           else if (whichStack<this.top.length-1&&this.top[whichStack]>=this.top[whichStack+1])  
         {  
           printStackInfo("Before Alloc");  
           this.garwick(whichStack);  
            printStackInfo("After Alloc");  
           System.out.println("Moves: " + this.movements);  
           System.out.println("OverFlow: " + this.stackOverFlowCount);  
         }  
        }  
        else  
        {  
         // this.table[this.top[whichStack]-1]=whichStack;  
         this.top[whichStack]= this.top[whichStack]-1;  
          if (this.top[whichStack]==this.top[whichStack-1])  
         {  
            printStackInfo("Before Alloc");  
           this.garwick(whichStack);  
            printStackInfo("After Alloc");  
           System.out.println("Moves: " + this.movements);  
           System.out.println("OverFlow: " + this.stackOverFlowCount);  
         }  
        }  
         k++;  
       }  
       System.out.print("continue? y/n");  
       continue1 = in2.nextLine();  
     }  
   }  
   public int skewDistribution() {double[] probability = new double[(int)numberStacks+1];  
     double number = Math.random();  
     probability[0]=1;  
     int count =1;  
     while (count<=(int)numberStacks)  
     {  
        probability[count]=1 / Math.pow(2, count);  
        count++;  
     }  
     count = 1;  
     while (count<=(int)numberStacks)  
     {  
       if (number > probability[count] && number <= probability[count - 1]) {  
           break;  
         }  
      count++;  
     }  
     if (count == 11){count = 10;}  
     return count;  
   }  
   public void printArray()  
   {  
     int count = 1;  
     while (count < table.length)  
     {  
       System.out.print(table[count]);System.out.print("|");  
       count++;  
     }  
     System.out.println();  
   }  
   public void printStackInfo(String h)  
   {  
     System.out.println("----------------------------------");  
     System.out.println(h);  
     int count = 1;  
     while (count <= numberStacks)  
     {  
       System.out.print("Top Stack "+count+": "+top[count]+" ");  
       count++;  
     }  
     System.out.println();  
     count = 1;  
     while (count <= numberStacks)  
     {  
       System.out.print("Base Stack "+count+": "+base[count] +" ");  
       count++;  
     }  
     System.out.println();  
     count = 1;  
     while (count <= numberStacks)  
     {  
       System.out.print("Alloc Stack "+count+": "+alloc[count] +" ");  
       count++;  
     }  
     System.out.println();  
     count = 1;  
     while (count <= numberStacks)  
     {  
       System.out.print("delta Stack "+count+": "+delta[count] +" ");  
       count++;  
     }  
     System.out.println();  
   }  
 public void initializeGarwick(boolean uniform)  
   {  
     int count123 = 1;  
     while (count123<=1000)  
     {  
       if (uniform) whichStack = 1 + (int)(Math.random() * ((10 - 1) + 1));  
       else  whichStack = skewDistribution() ;  
       int k = 0;  
       while (k<spurts)  
       {  
        if (whichStack%2!=0){  
          //if ((this.top[whichStack]+1)!=this.table.length)  
         //{this.table[this.top[whichStack]+1]=whichStack;}  
          this.top[whichStack]= this.top[whichStack]+1;  
          if (this.top[whichStack]>this.tableMax)  
         {  
           //System.out.println("stack overflow");  
           this.garwick(whichStack);  
         }  
           else if (whichStack<this.top.length-1&&this.top[whichStack]>=this.top[whichStack+1])  
         {  
           //System.out.println("stack overflow");  
           this.garwick(whichStack);  
         }  
        }  
        else  
        {  
          //this.table[this.top[whichStack]-1]=whichStack;  
          this.top[whichStack]= this.top[whichStack]-1;  
          if (this.top[whichStack]==this.top[whichStack-1])  
         {  
           //System.out.println("stack overflow");  
           this.garwick(whichStack);  
         }  
        }  
         k++; count123++;  
         if (count123==700){this.numberMoves70Percent = this.movements; }  
       }  
     }  
   }  
 }  