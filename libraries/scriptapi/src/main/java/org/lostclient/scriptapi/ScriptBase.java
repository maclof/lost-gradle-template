package org.lostclient.scriptapi;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.lostclient.api.Client;
import org.lostclient.api.events.random.RandomManager;
import org.lostclient.api.frameworks.tree.Tree;
import org.lostclient.api.listeners.Painter;
import org.lostclient.api.listeners.skill.SkillTracker;
import org.lostclient.api.script.AbstractScript;
import org.lostclient.api.utilities.Log;
import org.lostclient.api.utilities.Timer;
import org.lostclient.api.utilities.paint.CustomPaint;
import org.lostclient.api.wrappers.skill.Skill;

abstract public class ScriptBase extends AbstractScript implements Painter
{
	@Getter
	private final Tree tree = new Tree();

	@Getter
	private final Timer startTimer = new Timer();

	private final CustomPaint mainPaint = new CustomPaint(CustomPaint.PaintLocations.TOP_RIGHT_PLAY_SCREEN)
			.setInfoSupplier(() -> new ArrayList<String>()
			{{
				add(getScriptName() + " v" + getScriptVersion());
				add("Runtime: " + startTimer);
				add("Current Branch: " + Tree.currentBranch);
				add("Current Leaf: " + Tree.currentLeaf);
				List<String> paintInfo = getPaintInfo();
				if (paintInfo != null)
				{
					addAll(paintInfo);
				}
			}}.toArray(new String[0]));

	private boolean startedSkillTracker = false;

	@Override
	public void onStart(String... rawArgs)
	{
		RandomManager.setAutoLoginEnabled(true);

		initRawScriptArgs(rawArgs);

		tree.addBranches(
				// add common leafs that are available on *every* script
		);

		initScript(tree);
		
		tree.registerListeners();

		if (Client.isLoggedIn())
		{
			resetSkillTracker();
		}
	}

	@Override
	public int onLoop()
	{
		if (Client.isLoggedIn())
		{
			startSkillTracker();
		}

		return tree.onLoop();
	}

	@Override
	public void onPaint(Graphics2D g)
	{
		mainPaint.paint(g);
	}

	abstract public void initScript(Tree tree);

	private void initRawScriptArgs(String... rawArgs)
	{
		Map<String, String> args = new HashMap<>();

		for (String rawArg : rawArgs)
		{
			String[] splitArgs = rawArg.split(";");
			for (String splitArg : splitArgs)
			{
				String[] params = splitArg.split(":");

				Log.info("Script argument: " + params[0] + " - len: " + params.length);

				if (params.length != 2)
				{
					continue;
				}

				args.put(params[0], params[1]);
			}
		}

		initScriptArgs(args);
	}

	public void initScriptArgs(Map<String, String> args)
	{
		for (String key : args.keySet())
		{
			String value = args.get(key);
			switch (key)
			{
				case "arg1":
					// do something with value
					break;
				case "arg2":
					// do something with value
					break;
				case "arg3":
					// do something with value
					break;
			}
		}
	}

	abstract public List<String> getPaintInfo();

	private void startSkillTracker()
	{
		if (startedSkillTracker)
		{
			return;
		}
		for (Skill skill : Skill.values())
		{
			if (!SkillTracker.hasStarted(skill))
			{
				SkillTracker.start(skill);
			}
		}
		startedSkillTracker = true;
	}

	private void resetSkillTracker()
	{
		if (!startedSkillTracker)
		{
			return;
		}
		for (Skill skill : Skill.values())
		{
			if (SkillTracker.hasStarted(skill))
			{
				SkillTracker.deregister();
				break;
			}
		}
		startedSkillTracker = false;
	}
}
