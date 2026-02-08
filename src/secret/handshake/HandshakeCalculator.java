package secret.handshake;

import java.util.ArrayList;
import java.util.List;

enum Signal {
    WINK, DOUBLE_BLINK, CLOSE_YOUR_EYES, JUMP
}


class HandshakeCalculator {
    private final static Signal[] actions = { Signal.WINK, Signal.DOUBLE_BLINK, Signal.CLOSE_YOUR_EYES, Signal.JUMP };

    List<Signal> calculateHandshake(int number) {
        List<Signal> signals = new ArrayList<>();
        String binaryNumber = Integer.toBinaryString(number);

        System.out.println(binaryNumber);

        // Check values of bits, starting from the end of string
        for (int i = 0; i < Math.min(binaryNumber.length(), 5); i++) {
            int indexFromEnd = binaryNumber.length() - 1 - i;
            if (binaryNumber.charAt(indexFromEnd) == '1') {
                if (i == 4) {
                    signals = signals.reversed();
                } else {
                    signals.add(actions[i]);
                }
            }
        }

        return signals;
    }

    public static void main(String[] args) {
        HandshakeCalculator calculator = new HandshakeCalculator();
        System.out.println(calculator.calculateHandshake(111));
    }
}
