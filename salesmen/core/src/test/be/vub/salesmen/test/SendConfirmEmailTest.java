package be.vub.salesmen.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class SendConfirmEmailTest extends SeamTest {

	@Test
	public void test_sendConfirmEmail() throws Exception {
		new FacesRequest("/sendConfirmEmail.xhtml") {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{SendConfirmEmail.sendConfirmEmail}");
			}
		}.run();
	}
}
