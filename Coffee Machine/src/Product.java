import java.util.Map;

public enum Product {
    ESPRESSO(Map.of(
            "WATER", 250,
            "MILK", 0,
            "BEANS", 16,
            "CUPS", 1,
            "COST", 4
    )
    ),
    LATTE(Map.of(
            "WATER", 350,
            "MILK", 75,
            "BEANS", 20,
            "CUPS", 1,
            "COST", 5
    )
    ),
    CAPPUCCINO(Map.of(
            "WATER", 250,
            "MILK", 100,
            "BEANS", 12,
            "CUPS", 1,
            "COST", 6
    )
    );

    private final Map<String, Integer> properties;

    Product(Map<String, Integer> properties) {
        this.properties = properties;
    }

    public Map<String, Integer> getProperties() {
        return properties;
    }
}
