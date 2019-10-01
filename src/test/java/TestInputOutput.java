import org.improving.tag.InputOutput;

public class TestInputOutput implements InputOutput {
    public String lastText;

    @Override
    public String receiveInput() {
        return null;
    }

    @Override
    public void displayText(Object text) {
        this.lastText = text.toString();
    }

    @Override
    public void displayNewLine() {
    }

    @Override
    public void displayPrompt(String prompt) {

    }
}
