/******************************************************************************
 *  Compilation:  javac GenomeCompressor.java
 *  Execution:    java GenomeCompressor - < input.txt   (compress)
 *  Execution:    java GenomeCompressor + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   genomeTest.txt
 *                virus.txt
 *
 *  Compress or expand a genomic sequence using a 2-bit code.
 ******************************************************************************/

/**
 *  The {@code GenomeCompressor} class provides static methods for compressing
 *  and expanding a genomic sequence using a 2-bit code.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Zach Blick
 *  @author William Beesley
 */
public class GenomeCompressor {
    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses and writes the results to standard output.
     */

    public static final int SHIFT = 'A';

    public static void compress() {
        // Make a map to convert the characters A, C, G, and T into 0, 1, 2, 3 respectively
        // Subtract by 'A' (SHIFT) to save some memory
        int[] nucleotides = new int['T' + 1 - SHIFT];
        nucleotides['A' - SHIFT] = 0;
        nucleotides['C' - SHIFT] = 1;
        nucleotides['G' - SHIFT] = 2;
        nucleotides['T' - SHIFT] = 3;

        String input = BinaryStdIn.readString();
        int length = input.length();
        // Output each nucleotide as a 2 bit int
        for (int i = 0; i < length; i++) {
            BinaryStdOut.write(nucleotides[input.charAt(i) - SHIFT], 2);
        }
        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {
        int[] nucleotides = new int[4];
        nucleotides[0] = 'A';
        nucleotides[1] = 'C';
        nucleotides[2] = 'G';
        nucleotides[3] = 'T';

        // Read in the nucleotides until empty, considering that they are 2 bits each
        while (!BinaryStdIn.isEmpty()) {
            int num = BinaryStdIn.readInt(2);
            // Write the character into the buffer
            BinaryStdOut.write(nucleotides[num]);
        }
        BinaryStdOut.close();
    }


    /**
     * Main, when invoked at the command line, calls {@code compress()} if the command-line
     * argument is "-" an {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}