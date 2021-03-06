/* ###
 * IP: GHIDRA
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
package help.screenshot;

import org.junit.Test;

import ghidra.app.plugin.core.codebrowser.CodeBrowserPlugin;
import ghidra.app.plugin.core.comments.CommentsDialog;
import ghidra.program.model.address.Address;

public class CommentsPluginScreenShots extends GhidraScreenShotGenerator {

	public CommentsPluginScreenShots() {
		super();
	}

	@Test
	public void testCaptureComment() {
		positionListingTop(0x401000);
		performAction("Edit Comments", "CommentsPlugin", false);
		captureDialog();
	}

	@Test
	public void testShowCommentHistory() throws Exception {

		setCommentFieldText("This is my first comment.", addr(0x401000));
		sleep(1000);
		setCommentFieldText("This is my second comment.", addr(0x401000));

		performAction("Show Comment History", "CommentsPlugin", false);

		captureDialog();
	}

	private void setCommentFieldText(String text, Address addr) {
		CodeBrowserPlugin plugin = getPlugin(tool, CodeBrowserPlugin.class);
		plugin.goToField(addr, "Address", 0, 0);
		performAction("Set EOL Comment", "CommentsPlugin", false);
		CommentsDialog dialog = (CommentsDialog) getDialog();
		prepareCommentsDialog(dialog, text);
		pressButtonByText(dialog, "OK");
	}

}
