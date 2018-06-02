package util;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AutoLabel {
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public static void setLabelFontSize(JLabel label, int size){
        int font_size = label.getFont().getSize();
        while(true){
            if(label.getPreferredSize().getWidth() < size){
                Font font = new Font(label.getFont().getName(), label.getFont().getStyle(), font_size++);
                label.setFont(font);
            }else{
                setLabelFontSize_Negative(label, size);
                break;
            }
        }
    }


    public static void setLabelFontSize_Negative(JLabel label, int size){
        int font_size = label.getFont().getSize();
        while(true){
            if(label.getPreferredSize().getWidth() > size){
                Font font = new Font(label.getFont().getName(), label.getFont().getStyle(), font_size--);
                label.setFont(font);
            }else{
                break;
            }
        }
    }
 
}
