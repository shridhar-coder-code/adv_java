import java.io.PrintWriter;

public final class ServletUi {
    private ServletUi() {
    }

    public static void begin(PrintWriter out, String title) {
        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>" + escape(title) + "</title>");
        out.println("<style>");
        out.println("body{margin:0;font-family:Arial,sans-serif;background:#ff0;color:#111;}");
        out.println(".wrap{max-width:980px;margin:0 auto;padding:36px 18px 50px;}");
        out.println(
                ".card{background:#fffdf4;border:2px solid #1e4ea8;border-radius:18px;padding:28px;box-shadow:0 10px 26px rgba(0,0,0,.15);} ");
        out.println("h1{margin:0 0 14px;color:#b40000;font-size:32px;text-align:center;}");
        out.println(".msg{margin:0 0 20px;font-size:18px;font-weight:700;text-align:center;}");
        out.println("table{width:100%;border-collapse:collapse;background:#fff;}");
        out.println("td,th{border:1px solid #1e4ea8;padding:10px 12px;text-align:left;}");
        out.println("th{background:#ffe08a;}");
        out.println(".actions{margin-top:22px;text-align:center;}");
        out.println(
                ".btn{display:inline-block;padding:12px 22px;margin:6px;border:2px solid #9b3b3b;border-radius:10px;background:#ffcccc;color:#111;text-decoration:none;font-weight:700;}");
        out.println(".btn:hover{background:#b40000;color:#fff;}");
        out.println(".hint{margin-top:10px;text-align:center;color:#7a0000;font-weight:700;}");
        out.println("</style></head><body><div class='wrap'><div class='card'>");
    }

    public static void end(PrintWriter out) {
        out.println("</div></div></body></html>");
    }

    public static void heading(PrintWriter out, String text) {
        out.println("<h1>" + escape(text) + "</h1>");
    }

    public static void message(PrintWriter out, String text) {
        out.println("<div class='msg'>" + escape(text).replace("\n", "<br>") + "</div>");
    }

    public static void actions(PrintWriter out, String... links) {
        out.println("<div class='actions'>");
        for (String link : links) {
            out.println(link);
        }
        out.println("</div>");
    }

    public static String button(String href, String text) {
        return "<a class='btn' href='" + escapeAttr(href) + "'>" + escape(text) + "</a>";
    }

    public static void row(PrintWriter out, String key, String value) {
        out.println("<tr><th>" + escape(key) + "</th><td>" + escape(value) + "</td></tr>");
    }

    public static String escape(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    private static String escapeAttr(String value) {
        return escape(value).replace(" ", "%20");
    }
}