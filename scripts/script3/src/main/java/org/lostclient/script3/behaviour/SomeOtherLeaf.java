package org.lostclient.script3.behaviour;

import org.lostclient.api.frameworks.tree.Leaf;

public class SomeOtherLeaf extends Leaf
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
