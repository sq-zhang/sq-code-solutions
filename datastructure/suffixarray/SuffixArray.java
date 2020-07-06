package datastructure.suffixarray;

/**
 * @author sqzhang
 * @year 2020
 */
public abstract class SuffixArray {

    protected final int n;
    protected int[] text;
    protected int[] sa;
    protected int[] lcp;

    public SuffixArray(String text) {
        if (text == null) {
            throw new IllegalArgumentException();
        }
        this.n = text.length();
        this.text = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            this.text[i] = text.charAt(i);
        }

        buildSuffixArray();
        buildLcpArrayWithKasai();
    }

    public int[] getSuffixArray() {
        return sa;
    }

    public int[] getLcpArray() {
        return lcp;
    }

    private void buildLcpArrayWithKasai() {
        lcp = new int[n];
        int[] rsa = new int[n];
        for(int i = 0;i < n;i++) {
            rsa[sa[i]] = i;
        }
        for(int i = 0, len = 0;i < n;i++) {
            if (rsa[i] == 0) {
                continue;
            }
            int k = sa[rsa[i] - 1];
            while ((i + len < n) && (k + len < n) && text[i + len] == text[k + len]) {
                len++;
            }
            lcp[rsa[i]] = len;
            if (len > 0) {
                len--;
            }
        }
    }

    abstract void buildSuffixArray();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("   i   sa  lcp suffix\n");

        for (int i = 0; i < n; i++) {
            int suffixLen = n - sa[i];
            char[] suffixArray = new char[suffixLen];
            for (int j = sa[i], k = 0; j < n; j++, k++) {
                suffixArray[k] = (char) text[j];
            }
            String suffix = new String(suffixArray);
            String formattedStr = String.format("%4d %4d %4d %s\n", i, sa[i], lcp[i], suffix);
            sb.append(formattedStr);
        }
        return sb.toString();
    }

}
