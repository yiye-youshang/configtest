import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Article {
    private int id;
    private String title;
    private String content;
    private  int score;

    public Article(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
