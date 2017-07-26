package assignment3finalfinal;  
 import java.util.Scanner;  
 public class Garwick {  
   int[] table;  
   int[] top;  
   int[] base;  
   int[] increase;  
   int[] oldTop;  
   int[] newBase;  
   int[] stackSize;  
   double[] alloc;  
   int totalSpaceUsed = 0;  
   int sumOfIncrease = 0;  
   int remSpace = 0;  
   int tableMax = 0;  
   int tableBase = 0;  
   double p;  
   int[] delta;  
   int whichStack;  
   int movements = 0;  
   int spurts=1;  
   int numberStacks;  
   int stackOverFlowCount;  
   int numberMoves70Percent;  
   public Garwick( int tableLength, int numberStacks, int spurts, double p) {  
     this.p = p;  
     this.spurts = spurts;  
     this.numberStacks = numberStacks;  
     this.tableMax = tableLength;  
     this.table = new int[tableLength+1];  
     this.increase = new int[numberStacks+1];  
     this.alloc = new double[numberStacks+1];  
     this.delta = new int[numberStacks+1];  
     this.stackSize = new int[numberStacks+1];  
     this. oldTop = new int[numberStacks+1];  
     this.top = new int[numberStacks+1];  
     this.base = new int[numberStacks + 2];  
     this.newBase = new int[numberStacks + 2];  
     // initializes bases, tops and allocates stack storage for each stack  
     int initialAllocationForEachStack = tableLength/(int)this.numberStacks;  
     int count = 1;  
     int m = 0;  
     this.base[this.base.length-1]=tableLength;  
     while (count <= this.numberStacks)  
     {  
      this.base[count] = m;  
      this.oldTop[count]=m;  
      this.top[count]=m;  
      m += initialAllocationForEachStack;  
      count ++;  
     }  
      base[numberStacks+1] = tableLength;  
     remSpace = tableMax - tableBase - totalSpaceUsed;  
   }  
   public void garwick(int whichStack){  
     this.whichStack = whichStack; //whichStack = stack that causes overflow  
     sumOfIncrease=0;  
     totalSpaceUsed=0;  
     int yu=1;  
     while (yu<=numberStacks) //calculates increaases for each stack and totals total increase  
      {  
       increase[yu] = top[yu] - oldTop[yu];  
       sumOfIncrease += increase[yu];  
       yu++;  
     }  
     int uxy = 1;  
     while (uxy<=numberStacks) //calcualtes stackSizes for each stack and totals totalSpaceUsed  
     {  
      stackSize[uxy] = top[uxy] - base[uxy];  
       totalSpaceUsed += stackSize[uxy];  
       uxy++;  
     }  
     remSpace = tableMax - tableBase - totalSpaceUsed;  
     if (remSpace < 0) {  
     } else {  
       stackOverFlowCount++;  
       int jk = 1;  
       while (jk<=numberStacks) //allocates allocation space for each stack  
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
       //calculates deltas for each stack  
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
      //calculates newBases for each stack  
      newBase[1] = base[1];  
       int m = 2;  
       while (m<=numberStacks)  
         {  
           newBase[m] = newBase[m - 1] + stackSize[m - 1] + delta[m - 1];m++;  
         }  
       movingAlgorithm();  
       //adds back the entry that causes the overflow after reallocation  
       top[whichStack]++;  
       //resets OldTop to current Top  
       int l = 1;  
       while (l<=numberStacks)  
       {  
         oldTop[l] = top[l];l++;  
       }  
     }  
   }  
   public void movingAlgorithm() {  
     newBase[numberStacks+1]= base[numberStacks+1];  
     top[whichStack]--; //removes the entry that causes overflow because it won't be added until after moving algorithm  
     int i = 2;  
     while( i <= numberStacks+1) {  
       if (newBase[i] < base[i]) {  
         int omega = base[i] - newBase[i];  
         int kx = base[i] + 1;  
         while (kx <= top[i])  
             {  
               movements++;  
               kx++;  
             }  
         base[i] = newBase[i];  
         top[i] = top[i] - omega;  
         i++;  
       } else if (newBase[i] > base[i]) {  
         int j = numberStacks+1;  
         int mn = i;  
         while (mn<=numberStacks)  
         {  
           if (newBase[mn] <= base[mn]) {  
             j = mn;  
             break;  
           }  
           mn++;  
         }  
         int nx = j-1;  
         while (nx >=i)  
         {  
           int omega = newBase[nx]-base[nx];  
           int r = top[nx];  
           while (r>=base[nx]+1)  
           {  
             movements++;r--;  
           }  
           base[nx] = newBase[nx];  
           top[nx] = top[nx] + omega;  
           nx--;  
         }  
         i = j-1;  
       } else {  
         i++;  
       }  
     }  
   }  
   public int skewedNumber() {  
     //creates a new array to hold the probability numbers for each stack from 1 to 10.  
     double[] probability = new double[(int)numberStacks+1];  
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
  public void initializeStep3(){  
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
         // if ((this.top[whichStack]+1)!=this.table.length)  
         //{this.table[this.top[whichStack]+1]=whichStack;}  
         this.top[whichStack]= this.top[whichStack]+1;  
         if (this.top[whichStack]>this.base[whichStack+1])  
         {  
           printStackInfo("Before Alloc");  
           this.garwick(whichStack);  
           printStackInfo("After Alloc");  
           System.out.println("Moves: " + this.movements);  
           System.out.println("OverFlow: " + this.stackOverFlowCount);  
         }  
         k++;  
       }  
       System.out.print("continue? y/n");  
       continue1 = in2.nextLine();  
     }  
   }  
   public void initializeGarwick(boolean uniform)  
    {  
     int count123 = 1;  
     while (count123<=1000)  
     {  
       if (uniform)  
       {whichStack = 1 + (int)(Math.random() * ((numberStacks - 1) + 1));}  
       else  
       {  
        whichStack = skewedNumber() ;  
       }  
       int k = 0;  
       while (k<spurts)  
       {  
         //if ((Top[whichStack]+1)!=table.length)  
         // {table[Top[whichStack]+1]=whichStack;}  
         this.top[whichStack]= this.top[whichStack]+1;  
         if (this.top[whichStack]>this.base[whichStack+1])  
         {  
           this.garwick(whichStack);  
           //System.out.println("moves: " + this.numberMoves);  
         }  
          k++; count123++;  
          if (count123==700)  
          {this.numberMoves70Percent = this.movements;  
          }  
       }  
     }  
    }  
 }  
