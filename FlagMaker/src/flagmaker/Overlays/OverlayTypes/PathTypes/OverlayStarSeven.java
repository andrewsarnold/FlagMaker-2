package flagmaker.Overlays.OverlayTypes.PathTypes;

import flagmaker.Data.Vector;

public class OverlayStarSeven extends OverlayPath
{
	private final String Path = "M 0.22136364,-81.695795 15.998909,-32.640158 64.189364,-50.890431 35.673273,-7.9693381 79.988182,18.328569 28.651545,22.79466 l 7.069364,51.043364 -35.49954536,-37.352 -35.49963664,37.352 7.069364,-51.043364 -51.336546,-4.466091 44.31491,-26.2979071 -28.516182,-42.9210929 48.190454,18.250273 15.77763664,-49.055637 z";
	private final Vector PathSize = new Vector(160, 156);

	public OverlayStarSeven(int maximumX, int maximumY)
	{
		super("star seven", maximumX, maximumY);
		Constructor(Path, PathSize);
	}
}
