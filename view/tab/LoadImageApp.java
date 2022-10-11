package view.tab;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import view.model.ImageDAO;

public class LoadImageApp extends Component {
	BufferedImage img;
	JFrame f = null;
	ImageDAO dao = null;
	String result = null;
	String filename = null;

	public LoadImageApp() {

	};

	public LoadImageApp(String data) {
		// TODO Auto-generated constructor stub
		try {
			dao = new ImageDAO();
			result = dao.resSelect(data);
			System.out.println(result + "이새키야");
			img = ImageIO.read(new File(result));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
		System.out.println("된다!");
	}

	public Dimension getPreferredSize() {
		if (img == null) {
			System.out.println("된다!2");
			return new Dimension(100, 100);
		} else {
			System.out.println("된다!3");
			return new Dimension(img.getWidth(null), img.getHeight(null));
		}
	}

}
