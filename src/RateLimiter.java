import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

record UserInfo(int requests, Instant firstRequest) {}

public class RateLimiter<K> {
    private final Map<K, UserInfo> users = new HashMap<>();
    private final int limit;
    private final Duration windowSize;
    private final TimeSource timeSource;

    public RateLimiter(int limit, Duration windowSize, TimeSource timeSource) {
        this.limit = limit;
        this.windowSize = windowSize;
        this.timeSource = timeSource;
    }

    public boolean allow(K clientId) {
        UserInfo userInfo = users.get(clientId);
        Instant now = timeSource.now();

        if (userInfo == null || now.compareTo(userInfo.firstRequest().plus(windowSize)) >= 0) {
            users.put(clientId, new UserInfo(1, now));
            return true;
        }

        if (userInfo.requests() >= limit) {
            return false;
        }

        users.put(clientId, new UserInfo(userInfo.requests() + 1, userInfo.firstRequest()));
        return true;
    }

    public static void main(String[] args) {
        TimeSource clock = new TimeSource(Instant.EPOCH);
        RateLimiter<String> rateLimiter = new RateLimiter<>(2, Duration.ofNanos(10_000L), clock);
        System.out.println(rateLimiter.allow("A"));
        System.out.println(rateLimiter.allow("A"));
        System.out.println(rateLimiter.allow("A"));
        clock.advance(Duration.ofNanos(10_000L));
        System.out.println(rateLimiter.allow("A"));
    }
}

class TimeSource {
    private Instant now;

    public TimeSource(Instant start) {
        this.now = start;
    }

    public Instant now() {
        return now;
    }

    public void advance(Duration d) {
        this.now = this.now.plus(d);
    }
}
