package org.lostclient.script1.behaviour;

import org.lostclient.api.frameworks.tree.Leaf;

public class SomeLeaf extends Leaf
{
	@Override
	public boolean isValid()
	{
		return false;
	}

	@Override
	public int onLoop()
	{
		return 0;
	}
}
