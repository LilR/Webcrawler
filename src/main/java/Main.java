import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lily on 24/07/2019.
 */
public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        String rootUrl = args[0];

        Link root = new Link(rootUrl);

        HashSet<String> visited = new HashSet<>();
        getChildren(rootUrl, root, visited);

        System.out.println("finished");
    }

    private static void getChildren(String baseUri, Link root, Set<String> visited) throws IOException, URISyntaxException {
        Document document = Jsoup.connect(root.getUri()).get();
        Elements links = document.select("a[href]");
        for (Element child : links) {
            String href = child.attr("href");
            if (!visited.contains(href)) {
                Link childLink = getChildLink(href, baseUri);
                if (childLink != null) {
                    visited.add(href);
                    root.addLink(childLink);
                    System.out.println(childLink.getUri());
                    getChildren(baseUri, childLink, visited);
                }
            }
        }
    }

    private static Link getChildLink(String href, String baseUri) throws URISyntaxException {
        URI uri = new URI(href);
        if ((uri.getHost() == null || uri.getHost().equals(baseUri))
                && uri.getFragment() == null) {
            return new Link(baseUri + uri.getPath());
        }
        return null;
    }

}
