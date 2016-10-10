package moonroad.classes;

public class HTML {
    private String title;
    private String body;

    public HTML (String title, String body) {
        this.title = title;
        this.body  = body;
    }

    @Override
    public String toString ( ) {
        return
            "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<meta charset=\"UTF-8\">\n" +
            "<title>" + this.title + "</title>" +
            "</head>\n" +
            "<body>\n" +
            this.body + "\n" +
            "</body>\n" +
            "</html>";
    }

    public static String bold (String text) {
        return "<b>" + text + "</b>";
    }
}
