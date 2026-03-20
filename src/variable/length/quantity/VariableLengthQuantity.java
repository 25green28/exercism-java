package variable.length.quantity;

import java.util.*;

class VariableLengthQuantity {

    List<String> encode(List<Long> numbers) {
        List<String> groups = new ArrayList<>();

        for (Long number : numbers) {
            List<Long> chunks = new ArrayList<>();

            do {
                long bits = number & 127;
                number = number >> 7;
                chunks.add(bits);
            } while (number > 0);

            Collections.reverse(chunks);

            for (int i = 0; i < chunks.size(); i++) {
                Long chunk = chunks.get(i);
                chunk = chunk | ((i < chunks.size() - 1) ? 128 : 0);
                String result = String.format("0x%x", chunk);
                groups.add(result);
            }
        }

        return groups;
    }

    List<String> decode(List<Long> bytes) {
        List<String> numbers = new ArrayList<>();
        long current = 0;
        boolean continuation = true;

        for (long chunk : bytes) {
            long data = chunk & 127;
            current = (current << 7) | data;
            continuation = (chunk & 128) != 0;
            if (!continuation) {
                numbers.add("0x" + Long.toHexString(current));
                current = 0;
            }
        }

        if (continuation) {
            throw new IllegalArgumentException("Invalid variable-length quantity encoding");
        }

        return numbers;
    }
}