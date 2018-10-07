package com.collegeProject.spaceShooter;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LoadImages {
	public static BufferedImage image;
	public static BufferedImage player;
	public static BufferedImage bullet;
	public static BufferedImage enemy;
	public static void init() {
		image=imageLoader("10.png");
		enemy=imageLoader("greenbat.png");
		player=imageLoader("player.png");
		bullet=imageLoader("bullet.png");
	}
	public static BufferedImage imageLoader(String path) {
		try {
			return ImageIO.read(LoadImages.class.getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		return null; 
	}
}
