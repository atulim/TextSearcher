package First;
import java.io.File;
import java.io.FileFilter;

public class TextFilter implements FileFilter {

	@Override
	public boolean accept(File arg0) {
		
		return arg0.getName().toLowerCase().endsWith(".txt");
	}

}
