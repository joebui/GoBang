



public class Application {
    public static void main(String[] args) {
        GoBang_Model model = new GoBang_Model();
        GoBang_View view = new GoBang_View(model);
        GoBang_Controller control = new GoBang_Controller(model, view);
        
        view.setVisible(true);        
    }
}
 