package com.terraformersmc.terrestria;

import java.util.Random;

public class Perlin {
	private int seed;
	private Random rand;
	private int zoom;

	public Perlin(int zoom) {
		rand = new Random();
		this.seed = rand.nextInt(10000);
		this.zoom = zoom;
	}

	public Perlin(int zoom, int seed) {
		rand = new Random();
		this.seed = seed;
		this.zoom = zoom;
	}

	public int getSeed() {
		return this.seed;
	}

	public double getNoiseLevelAtPosition(int x, int z) {
		x = Math.abs(x);
		z = Math.abs(z);
		int xmin = (int) (double) x / zoom;
		int xmax = xmin + 1;
		int zmin = (int) (double) z / zoom;
		int zmax = zmin + 1;
		Vector2i a = new Vector2i(xmin, zmin);
		Vector2i b = new Vector2i(xmax, zmin);
		Vector2i c = new Vector2i(xmax, zmax);
		Vector2i d = new Vector2i(xmin, zmax);
		double ra = getRandomAtPosition(a);
		double rb = getRandomAtPosition(b);
		double rc = getRandomAtPosition(c);
		double rd = getRandomAtPosition(d);
		return (double) cosineInterpolate( //Interpolate Z direction
			cosineInterpolate((float) ra, (float) rb, (float) (x - xmin * zoom) / zoom), //Interpolate X1
			cosineInterpolate((float) rd, (float) rc, (float) (x - xmin * zoom) / zoom), //Interpolate X2
			((float)z - (float)zmin * (float) zoom) / (float) zoom);
	}
	private float cosineInterpolate(float a, float b, float x) {
		float ft = (float) (x * Math.PI);
		float f = (float) ((1f - Math.cos(ft)) * .5f);
		return a * (1f - f) + b * f;
	}
	private double getRandomAtPosition(Vector2i coord) {
		double var = 10000 * (Math.sin(coord.getX()) + Math.cos(coord.getY()) + Math.tan(seed));
		rand.setSeed((long) var);
		return rand.nextDouble();
	}

	private static class Vector2i {
		private int x;
		private int y;

		public Vector2i(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
	}
}
