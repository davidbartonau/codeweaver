package common;

public enum Tags {
    OPEN("{OPEN:CLASS}"),
    PREOPEN("{PREOPEN:CLASS}"),
    POSTOPEN("{POSTOPEN:CLASS}"),
    CLOSE("{CLOSE:CLASS}"),
    PRECLOSE("{PRECLOSE:CLASS}"),
    POSTCLOSE("{POSTCLOSE:CLASS}");

    public final String label;

    private Tags(String label){
        this.label = label;
    }
}
