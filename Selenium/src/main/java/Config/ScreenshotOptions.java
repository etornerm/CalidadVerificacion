package Config;

public enum ScreenshotOptions {
    DEFAULT ("default");

    private String name;
    private ScreenshotOptions(String name) {
        this.name = name;
    }

    public String getName() {
            return name;
        }
}
