public enum Type {

    ACOUTIC, ELECTRIC;

    public String toString() {
        switch(this) {
            case ACOUTIC: return "acoustic";
            case ELECTRIC: return "electric";
            default: return "default"; 
        }
    }
}