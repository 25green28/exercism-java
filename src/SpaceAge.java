class SpaceAge {
    private final double seconds;
    private final double earthYears;
    private static final double SECONDS_PER_YEAR = 31_557_600;

    private static final double MERCURY_YEAR = 0.2408467;
    private static final double VENUS_YEAR = 0.61519726;
    private static final double EARTH_YEAR = 1.0;
    private static final double MARS_YEAR = 1.8808158;
    private static final double JUPITER_YEAR = 11.862615;
    private static final double SATURN_YEAR = 29.447498;
    private static final double URANUS_YEAR = 84.016846;
    private static final double NEPTUNE_YEAR = 164.79132;

    SpaceAge(double seconds) {
        this.seconds = seconds;
        this.earthYears = seconds / SECONDS_PER_YEAR;
    }

    double getSeconds() {
        return seconds;
    }

    double onEarth() {
        return earthYears;
    }

    double onMercury() {
        return earthYears / MERCURY_YEAR;
    }

    double onVenus() {
        return earthYears / VENUS_YEAR;
    }

    double onMars() {
        return earthYears / MARS_YEAR;
    }

    double onJupiter() {
        return earthYears / JUPITER_YEAR;
    }

    double onSaturn() {
        return  earthYears / SATURN_YEAR;
    }

    double onUranus() {
        return earthYears / URANUS_YEAR;
    }

    double onNeptune() {
        return earthYears / NEPTUNE_YEAR;
    }
}
