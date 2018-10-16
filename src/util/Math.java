package util;

public class Math {

    /**
     * Method that returns the num without passing a given limit
     * @param num num to compare with the limit
     * @param min minimum number to return
     * @param max maximum number to return
     * @return an integer
     */
    public static int clamp(int num, int min, int max){
        if(num > max){
            return max;
        }
        if(num < min){
            return min;
        }
        else {
            return num;
        }
    }
}
