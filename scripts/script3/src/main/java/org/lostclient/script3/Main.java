package org.lostclient.script3;

import java.util.ArrayList;
import java.util.List;
import org.lostclient.api.frameworks.tree.Tree;
import org.lostclient.api.script.ScriptManifest;
import org.lostclient.script3.behaviour.SomeLeaf;
import org.lostclient.script3.behaviour.SomeOtherLeaf;
import org.lostclient.scriptapi.ScriptBase;

@ScriptManifest(name = "Script3")
public class Main extends ScriptBase
{
	@Override
	public void initScript(Tree tree)
	{
		tree.addBranches(
				new SomeLeaf(),
				new SomeOtherLeaf()
		);
	}

	@Override
	public List<String> getPaintInfo()
	{
		return new ArrayList<>()
		{{
			add("Test");
		}};
	}
}
