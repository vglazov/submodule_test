import org.bytedeco.javacv.FrameGrabber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class ImageGrabberTest {

    @DisplayName("Grabbing first 10 images from sample video")
    @Test
    void testGrab10Images() throws FrameGrabber.Exception {
        ImageGrabber grabber = new ImageGrabber(ClassLoader.getSystemResourceAsStream("big_buck_bunny_trailer.webm"), "./images", "image");
        for(int i = 0; i < 10; i++) {
            grabber.grabNextImage();
        }
        assertTrue(new File("./images/image-10.jpeg").exists(), "10th image file exists");
    }
}
