package diamond;

import java.util.ArrayList;
import java.util.List;

class DiamondPrinter {

    List<String> printToList(char a) {
        int width = (a - 'A') * 2 + 1;
        StringBuilder builder = new StringBuilder();
        List<String> list = new ArrayList<>();

        for (char c = 'A'; c <= a; c++) {
            int outsideSpaces = (width - 1) / 2 - (c - 'A');
            int insideSpaces = (c == 'A') ? 0 : width - outsideSpaces * 2 - 2;

            builder.append(createSpaces(outsideSpaces));
            builder.append(c);
            builder.append(createSpaces(insideSpaces));
            if (c != 'A') {
                builder.append(c);
            }
            builder.append(createSpaces(outsideSpaces));

            list.add(builder.toString());

            // Clear StringBuilder for next use
            builder.setLength(0);
        }

        for (int i = list.size() - 2; i >= 0; i--) {
            list.add(list.get(i));
        }

        return list;
    }

    private String createSpaces(int size) {
        return " ".repeat(size);
    }


    // Used to display calculation in early phase of creating
//            System.out.print((char) i + ": ");
//            System.out.print("[outsideSpaces: " + outsideSpaces + "], ");
//            System.out.println("[insideSpaces: " + insideSpaces + "]");

    public static void main(String[] args) {
        DiamondPrinter printer = new DiamondPrinter();
        for (String s : printer.printToList('H')) {
            System.out.println(s);
        }
//        System.out.println(printer.printToList('C'));
    }

}
