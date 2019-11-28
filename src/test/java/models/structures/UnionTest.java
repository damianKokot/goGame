package models.structures;

import org.junit.Assert;
import org.junit.Test;

public class UnionTest {

   @Test
   public void shouldPointItself() {
      Union head = new Union();
      Assert.assertEquals(head, head.find());
   }

   @Test
   public void shouldUnify() {
      Union head = new Union();
      Union child = new Union();

      child.union(head);
      Assert.assertEquals(head, child.find());
   }

   @Test
   public void shouldAddToArray() {
      Union head = new Union();
      Union h1 = new Union();
      Union h2 = new Union();
      Union h3 = new Union();
      h1.union(head);
      h2.union(head);
      h3.union(head);

      Union child = new Union();
      Union c1 = new Union();
      Union c2 = new Union();
      Union c3 = new Union();
      c1.union(child);
      c2.union(child);
      c3.union(child);

      child.union(head);

      Union[] heads = new Union[]{head, head, head, head, head, head, head};
      Union[] childes = new Union[]{child.find(), c1.find(), c2.find(), c3.find(), h1.find(), h2.find(), h3.find()};
      Assert.assertArrayEquals(heads, childes);
   }

   @Test
   public void shouldReset() {
      Union head = new Union();
      Union h1 = new Union();
      Union h2 = new Union();
      Union h3 = new Union();
      h1.union(h2);
      h2.union(h3);
      h3.union(head);

      Union child = new Union();
      Union c1 = new Union();
      Union c2 = new Union();
      Union c3 = new Union();
      c1.union(head);
      c2.union(c1);
      c3.union(c2);

      child.union(h2);

      head.resetAllChildes();

      Union[] heads = new Union[]{child, c1, c2, c3, h1, h2, h3, head};
      Union[] childes = new Union[]{child.find(), c1.find(), c2.find(), c3.find(), h1.find(), h2.find(), h3.find(), head.find()};
      Assert.assertArrayEquals(heads, childes);
   }

   @Test
   public void shouldNotBeLooped() {
      Union h1 = new Union();
      Union h2 = new Union();

      h1.union(h2);
      Assert.assertEquals(h2, h2.union(h1));
   }
}
