import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;

import java.io.File;
import java.io.InputStream;

import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;

public class ImageGrabber {

    private final FFmpegFrameGrabber grabber;
    private final OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();
    private final String targetDirectory;
    private final String imageName;
    private int counter = 1;

    public ImageGrabber(InputStream videoSource, String targetDirectory, String imageName) throws FrameGrabber.Exception {
        grabber = new FFmpegFrameGrabber(videoSource);
        grabber.start();
        checkAndCreateDirectory(targetDirectory);
        this.targetDirectory = targetDirectory;
        this.imageName = imageName;
    }

    private void checkAndCreateDirectory(String path) {
        File directory = new File(path);
        if(directory.exists()) {
            if(!directory.isDirectory()) {
                throw new RuntimeException(path + " is not a directory");
            }
        } else {
            if(!directory.mkdir()) {
                throw new RuntimeException("Error creating directory " + path);
            }
        }
    }

    public void grabNextImage() throws FrameGrabber.Exception {
        Mat grabbedImage = converter.convert(grabber.grabImage());
        imwrite(String.format("%s/%s-%d.jpeg", targetDirectory, imageName, counter++), grabbedImage);
    }

}
