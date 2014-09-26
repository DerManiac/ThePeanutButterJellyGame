package at.tu.tpbjg.monkey_engine;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 * Hello world!
 *
 */
public class App extends SimpleApplication {
	public static void main(String[] args) {
		App app = new App();
		app.start();
	}

	@Override
	public void simpleInitApp() {
		Box b = new Box(1, 1, 1);
		Geometry geom = new Geometry("Box", b);
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.Blue);
		rootNode.attachChild(geom);
	}
}
