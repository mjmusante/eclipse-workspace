package com.oracle.storage.MSVis;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

class PixelCanvas extends Canvas {
	BufferedImage bi;
	
	public PixelCanvas() {
		bi = new BufferedImage(1600, 1000, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = bi.getGraphics();
		
		g.setColor(Color.WHITE);
		for (int v = 1; v < 10; v++) {
			g.drawLine(0, v * 201, 1600, v * 200 + v);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(bi, 0, 0, null);
	}
	
	public void alloc(int vdev, int ms, long offset, long size) {
		int x1 = (int)(offset / 512);
		int x2 = (int)(offset / 512 + size / 512);
		int y = vdev * 201 + 1 + ms;
		Graphics g = bi.getGraphics();
		g.setColor(Color.RED);
		g.drawLine(x1, y, x2, y);
		repaint();
	}
	
	public void free(int vdev, int ms, long offset, long size) {
		int x1 = (int)(offset / 512);
		int x2 = (int)(offset / 512 + size / 512);
		int y = vdev * 201 + vdev + ms;
		Graphics g = bi.getGraphics();
		g.setColor(Color.GREEN);
		g.drawLine(x1, y, x2, y);
		repaint();
	}

}

public class MSVis {
	
	JFrame frame;
	PixelCanvas disp;
	
	String[] data = {
			"f:0:0:159232:512",
			"f:0:26:239616:512",
			"f:0:52:48128:512",
			"f:1:0:2048:512",
			"f:0:0:159744:512",
			"f:0:26:240128:512",
			"f:0:0:241152:2560",
			"f:0:26:27917561344:2560",
			"f:0:52:107008:2560",
			"a:0:0:161792:512",
			"a:0:26:242176:512",
			"f:0:0:181248:1536",
			"f:0:26:109568:1536",
			"f:0:0:182784:1024",
			"f:0:26:111104:1024",
			"f:0:0:183808:1024",
			"f:0:26:112128:1024",
			"f:0:0:184832:1024",
			"f:0:26:113152:1024",
			"f:0:0:185856:1024",
			"f:0:26:114176:1024",
			"f:0:0:186880:1024",
			"f:0:26:115200:1024",
			"f:0:0:187904:1024",
			"f:0:26:116224:1024",
			"f:0:0:158720:512",
			"f:0:26:239104:512",
			"a:0:0:162816:512",
			"a:0:26:243200:512",
			"a:0:0:163840:512",
			"a:0:26:244224:512",
			"f:0:0:157696:512",
			"f:0:26:238080:512",
			"f:0:0:195787776:16384",
			"f:0:26:370688:16384",
			"f:0:52:112640:16384",
			"f:0:0:195816448:16384",
			"f:0:26:399360:16384",
			"f:0:52:129024:16384",
			"f:0:0:246784:2048",
			"f:0:26:279552:2048",
			"f:0:52:161792:2048",
			"a:1:0:20480:512",
			"a:0:0:164864:512",
			"a:0:26:245248:512",
			"a:1:0:21504:512",
			"a:0:0:165888:512",
			"a:0:26:246272:512",
			"a:1:0:22016:512",
			"a:0:0:166400:512",
			"a:0:26:246784:512",
			"a:0:0:199168:1536",
			"a:0:26:127488:1536",
			"a:0:52:51712:1536",
			"f:1:0:16384:4096",
			"f:0:0:195849216:4096",
			"f:0:26:432128:4096",
			"f:1:0:8192:4096",
			"f:0:0:195808256:4096",
			"f:0:26:391168:4096",
			"f:1:0:12288:4096",
			"f:0:0:195812352:4096",
			"f:0:26:395264:4096",
			"f:1:0:4096:4096",
			"f:0:0:195804160:4096",
			"f:0:26:387072:4096",
			"f:1:0:3584:512",
			"f:0:0:161280:512",
			"f:0:26:241664:512",
			"f:0:0:188928:1536",
			"f:0:26:117248:1536",
			"f:0:52:48640:1536",
			"f:1:0:2560:512",
			"f:0:0:160256:512",
			"f:0:26:240640:512",
			"f:0:0:180736:512",
			"f:0:26:237056:512",
			"f:0:0:158208:512",
			"f:0:26:238592:512",
			"f:0:0:162304:512",
			"f:0:26:242688:512",
			"f:0:0:195832832:16384",
			"f:0:26:415744:16384",
			"f:0:52:145408:16384",
			"f:0:0:243712:3072",
			"f:0:26:276480:3072",
			"f:0:52:109568:3072",
			"f:0:0:190464:1024",
			"f:0:26:118784:1024",
			"f:0:52:50176:1024",
			"f:1:0:3072:512",
			"f:0:0:160768:512",
			"f:0:26:241152:512",
			"f:1:0:0:512",
			"f:0:0:178688:512",
			"f:0:26:235008:512",
			"a:0:0:195853312:8192",
			"a:0:0:191488:1536",
			"a:0:26:119808:1536",
			"a:0:0:193024:1024",
			"a:0:26:121344:1024",
			"a:0:0:194048:1024",
			"a:0:26:122368:1024",
			"a:0:0:195072:1024",
			"a:0:26:123392:1024",
			"a:0:0:196096:1024",
			"a:0:26:124416:1024",
			"a:0:0:197120:1024",
			"a:0:26:125440:1024",
			"a:0:0:198144:1024",
			"a:0:26:126464:1024",
			"a:0:0:162304:512",
			"a:0:26:242688:512",
			"a:0:0:163328:512",
			"a:0:26:243712:512",
			"a:0:0:248832:2560",
			"a:0:26:281600:2560",
			"a:0:52:163840:2560",
			"a:0:0:164352:512",
			"a:0:26:244736:512",
			"a:0:52:51200:512",
			"a:1:0:20992:512",
			"a:0:0:165376:512",
			"a:0:26:245760:512"
	};
	
	public MSVis() {
		this.frame = new JFrame();
		this.disp = new PixelCanvas();
		
		frame.setSize(1600, 1000);
		frame.add(disp);
		
		frame.setVisible(true);
	}
	
	public void go() {
		for (int i = 0; i < data.length; i++) {
			String[] line = data[i].split(":");
			int vdev = Integer.parseInt(line[1]);
			int ms = Integer.parseInt(line[2]);
			long offset = Long.parseLong(line[3]);
			long size = Long.parseLong(line[4]);
			
			switch (line[0].charAt(0)) {
			case 'a':
				this.disp.alloc(vdev, ms, offset, size);
				break;
			case 'f':
				this.disp.free(vdev, ms, offset, size);
				break;
			}
			// System.out.println((line[0] == "a" ? "Allocate" : "Free") + ": vdev=" + line[1] + "; ms=" + line[2] + "; offset=" + line[3] + "; size=" + line[4]);
			try { Thread.sleep(5); } catch (Exception e) {}
		}
	}
	
	
	
	public static void main(String[] argv) {
		MSVis v = new MSVis();
		
		v.go();
	}

}
