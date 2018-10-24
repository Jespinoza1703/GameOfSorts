package util;

import java.util.Random;

/**
 * Class for logic operations
 * @author José Acuña
 */
public class Math {

    public static Random random = new Random();

    /**
     * Method that returns the num without passing a given limit
     *
     * @param num num to compare with the limit
     * @param min minimum number to return
     * @param max maximum number to return
     * @return an integer inside the domain
     */
    public static double clamp(double num, double min, double max) {
        if (num > max) {
            return max;
        }
        if (num < min) {
            return min;
        } else {
            return num;
        }
    }

    /**
     * Approach "a" towards "b" by "amount" and returns the result
     * @param num integer to approach
     * @param result integer expected
     * @param amount how much to approach
     * @return integer of the maximum approach it could do
     */
    public static double approach(double num, double result, double amount) {
        if(num < result) {
            num += amount;
            if (num > result)
                return result;
            }
        else {
            num -= amount;
            if (num < result)
                return result;
            }
        return num;
    }
}
