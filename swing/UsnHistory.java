import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class UsnHistory {
    private static final int LIMIT = 2;
    private static final LinkedList<String> RECENT_USNS = new LinkedList<>();

    private UsnHistory() {
    }

    public static synchronized void add(String usn) {
        if (usn == null) {
            return;
        }
        String trimmed = usn.trim();
        if (trimmed.isEmpty()) {
            return;
        }

        RECENT_USNS.removeIf(existing -> existing.equalsIgnoreCase(trimmed));
        RECENT_USNS.addFirst(trimmed);
        while (RECENT_USNS.size() > LIMIT) {
            RECENT_USNS.removeLast();
        }
    }

    public static synchronized List<String> getRecent() {
        return new ArrayList<>(RECENT_USNS);
    }
}