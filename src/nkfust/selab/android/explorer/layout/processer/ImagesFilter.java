/*
 * Copyright (C) 2014 Zi-Xiang Lin <bdl9437@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nkfust.selab.android.explorer.layout.processer;

import java.util.ArrayList;
import java.util.List;

import poisondog.net.URLUtils;
import poisondog.string.ExtractFileName;
/**
 * This class is a filter, if get uncertain image path list can use it filter path list.
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 *
 */
public class ImagesFilter {
	
	/**
	 * @param imagesPaths	The list that want to filter.
	 * @return	The list of have been filter.
	 */
	public static List<String> getImagesList(List<String> imagesPaths){
		List<String> list = new ArrayList<String>();
		String[] sliptPathChart = imagesPaths.get(0).split("/");
		String root = imagesPaths.get(0).replace(sliptPathChart[sliptPathChart.length-1], "");
		for(String str : imagesPaths)
			if(URLUtils.guessContentType(new ExtractFileName().process(str)).startsWith("image/")){
				if(str.startsWith("webdav"))
					str = root + str.split("/")[str.split("/").length-1];
				list.add(str);
			}
		return list;
	}
}
