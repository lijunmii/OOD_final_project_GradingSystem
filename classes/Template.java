import java.util.ArrayList;
import java.util.List;


public class Template {
    private String templateName;
    private List<Category> categoryList;

    public Template(String templateName) {
        this.templateName = templateName;
        categoryList = new ArrayList<>();
    }

    public String getTemplateName() { return templateName;}

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void addCategory (Category cat) {
        categoryList.add(cat);
    }
}
