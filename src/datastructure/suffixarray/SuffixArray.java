package datastructure.suffixarray;

/**
 * @author sqzhang
 * @year 2020
 */
public class SuffixArray {

    private final int n;
    private int[] text;
    private int[] sa;
    private int[] lcp;

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
            while ((i + len < n) && (k + len < n) && text[i + n] == text[k + n]) {
                len++;
            }
            lcp[rsa[i]] = len;
            if (len > 0) {
                len--;
            }
        }
    }

    void buildSuffixArrayOriginal() {

    }

    void buildSuffixArrayWithCountSort() {

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----i-----sa-----lcp---suffix\n");

        for (int i = 0; i < n; i++) {
            int suffixLen = n - sa[i];
            char[] suffixArray = new char[suffixLen];
            for (int j = sa[i], k = 0; j < n; j++, k++) {
                suffixArray[k] = (char) text[j];
            }
            String suffix = new String(suffixArray);
            String formattedStr = String.format("% 7d % 7d % 7d %s\n", i, sa[i], lcp[i], suffix);
            sb.append(formattedStr);
        }
        return sb.toString();
    }

}
