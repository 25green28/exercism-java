package flatten.array;

import java.util.ArrayList;
import java.util.List;

class Flattener {
    List<Object> flatten(List<?> list) {
        List<Object> result = new ArrayList<>();

        list.forEach(row -> {
            if (row != null) {
                if (row instanceof List) {
                    result.addAll(flatten((List<?>) row));
                } else {
                    result.add(row);
                }
            }
        });

        return result;
    }
}
