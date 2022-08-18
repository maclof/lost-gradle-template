package org.lostclient.script1;

import java.util.ArrayList;
import java.util.List;
import org.lostclient.api.frameworks.tree.Tree;
import org.lostclient.api.script.ScriptManifest;
import org.lostclient.library1.behaviour.SomeQuestLeaf;
import org.lostclient.script1.behaviour.SomeLeaf;
import org.lostclient.script1.behaviour.SomeOtherLeaf;
import org.lostclient.scriptapi.ScriptBase;

@ScriptManifest(name = "Script1")
public class Main extends ScriptBase
{
	@Override
	public void initScript(Tree tree)
	{
		tree.addBranches(
				new SomeLeaf(),
				new SomeOtherLeaf(),
				new SomeQuestLeaf()
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
