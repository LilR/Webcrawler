import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lily on 24/07/2019.
 */
public class Link {
    private String uri;
    private List<Link> links;

    public Link(String uri) {
        this.uri = uri;
        links = new ArrayList<>();
    }

    public String getUri() {
        return uri;
    }

    public void addLink(Link link){
        links.add(link);
    }
}
