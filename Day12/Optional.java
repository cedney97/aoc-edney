package Day12;

public class Optional {
    private boolean hasValue;
    private int value;

    public Optional(int value) {
        this.value = value;
        this.hasValue = true;
    }
    
    public Optional() {
        this.hasValue = false;
    }
    
    public boolean hasValue() {
        return this.hasValue;
    }

    public int getValue() {
        return this.value;
    }

    public static Optional min(Optional a, Optional b, Optional c, Optional d) {
        boolean changed = false;
        int currMin = 100000;
        if (a.hasValue()) {
            currMin = a.getValue() < currMin ? a.getValue() : currMin;
            changed = true;
        }
        if (b.hasValue()) {
            currMin = b.getValue() < currMin ? b.getValue() : currMin;
            changed = true;
        }
        if (c.hasValue()) {
            currMin = c.getValue() < currMin ? c.getValue() : currMin;
            changed = true;
        }
        if (d.hasValue()) {
            currMin = d.getValue() < currMin ? d.getValue() : currMin;
            changed = true;
        }
        if (changed) {
            return new Optional(currMin);
        } else {
            return new Optional();
        }
    }
}
