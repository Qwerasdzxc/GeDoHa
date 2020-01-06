package app.views.page.slot_content.text_content;

import app.models.slot.content.TextSlotContent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextEditorController {

    private TextSlotContent textContent;
    private TextEditor editor;

    public TextEditorController(TextSlotContent textContent, TextEditor editor) {
        this.textContent = textContent;
        this.editor = editor;
    }

    public ActionListener getBoldTextListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textContent.getFormats().contains(TextSlotContent.FormatType.BOLD)) {
                    textContent.getFormats().remove(TextSlotContent.FormatType.BOLD);
                } else {
                    textContent.getFormats().add(TextSlotContent.FormatType.BOLD);
                }
                updateText();
//                slot.notifyObservers(NotificationObserver.ADD, new JPanel());
            }
        };
    }

    public ActionListener getItalicTextActionListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (textContent.getFormats().contains(TextSlotContent.FormatType.ITALIC)) {
                    textContent.getFormats().remove(TextSlotContent.FormatType.ITALIC);
                } else {
                    textContent.getFormats().add(TextSlotContent.FormatType.ITALIC);
                }
                updateText();
//                slot.notifyObservers(NotificationObserver.ADD, new JPanel());
            }
        };
    }

    public ActionListener getFontSizeActionListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                textContent.setFontSize(editor.getToolbar().getSelectedFontSize());
                updateText();
//                slot.notifyObservers(NotificationObserver.ADD, new JPanel());
            }
        };
    }

    public ActionListener getFontStyleActionListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                textContent.setFontStyle(editor.getToolbar().getSelectedFontStyle());
                updateText();
//                slot.notifyObservers(NotificationObserver.ADD, new JPanel());
            }
        };
    }

    public ActionListener getFontColorActionListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String c = editor.getToolbar().getSelectedFontColor();
                switch (c) {
                    case "Black":
                        textContent.setColor(Color.BLACK);
                        break;
                    case "Red":
                        textContent.setColor(Color.RED);
                        break;
                    case "Green":
                        textContent.setColor(Color.GREEN);
                        break;
                    case "Blue":
                        textContent.setColor(Color.BLUE);
                        break;
                    case "Yellow":
                        textContent.setColor(Color.YELLOW);
                        break;
                    case "Orange":
                        textContent.setColor(Color.ORANGE);
                        break;
                    case "Pink":
                        textContent.setColor(Color.PINK);
                        break;
                }
                updateText();
//                slot.notifyObservers(NotificationObserver.ADD, new JPanel());
            }
        };
    }

    public void updateText() {
        boolean isBold = textContent.getFormats().contains(TextSlotContent.FormatType.BOLD);
        boolean isItalic = textContent.getFormats().contains(TextSlotContent.FormatType.ITALIC);

        int style = -1;

        if (isBold && isItalic)
            style = Font.BOLD + Font.ITALIC;
        else if (isBold)
            style = Font.BOLD;
        else if (isItalic)
            style = Font.ITALIC;
        else
            style = Font.PLAIN;

        editor.getTextArea().setFont(new Font(textContent.getFontStyle(), style, textContent.getFontSize()));
        editor.getTextArea().setForeground(textContent.getColor());
    }
}
