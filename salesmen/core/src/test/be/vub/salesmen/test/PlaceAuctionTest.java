package be.vub.salesmen.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class PlaceAuctionTest extends SeamTest {

	@Test
	public void test_placeAuction() throws Exception {
		new FacesRequest("/placeAuction.xhtml") {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{placeAuction.placeAuction}");
			}
		}.run();
	}
}
