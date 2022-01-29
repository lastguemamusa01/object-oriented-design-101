public enum Builder {
    
    FENDER, MARTIN, GIBSON, COLLINGS, PRS;

    public String toString() {
        switch(this) {
            case FENDER: return "fender";
            case MARTIN: return "martin";
            case GIBSON: return "gibson";
            case COLLINGS: return "collings";
            case PRS: return "martin";
            default: return "default"; 
        }
    }

}
