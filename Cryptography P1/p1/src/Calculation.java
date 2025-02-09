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
    private static PrintWriter writer;  // Made static to share across instances

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
        writer.println("\nCalculation for a = " + a + ", b = " + b);
        writer.println("GCD = " + gcd());
        writer.println();
        writer.println(String.format("%-10s %-10s %-10s %-10s %-10s %-10s %-10s", 
            "u1", "v1", "u2", "v2", "u3", "v3", "q"));
        writer.println("-".repeat(70));
    }

    private void printRow() {
        writer.println(String.format("%-10d %-10d %-10d %-10d %-10d %-10d %-10d",
            u1, v1, u2, v2, u3, v3, q));
    }

    
    public int gcd(){
        // find the smaller value between the a and b
        int val = Math.min(a, b);

        // recusion formate
        // while that value is greater than zero
        // if the reminder of a and b divided by the value is 0 then stop/exit
        // 
        while(val > 0){
            if((a%val) == 0 && (b%val) == 0){
                break;
            }
            val--;
        }
        return val;
    }

    public int[] modCalc() throws Exception {
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

        int[] result;
        int gcd = gcd();
        if(((u1*a) + (u2*b)) == gcd){
            result = new int[]{u1, u2};
        }else if(((u2*a) + (u1*b)) == gcd){
            result = new int[]{u2, u1};
        } else {
            result = new int[]{-1, -1};
        }

        writer.println("\nResult: x = " + result[0] + ", y = " + result[1]);
        writer.println("-".repeat(70));
        return result;
    }

    public static void main(String[] args) throws Exception {
        // Initialize single output file
        writer = new PrintWriter(new FileWriter("output.txt"));
        writer.println("Modular Arithmetic Calculations");
        writer.println("==============================\n");

        // Creates a new calculation object which will, initialize u1-u3, v1-v3, a, b, and q variables based on given input
        Calculation calc1 = new Calculation(256112, 46064);
        // Prints the gcd calculated for a and b in terminal
        System.out.println(calc1.gcd());
        // Calls modCalc method in order to get the x and y variables to solve the equation ax + by = gcd
        int[] ans1 = calc1.modCalc(); // Will print table in the modCalc method as well to the output.txt file
        // Prints the x and y out into the terminal
        System.out.println("x: " + ans1[0] + " y: " + ans1[1]);

        // Creates a new calculation object which will, initialize u1-u3, v1-v3, a, b, and q variables based on given input
        Calculation calc2 = new Calculation(1042128, 445295);
        // Prints the gcd calculated for a and b in terminal
        System.out.println(calc2.gcd());
        // Calls modCalc method in order to get the x and y variables to solve the equation ax + by = gcd
        int[] ans2 = calc2.modCalc(); // Will print table in the modCalc method as well to the output.txt file
        // Prints the x and y out into the terminal
        System.out.println("x: " + ans2[0] + " y: " + ans2[1]);

        // Creates a new calculation object which will, initialize u1-u3, v1-v3, a, b, and q variables based on given input
        Calculation calc3 = new Calculation(17601969, 2364768);
        // Prints the gcd calculated for a and b in terminal
        System.out.println(calc3.gcd());
        // Calls modCalc method in order to get the x and y variables to solve the equation ax + by = gcd
        int[] ans3 = calc3.modCalc(); // Will print table in the modCalc method as well to the output.txt file
        // Prints the x and y out into the terminal
        System.out.println("x: " + ans3[0] + " y: " + ans3[1]);

        // Close the writer after all calculations are done
        writer.close();
    }
}
