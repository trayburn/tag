import org.improving.tag.InputOutput;

public class TestInputOutput implements InputOutput {
    public String lastText;

    @Override
    public String receiveInput() {
        return null;
    }

    @Override
    public void displayText(String text) {
        this.lastText = text;
    }

    @Override
    public void displayPrompt(String prompt) {

    }
}
