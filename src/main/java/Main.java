import org.bytedeco.javacv.FrameGrabber;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {

    // create an utility that receives two strings: a file name for a video clip and a directory path
    // and saves the first 10 frames as jpeg on that directory (fileName-1.jpeg, … fileName-10.jpeg)
    public static void main(String [] args) throws FileNotFoundException, FrameGrabber.Exception {
        if(args.length != 2) throw new IllegalArgumentException("Wrong number of arguments");

        ImageGrabber grabber = new ImageGrabber(new FileInputStream(args[0]), args[1], "fileName");

        for(int i = 0; i < 10; i++) {
            grabber.grabNextImage();
        }
    }
}
