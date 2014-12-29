package nkfust.selab.android.explorer.layout.processer;

import java.util.ArrayList;
import java.util.List;

import poisondog.net.URLUtils;
import poisondog.string.ExtractFileName;

public class ImagesFilter {
	
	public static List<String> getImagesList(List<String> imagesPaths){
		List<String> list = new ArrayList<String>();
		for(String str : imagesPaths)
			if(URLUtils.guessContentType(new ExtractFileName().process(str)).startsWith("image/"))
				list.add(str);
		return list;
	}
}
