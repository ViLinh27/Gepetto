package csc421_practice;

import java.util.Scanner;

public class hw3_gepetto {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        //covers the first line of input:
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        
        int[][] cantMix = new int[N][N]; // cantMix[i-1][j-1]=1 if ingredients i and j (0<=i,j<N) cannot be mixed; initially all zeroes

        for(int pair=0;pair<M;pair++) {
            int i=scanner.nextInt();
            int j=scanner.nextInt();
            cantMix[i-1][j-1]=1;//first thing in the cantMix table(pair)
            cantMix[j-1][i-1]=1;//first thing in cantMix table(pair)
        }
        
        int[] pizza = new int[N];//as long as there are ingredient num
        int pizzaCount = 1; //represents first valid pizza, the completely plain one(no ingredients)
        int ingrNum = 0;//how many ingredients we working iwth?
        boolean ingrOk;//any no mix ingredient?
        
        while(true) {
            if(pizza[ingrNum]<N) { //one more ingredient to try for ingrNum //see how far you can extend partial solution
                if(pizza[ingrNum]==0 && ingrNum>0) { //choose next ingredient but not less than previous ingredient//ingredients can't repeat
                    pizza[ingrNum]=pizza[ingrNum-1]+1;
                }  //if no ingredient there, choose next ingredient from previous ingredient//ggo back to choose next ingr
                else {
                    //something already there so go to next slot
                    pizza[ingrNum]++;  //try next ingredient in sequence; including special case ingrNum==0, choose ingredient 1
                }
                
                ingrOk=true;//all ingredients mix well
                for(int i=0;i<ingrNum;i++) {
                    if(cantMix[pizza[i]-1][pizza[ingrNum]-1]>0) {
                        ingrOk=false;//some ingredients cannot mix
                        break;//exit loop!!!
                    } //check that pizza[ingrNo] is compatible with all other ingredients before
                }
                
                if(ingrOk) {//ingredients can mix fine
                    pizzaCount++;
                    /*
                    System.out.printf("%d:", pizzaCount);                 // <<<<<< Debug only
                    for(int j=0;j<N;j++) {                                    // <<<<<< Debug only
                        if(pizza[j]>0) {System.out.printf(" %d",pizza[j]);}  // <<<<<< Debug only
                        else break;                                           // <<<<<< Debug only
                    }                                                         // <<<<<< Debug only
                    System.out.println();                                     // <<<<<< Debug only
                    */
                    if(pizza[ingrNum]<N) {//more ingredients in pizza to check?
                        ingrNum++;
                    }
                }
            }
            else {//backtrack as needed in case ingredient no work
                pizza[ingrNum]=0;//starts back at beginning for what goes on pizza
                if(ingrNum>0) {//checks if pizza not empty of ingredients
                    ingrNum--;//back track to previous ingredient choice
                }
                else {
                    break;
                }
            }
        }
        System.out.println(pizzaCount);     //how many pizzsa can be made

    }

}
