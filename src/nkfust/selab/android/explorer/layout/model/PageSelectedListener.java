/* Copyright (C) 2015 Zi-Xiang Lin <bdl9437@gmail.com>
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
package nkfust.selab.android.explorer.layout.model;
/**
 * If you have to process multi-onPageSelect event for Tab page, so need implements this interface then 
 * all implemented class add to the List, use For-loop execute all onPageSelect. 
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public interface PageSelectedListener {
	public void onPageSelect();
}
