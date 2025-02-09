// Tarun Nandamudi, Anne Fu, Dhruv Shah, Stephen Argauer
// The following code can be run in a Java Project to get the GCD and Modular Artihmetic Calculation Table

import java.io.FileWriter;
import java.io.PrintWriter;

public class Calculation {
    private int u1;
    private int u2;
    private int u3;
    private int v1;
    private int v2;
    private int v3;
    private int q;
    private int a;
    private int b;
    private PrintWriter writer;

    public Calculation(int aIn, int bIn){
        u1 = 1;
        v1 = 0;
        u2  = 0;
        v2 = 1;
        a = aIn;
        u3 = aIn;
        b = bIn;
        v3 = bIn;
        q = 0;
    }

    private void printTableHeader() {
        writer.println(String.format("%-10s %-10s %-10s %-10s %-10s %-10s %-10s", 
            "u1", "v1", "u2", "v2", "u3", "v3", "q"));
        writer.println("-".repeat(70));
    }

    private void printRow() {
        writer.println(String.format("%-10d %-10d %-10d %-10d %-10d %-10d %-10d",
            u1, v1, u2, v2, u3, v3, q));
    }

    public int gcd(){
        int val = Math.min(a, b);
        while(val > 0){
            if((a%val) == 0 && (b%val) == 0){
                break;
            }
            val--;
        }
        return val;
    }

    public int[] modCalc(String outputFile) throws Exception {
        writer = new PrintWriter(new FileWriter(outputFile));
        int gcd = gcd();

        // Print header and initial values
        printTableHeader();
        printRow();

        while(v3 != 0){
            q = u3/v3;
            int tempV1 = u1 - (q*v1);
            u1 = v1;
            v1 = tempV1;

            int tempV2 = u2 - (q*v2);
            u2 = v2;
            v2 = tempV2;

            int tempV3 = u3 - (q*v3);
            u3 = v3;
            v3 = tempV3;

            printRow();
        }

        writer.close();

        if(((u1*a) + (u2*b)) == gcd){
            return new int[]{u1, u2};
        }else if(((u2*a) + (u1*b)) == gcd){
            return new int[]{u2, u1};
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) throws Exception {
        Calculation calc1 = new Calculation(256112, 46064);
        System.out.println(calc1.gcd());
        int[] ans1 = calc1.modCalc("output1.txt");
        System.out.println("x: " + ans1[0] + " y: " + ans1[1]);

        Calculation calc2 = new Calculation(1042128, 445295);
        System.out.println(calc2.gcd());
        int[] ans2 = calc2.modCalc("output2.txt");
        System.out.println("x: " + ans2[0] + " y: " + ans2[1]);

        Calculation calc3 = new Calculation(17601969, 2364768);
        System.out.println(calc3.gcd());
        int[] ans3 = calc3.modCalc("output3.txt");
        System.out.println("x: " + ans3[0] + " y: " + ans3[1]);
    }
}
