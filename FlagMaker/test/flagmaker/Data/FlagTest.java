package flagmaker.Data;

import flagmaker.Divisions.Division;
import flagmaker.Divisions.DivisionGrid;
import flagmaker.Divisions.DivisionPales;
import flagmaker.Overlays.Overlay;
import flagmaker.Overlays.OverlayTypes.OverlayLineHorizontal;
import flagmaker.Overlays.OverlayTypes.PathTypes.OverlayStar;
import flagmaker.Overlays.OverlayTypes.SpecialTypes.OverlayFlag;
import javafx.scene.paint.Color;
import org.junit.Test;
import static org.junit.Assert.*;

public class FlagTest {
    
 private final int MaxX = 5;
	private final int MaxY = 3;
	private final Ratio Ratio = new Ratio(MaxX, MaxY);
	
	@Test
	public void GridTest()
	{
		Flag f = new Flag("Basic", Ratio, Ratio, new DivisionGrid(Color.RED, Color.WHITE, 2, 2), new Overlay[]{});
		f.ShuffleColors();
		
		assertEquals(Color.WHITE, f.Division.Colors[0]);
		assertEquals(Color.RED, f.Division.Colors[1]);
	}

	@Test
	public void BlankTest()
	{
		Flag f = GetSampleFlag();
		f.ShuffleColors();
		
		assertEquals(Color.RED, f.Division.Colors[0]);
	}

	@Test
	public void PalesTest()
	{
		Flag f = new Flag("Basic", Ratio, Ratio, new DivisionPales(Color.RED, Color.WHITE, Color.BLUE, 1, 1, 1), new Overlay[]{});
		f.ShuffleColors();
		
		assertEquals(Color.BLUE, f.Division.Colors[0]);
		assertEquals(Color.RED, f.Division.Colors[1]);
		assertEquals(Color.WHITE, f.Division.Colors[2]);
	}
	
	@Test
	public void SingleOverlayTest()
	{
		Flag f = GetSampleFlag(new Overlay[] { new OverlayLineHorizontal(Color.WHITE, 1.5, 1, MaxX, MaxY) });
		f.ShuffleColors();
		
		assertEquals(Color.WHITE, f.Division.Colors[0]);
		assertEquals(Color.RED, f.Overlays[0].GetColorAttribute("Color"));
	}
	
	@Test
	public void FlagOverlayTest()
	{
		Flag inner = new Flag("inner", Ratio, Ratio, GetBlankDivision(Color.GREEN), new Overlay[] { new OverlayLineHorizontal(Color.WHITE, 1.5, 1, MaxX, MaxY) });
		Flag outer = GetSampleFlag(new Overlay[] { new OverlayFlag(inner, null, 0, 0, MaxX / 2, MaxX / 2, MaxX, MaxY)});
		outer.ShuffleColors();
		
		assertEquals(Color.GREEN, outer.Division.Colors[0]);
		assertEquals(Color.WHITE, inner.Division.Colors[0]);
		assertEquals(Color.RED, inner.Overlays[0].GetColorAttribute("Color"));
		assertEquals(inner, ((OverlayFlag)outer.Overlays[0]).Flag);
	}
	
	@Test
	public void PathOverlayTest()
	{
		Overlay o = new OverlayStar(MaxX, MaxY);
		o.SetAttribute("Color", Color.GREEN);
		Flag f = GetSampleFlag(new Overlay[] { o });
		f.ShuffleColors();
		
		assertEquals(Color.GREEN, f.Division.Colors[0]);
		assertEquals(Color.RED, o.GetColorAttribute("Color"));
		assertEquals(Color.BLACK, o.GetColorAttribute("StrokeColor")); // unchanged
	}
	
	@Test
	public void PathStrokedOverlayTest()
	{
		Overlay o = new OverlayStar(MaxX, MaxY);
		o.SetAttribute("Color", Color.GREEN);
		o.SetAttribute("StrokeColor", Color.BLUE);
		o.SetAttribute("Stroke", 1.0);
		
		Flag f = GetSampleFlag(new Overlay[] { o });
		f.ShuffleColors();
		
		assertEquals(Color.GREEN, f.Division.Colors[0]);
		assertEquals(Color.BLUE, o.GetColorAttribute("Color"));
		assertEquals(Color.RED, o.GetColorAttribute("StrokeColor"));
	}
	
	private Flag GetSampleFlag()
	{
		return GetSampleFlag(new Overlay[]{});
	}
	
	private Flag GetSampleFlag(Overlay[] overlays)
	{
		return new Flag("Basic", Ratio, Ratio, GetBlankDivision(Color.RED), overlays);
	}
	
	private Division GetBlankDivision(Color color)
	{
		return new DivisionGrid(color, Color.TRANSPARENT, 1, 1);
	}

    
}
