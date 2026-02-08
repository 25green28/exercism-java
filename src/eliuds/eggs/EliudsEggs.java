package eliuds.eggs;

public class EliudsEggs {
    public int eggCount(int number) {
        int eggCount = 0;

        while (number > 0) {
            eggCount += (number & 1);
            number >>= 1;
        }

        return eggCount;
    }

    public static void main(String[] args) {
        long time1 = System.nanoTime();
        EliudsEggs eggs = new EliudsEggs();
        eggs.eggCount(10);
        long time2 = System.nanoTime();
        System.out.println((time2 - time1));
    }
}
