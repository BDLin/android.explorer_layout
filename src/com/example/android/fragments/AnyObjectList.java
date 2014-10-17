/* Copyright (C) 2014 Zi-Xiang Lin <bdl9437@gmail.com>
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
package com.example.android.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.widget.ListView;

public class AnyObjectList {
	
	private ArrayList<Viewable> array = new ArrayList<Viewable>();
    private ListView list;
    private Context context;
    
    public AnyObjectList(Context context){
    	
    	this.context = context;
    	list = new ListView(context);
        
        Ipsum I1 = new Ipsum("Article One", "Article One\n\nExcepteur pour-over occaecat squid biodiesel umami gastropub, nulla laborum salvia dreamcatcher fanny pack. Ullamco culpa retro ea, trust fund excepteur eiusmod direct trade banksy nisi lo-fi cray messenger bag. Nesciunt esse carles selvage put a bird on it gluten-free, wes anderson ut trust fund twee occupy viral. Laboris small batch scenester pork belly, leggings ut farm-to-table aliquip yr nostrud iphone viral next level. Craft beer dreamcatcher pinterest truffaut ethnic, authentic brunch. Esse single-origin coffee banksy do next level tempor. Velit synth dreamcatcher, magna shoreditch in american apparel messenger bag narwhal PBR ennui farm-to-table.");
        Ipsum I2 = new Ipsum("Article Two","Article Two\n\nVinyl williamsburg non velit, master cleanse four loko banh mi. Enim kogi keytar trust fund pop-up portland gentrify. Non ea typewriter dolore deserunt Austin. Ad magna ethical kogi mixtape next level. Aliqua pork belly thundercats, ut pop-up tattooed dreamcatcher kogi accusamus photo booth irony portland. Semiotics brunch ut locavore irure, enim etsy laborum stumptown carles gentrify post-ironic cray. Butcher 3 wolf moon blog synth, vegan carles odd future.");
        
        array.add(I1);
        array.add(I2);
        array.add(new ListData("List Test"));
        array.add(new ImageData("Image Test", context, BitmapFactory.decodeResource(context.getResources(), R.drawable.winter)));
        
        list.setAdapter(new AnyObjectAdapter(context, array));
    }
    
    public void setItemListener(ArticleFragment article){
    	list.setOnItemClickListener(new ListOnClick(article, context, array));
    }
    
    public ListView getView(){
    	return list;
    }
    
}
