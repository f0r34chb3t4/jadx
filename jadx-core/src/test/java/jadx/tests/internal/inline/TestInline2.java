package jadx.tests.internal.inline;

import jadx.api.InternalJadxTest;
import jadx.core.dex.nodes.ClassNode;

import org.junit.Test;

import static jadx.tests.utils.JadxMatchers.containsOne;
import static org.junit.Assert.assertThat;

public class TestInline2 extends InternalJadxTest {

	public static class TestCls {
		public int test() throws InterruptedException {
			int[] a = new int[]{1, 2, 4, 6, 8};
			int b = 0;
			for (int i = 0; i < a.length; i+=2) {
				b += a[i];
			}
			for (long i = b; i > 0; i--) {
				b += i;
			}
			return b;
		}
	}

	@Test
	public void test() {
		ClassNode cls = getClassNode(TestCls.class);
		String code = cls.getCode().toString();
		System.out.println(code);

		assertThat(code, containsOne("int[] a = new int[]{1, 2, 4, 6, 8};"));
		assertThat(code, containsOne("for (int i = 0; i < a.length; i += 2) {"));
		assertThat(code, containsOne("for (long i2 = (long) b; i2 > 0; i2--) {"));
	}
}
