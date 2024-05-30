package bench.cpu;

import bench.IBenchmark;
import java.math.BigDecimal;
import java.math.MathContext;

public class CPUDigitsOfPi implements IBenchmark {
    private int numDigits;

    private static final BigDecimal C = new BigDecimal("426880");
    private static final BigDecimal C_SQRT = new BigDecimal("10005").sqrt(new MathContext(100));

    @Override
    public void initialize(int numDigits) {
        // Initialize number of digits
            this.numDigits = numDigits;
    }

    @Override
    public void run() {
        run("default");
    }

    @Override
    public void run(String algorithm) {
        switch (algorithm) {
            case "default":
            case "chudnovsky":
                calculatePiUsingChudnovsky(numDigits);
                break;
            case "bbp":
                calculatePiUsingBBP(numDigits);
                break;
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
        }
    }



    @Override
    public void clean() {
        // Clean up resources if needed
    }

    @Override
    public void run(int[] i){
        System.err.println("You can only run this benchmark using a specific algorithms");
    }

    private void calculatePiUsingChudnovsky(int digits) {
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal kFactorial6;
        BigDecimal numerator;
        BigDecimal denominator;
        BigDecimal term;
        MathContext mc = new MathContext(digits + 10); // Extra precision to avoid rounding errors

        for (int k = 0; k < digits / 14 + 1; k++) {
            kFactorial6 = factorial(6 * k);
            numerator = kFactorial6.multiply(new BigDecimal(13591409).add(new BigDecimal(545140134).multiply(new BigDecimal(k))));
            denominator = factorial(3 * k).multiply(factorial(k).pow(3)).multiply(new BigDecimal(640320).pow(3 * k));
            term = numerator.divide(denominator, mc);

            if (k % 2 == 0) {
                sum = sum.add(term);
            } else {
                sum = sum.subtract(term);
            }
        }

        BigDecimal pi = C.multiply(C_SQRT).divide(sum, mc);
        System.out.println(pi.round(new MathContext(digits)));
    }

    private BigDecimal factorial(int n) {
        BigDecimal result = BigDecimal.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigDecimal.valueOf(i));
        }
        return result;
    }

    private void calculatePiUsingBBP(int digits) {
        BigDecimal pi = BigDecimal.ZERO;
        MathContext mc = new MathContext(digits + 10);
        for (int k = 0; k < digits; k++) {
            BigDecimal term = BigDecimal.valueOf(1.0 / Math.pow(16, k))
                    .multiply(BigDecimal.valueOf(4).divide(BigDecimal.valueOf(8 * k + 1), mc)
                            .subtract(BigDecimal.valueOf(2).divide(BigDecimal.valueOf(8 * k + 4), mc))
                            .subtract(BigDecimal.ONE.divide(BigDecimal.valueOf(8 * k + 5), mc))
                            .subtract(BigDecimal.ONE.divide(BigDecimal.valueOf(8 * k + 6), mc)));
            pi = pi.add(term);
        }
        System.out.println(pi.round(new MathContext(digits)));
    }



    @Override
    public void cancel() {

    }

}
