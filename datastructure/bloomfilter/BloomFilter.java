package datastructure.bloomfilter;

/**
 * @author sqzhang
 * @year 2020
 */
public class BloomFilter {
    private final int N_SETS;
    private final long[][] bitsets;
    private final int[] SET_SIZES;
    private static final long MOD64_MASK = 0x7F;
    private static final long DIV64_SHIFT = 6;

    public BloomFilter(int[] bitSetSizes) {
        N_SETS = bitSetSizes.length;
        SET_SIZES = bitSetSizes.clone();
        bitsets = new long[N_SETS][];
        for (int i = 0; i < N_SETS; i++) {
            bitsets[i] = new long[SET_SIZES[i]];
        }
    }

    public void add(int setIndex, long hash) {
        hash = hash % SET_SIZES[setIndex];
        int block = (int) (hash >> DIV64_SHIFT);
        bitsets[setIndex][block] |= (1L << (hash & MOD64_MASK));
    }

    public void add(long[] hashes) {
        for (int i = 0; i < N_SETS; i++) {
            add(i, hashes[i]);
        }
    }

    public boolean contains(long[] hashes) {
        for (int i = 0; i < hashes.length; i++) {
            int block = (int) (hashes[i] >> DIV64_SHIFT);
            long MASK = 1L << (hashes[i] & MOD64_MASK);
            if ((bitsets[i][block] & MASK) != MASK) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        int maxSz = 0;
        for (int setSize : SET_SIZES) maxSz = Math.max(maxSz, setSize);

        char[][] matrix = new char[N_SETS][maxSz];
        for (char[] ar : matrix) java.util.Arrays.fill(ar, '0');

        for (int k = 0; k < N_SETS; k++) {
            for (int i = 0; i < SET_SIZES[k]; i++) {
                int block = i / 64;
                int offset = i % 64;
                long mask = 1L << offset;
                if ((bitsets[k][block] & mask) == mask) {
                    matrix[k][i] = '1';
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char[] row : matrix) {
            sb.append(new String(row)).append("\n");
        }
        return sb.toString();
    }
}
